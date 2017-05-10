package com.karash.services;

import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.Skills;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.cost.VehicleRoutingTransportCosts;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.job.Shipment;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.solution.route.activity.TimeWindow;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.util.Coordinate;
import com.graphhopper.jsprit.core.util.Solutions;
import com.graphhopper.jsprit.core.util.VehicleRoutingTransportCostsMatrix;
import com.karash.DTO.ProblemDTO;
import com.karash.DTO.Services;
import com.karash.DTO.Shipments;
import com.karash.DTO.Time_windows;
import com.karash.DTO.Vehicle_types;
import com.karash.DTO.Vehicles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class ProblemServiceImpl implements ProblemService {

    @Override
    public VehicleRoutingProblemSolution getSolution(ProblemDTO pojo) {
        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();

        this.buildVehicles(pojo).forEach(v -> vrpBuilder.addVehicle(v));
        this.buildShipments(pojo).forEach(s -> vrpBuilder.addJob(s));
        this.buildServices(pojo).forEach(s->vrpBuilder.addJob(s));
        vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.FINITE);

        VehicleRoutingTransportCostsMatrix.Builder costMatrixBuilder = VehicleRoutingTransportCostsMatrix.Builder.newInstance(true);

        Map<String, Map<String, Integer>> cost_matrices = (Map<String, Map<String, Integer>>) pojo.getCost_matrices();


        cost_matrices.forEach((s, stringStringMap) -> stringStringMap.forEach((k, v) -> {
            costMatrixBuilder.addTransportTime(s, k, v);
        }));

        VehicleRoutingTransportCosts costMatrix = costMatrixBuilder.build();


        VehicleRoutingAlgorithm vra = Jsprit.createAlgorithm(vrpBuilder.build());

        Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();
        VehicleRoutingProblemSolution vehicleRoutingProblemSolution = Solutions.bestOf(solutions);
        vehicleRoutingProblemSolution.getRoutes().stream().findAny().orElse(null).getTourActivities().getActivities().forEach(t -> {
            System.out.println("arrtime=" + new BigDecimal(t.getArrTime()).toPlainString());
            System.out.println("endtime=" + new BigDecimal(t.getEndTime()).toPlainString());
        });
        return vehicleRoutingProblemSolution;
    }

    private List<Shipment> buildShipments(ProblemDTO pojo) {
        List<Shipment> shipments = new ArrayList<>();
        for (Shipments shipment : pojo.getShipments()) {
            Shipment.Builder shipmentBuilder = Shipment.Builder.newInstance("shipment=" + shipment.getId());
            for (int i = 0; i < shipment.getSize().length; i++) {
                shipmentBuilder.addSizeDimension(i, shipment.getSize()[i]);
            }
            setShipmentPickUpLocation(shipment, shipmentBuilder);
            setShipmentDeliveryLocation(shipment, shipmentBuilder);
            addPickupTimeWindow(shipment, shipmentBuilder);
            addDeliveryTimeWindow(shipment, shipmentBuilder);
            shipmentBuilder.setPickupServiceTime(Double.valueOf(shipment.getPickup().getDuration()));
            shipmentBuilder.setDeliveryServiceTime(Double.valueOf(shipment.getDelivery().getDuration()));

            for (String skill : shipment.getRequired_skills()) {
                shipmentBuilder.addRequiredSkill(skill);
            }
//            required_skills???
//            allowed_vehicles???

            shipments.add(shipmentBuilder.build());
        }

        return shipments;
    }

    private void addDeliveryTimeWindow(Shipments shipment, Shipment.Builder shipmentBuilder) {
        for (Time_windows timeWindow : shipment.getDelivery().getTime_windows()) {
            shipmentBuilder.addDeliveryTimeWindow(Double.valueOf(timeWindow.getEarliest()), Double.valueOf(timeWindow.getLatest()));
        }
    }

    private void addPickupTimeWindow(Shipments shipment, Shipment.Builder shipmentBuilder) {
        for (Time_windows timeWindow : shipment.getPickup().getTime_windows()) {
            shipmentBuilder.addPickupTimeWindow(Double.valueOf(timeWindow.getEarliest()), Double.valueOf(timeWindow.getLatest()));
        }
    }

    private Shipment.Builder setShipmentPickUpLocation(Shipments shipment, Shipment.Builder shipmentBuilder) {
        return shipmentBuilder.setPickupLocation(loc(shipment.getPickup().getAddress().getLocation_id(),
                shipment.getPickup().getAddress().getLat(), shipment.getPickup().getAddress().getLon())).setName(shipment.getPickup().getAddress().getName());
    }

    private Shipment.Builder setShipmentDeliveryLocation(Shipments shipment, Shipment.Builder shipmentBuilder) {
        return shipmentBuilder.setDeliveryLocation(loc(shipment.getDelivery().getAddress().getLocation_id(),
                shipment.getDelivery().getAddress().getLat(), shipment.getDelivery().getAddress().getLon())).setName(shipment.getDelivery().getAddress().getName());
    }

    private List<VehicleType> buildVehicleTypes(ProblemDTO pojo) {
        List<VehicleType> vehicleTypes = new ArrayList<>();
        for (Vehicle_types vehicle : pojo.getVehicle_types()) {
            VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance("vehicletype=" + vehicle.getType_id());
            for (int i = 0; i < vehicle.getCapacity().length; i++) {
                vehicleTypeBuilder.addCapacityDimension(i, Integer.parseInt(vehicle.getCapacity()[i]));
            }
            vehicleTypeBuilder.setCostPerTime(2);
            vehicleTypeBuilder.setProfile(vehicle.getProfile());
            vehicleTypes.add(vehicleTypeBuilder.build());
        }
        return vehicleTypes;
    }

    private List<VehicleImpl> buildVehicles(ProblemDTO pojo) {
        List<VehicleImpl> vehicles = new ArrayList<>();
        List<VehicleType> vehicleTypes = this.buildVehicleTypes(pojo);
        for (Vehicles vehicle : pojo.getVehicles()) {
            VehicleImpl.Builder builder = VehicleImpl.Builder.newInstance("vehicle=" + vehicle.getVehicle_id());
            builder.setStartLocation(loc(vehicle.getStart_address().getLocationId(),
                    vehicle.getStart_address().getLat(), vehicle.getStart_address().getLon())).
                    setReturnToDepot(vehicle.getReturn_to_depot());
            builder.setType(vehicleTypes.stream().filter(v -> v.getProfile().equals(vehicle.getType_id())).findFirst().orElse(null));
            builder.setEarliestStart(Double.parseDouble(vehicle.getEarliest_start()));
            if (Double.parseDouble(vehicle.getLatest_end()) > 0)
                builder.setLatestArrival(Double.parseDouble(vehicle.getLatest_end()));
            builder.addSkills(buildSkills(vehicle.getSkills()));
            vehicles.add(builder.build());
        }
        return vehicles;
    }

    private Skills buildSkills(String skills[]) {
        Skills.Builder skillBuilder = Skills.Builder.newInstance();
        for (String skill : skills) {
            skillBuilder.addSkill(skill);
        }
        return skillBuilder.build();
    }


    private VehicleImpl.Builder createVehicle(Vehicles vehicles) {
        VehicleImpl.Builder vehicleBuilder = VehicleImpl.Builder.newInstance("vehicle");
        vehicleBuilder.setStartLocation(Location.newInstance(vehicles.getStart_address().getLocationId()));
        return vehicleBuilder;
    }

    private List<Service> buildServices(ProblemDTO pojo) {
        List<Service> services = new ArrayList<>();
        for (Services service : pojo.getServices()) {
            Service.Builder serviceBuilder = Service.Builder.newInstance("service=" + service.getId());
            serviceBuilder.setName(service.getName());
            serviceBuilder.setLocation(loc(service.getAddress().getLocation_id(),
                    service.getAddress().getLat(), service.getAddress().getLon()));
            serviceBuilder.setServiceTime(service.getDuration());
            for (Time_windows tw : service.getTime_windows()) {
                serviceBuilder.addTimeWindow(TimeWindow.newInstance(tw.getEarliest(), tw.getLatest()));
            }
            for (String rs : service.getRequired_skills()) {
                serviceBuilder.addRequiredSkill(rs);
            }
            for (int i = 0; i < service.getSize().length; i++) {
                serviceBuilder.addSizeDimension(i, Integer.valueOf(service.getSize()[i]));
            }
            services.add(serviceBuilder.build());
        }
        return services;
    }

    private Location loc(String id, Double x, Double y) {
        return Location.Builder.newInstance().setId(id)
                .setCoordinate(Coordinate.newInstance(x, y)).build();
    }
}
