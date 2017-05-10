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
import com.karash.DTO.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class ProblemServiceImpl implements ProblemService {

    @Override
    public VehicleRoutingProblemSolution getSolution(ProblemDTO pojo) {

        VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance(pojo.getVehicle_types()[0].getProfile())
                .addCapacityDimension(0, 2).setCostPerTime(2);
        VehicleType vehicleType = vehicleTypeBuilder.build();


        Shipment shipment1 = Shipment.Builder.newInstance("1").addSizeDimension(0, 1)
                .setPickupLocation(loc(pojo.getShipments()[0].getPickup().getAddress().getLocationId(),
                        Coordinate.newInstance(Double.valueOf(pojo.getShipments()[0].getPickup().getAddress().getLat()), Double.valueOf(pojo.getShipments()[0].getPickup().getAddress().getLon()))))
                .setDeliveryLocation(loc(pojo.getShipments()[0].getDelivery().getAddress().getLocationId(),
                        Coordinate.newInstance(Double.valueOf(pojo.getShipments()[0].getDelivery().getAddress().getLat()), Double.valueOf(pojo.getShipments()[0].getDelivery().getAddress().getLat()))))

                .addPickupTimeWindow(Double.parseDouble(pojo.getShipments()[0].getPickup().getTimeWindows()[0].getEarliest()),
                        Double.parseDouble(pojo.getShipments()[0].getPickup().getTimeWindows()[0].getLatest()))
                .addDeliveryTimeWindow(Double.parseDouble(pojo.getShipments()[0].getDelivery().getTime_windows()[0].getEarliest()),
                        Double.parseDouble(pojo.getShipments()[0].getDelivery().getTime_windows()[0].getLatest())).build();

        Shipment shipment2 = Shipment.Builder.newInstance("2").addSizeDimension(0, 1)
                .setPickupLocation(loc(pojo.getShipments()[1].getPickup().getAddress().getLocationId(), Coordinate.newInstance(Double.valueOf(pojo.getShipments()[1].getPickup().getAddress().getLat()), Double.valueOf(pojo.getShipments()[1].getPickup().getAddress().getLon()))))
                .setDeliveryLocation(loc(pojo.getShipments()[1].getDelivery().getAddress().getLocationId(), Coordinate.newInstance(Double.valueOf(pojo.getShipments()[1].getDelivery().getAddress().getLat()), Double.valueOf(pojo.getShipments()[1].getDelivery().getAddress().getLat()))))

                .addPickupTimeWindow(Double.parseDouble(pojo.getShipments()[1].getPickup().getTimeWindows()[0].getEarliest()),
                        Double.parseDouble(pojo.getShipments()[1].getPickup().getTimeWindows()[0].getLatest()))
                .addDeliveryTimeWindow(Double.parseDouble(pojo.getShipments()[1].getDelivery().getTime_windows()[0].getEarliest()),
                        Double.parseDouble(pojo.getShipments()[1].getDelivery().getTime_windows()[0].getLatest())).build();

        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        vrpBuilder.addVehicle(vehicle1).addVehicle(vehicle2);
        vrpBuilder.addJob(shipment1).addJob(shipment2);
        vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.FINITE);

        VehicleRoutingTransportCostsMatrix.Builder costMatrixBuilder = VehicleRoutingTransportCostsMatrix.Builder.newInstance(true);

        Map<String, Map<String, Integer>> cost_matrices = (Map<String, Map<String, Integer>>) pojo.getCost_matrices();


        cost_matrices.forEach((s, stringStringMap) -> stringStringMap.forEach((k, v) -> {
            costMatrixBuilder.addTransportTime(s, k, v);
        }));

        VehicleRoutingTransportCosts costMatrix = costMatrixBuilder.build();


        VehicleRoutingProblem vrp = VehicleRoutingProblem.Builder.newInstance().setFleetSize(VehicleRoutingProblem.FleetSize.INFINITE).setRoutingCost(costMatrix)
                .addVehicle(vehicle1).addVehicle(vehicle2).addJob(shipment1).addJob(shipment2).build();

        VehicleRoutingAlgorithm vra = Jsprit.createAlgorithm(vrp);

        Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();
        VehicleRoutingProblemSolution vehicleRoutingProblemSolution = Solutions.bestOf(solutions);
        vehicleRoutingProblemSolution.getRoutes().stream().findFirst().orElse(null).getTourActivities().getActivities().forEach(t -> {
            System.out.println("arrtime=" + new BigDecimal(t.getArrTime()).toPlainString());
            System.out.println("endtime=" + new BigDecimal(t.getEndTime()).toPlainString());
            System.out.println("____________________________________________________________");
        });
        return null;

    }

    private List<Shipment> buildShipments(ProblemDTO pojo) {
        List<Shipment> shipments = new ArrayList<>();
        for (Shipments shipment : pojo.getShipments()) {
            Shipment shipment1 = Shipment.Builder.newInstance("shipment=" + shipment.getId()).addSizeDimension(0, 1)
                    .setPickupLocation(loc(shipment.getPickup().getAddress().getLocationId(),
                            shipment.getPickup().getAddress().getLat(), shipment.getPickup().getAddress().getLon()))
                    .setDeliveryLocation(loc(shipment.getDelivery().getAddress().getLocationId(), shipment.getDelivery().getAddress().getLat(), shipment.getDelivery().getAddress().getLon()))
                    .addPickupTimeWindow(Double.valueOf(shipment.getPickup().getTimeWindows()[0].getEarliest()), Double.valueOf(shipment.getPickup().getTimeWindows()[0].getLatest())).build();
            shipments.add(shipment1);
        }

        return null;
    }

    private List<VehicleType> buildVehicleTypes(ProblemDTO pojo) {
        List<VehicleType> vehicleTypes = new ArrayList<>();
        for (Vehicle_types vehicle : pojo.getVehicle_types()) {
            VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance("vehicletype=" + vehicle.getType_id());
            for (int i = 0; i < vehicle.getCapacity().length; i++) {
                vehicleTypeBuilder.addCapacityDimension(i, Integer.parseInt(vehicle.getCapacity()[i]));
            }
            vehicleTypeBuilder.setCostPerTime(2);
            vehicleTypes.add(vehicleTypeBuilder.build());
        }
        return vehicleTypes;
    }

    private List<VehicleImpl> buildVehicles(ProblemDTO pojo) {
        List<VehicleImpl> vehicles = new ArrayList<>();
        List<VehicleType> vehicleTypes = this.buildVehicleTypes(pojo);
        for (Vehicles vehicle : pojo.getVehicles()) {
            VehicleImpl.Builder builder = VehicleImpl.Builder.newInstance("vehicle=" + vehicle.getVehicleId());
            builder.setStartLocation(loc(vehicle.getStartAddress().getLocationId(),
                    vehicle.getStartAddress().getLat(), vehicle.getStartAddress().getLon())).
                    setReturnToDepot(vehicle.getReturnToDepot());
            builder.setType(vehicleTypes.stream().filter(v -> v.getTypeId().equals(vehicle.getTypeId())).findFirst().get());
            builder.setEarliestStart(Double.parseDouble(vehicle.getEarliestStart()));
            builder.setLatestArrival(Double.parseDouble(vehicle.getLatestEnd()));
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

    private List<Services> buildServices(ProblemDTO pojo){
        List<Services> services = new ArrayList<>();
        for (Services service : services) {
            Service.Builder serviceBuilder = Service.Builder.newInstance("service=" + service.getId());
            serviceBuilder.setName(service.getName());
            serviceBuilder.setLocation(loc(service.getAddress().getLocationId(),
                    service.getAddress().getLat(), service.getAddress().getLon()));
            serviceBuilder.setServiceTime(service.getDuration());
            for (TimeWindows tw : service.getTime_windows()) {
                serviceBuilder.setTimeWindow(TimeWindow.newInstance(tw.getEarliest(), tw.getLatest()));
            }
        }
        return services;
    }

    private VehicleImpl.Builder createVehicle(Vehicles vehicles) {
        VehicleImpl.Builder vehicleBuilder = VehicleImpl.Builder.newInstance("vehicle");
        vehicleBuilder.setStartLocation(Location.newInstance(vehicles.getStartAddress().getLocationId()));
        return vehicleBuilder;
    }

    private Location loc(String id, Double x, Double y) {
        return Location.Builder.newInstance().setId(id)
                .setCoordinate(Coordinate.newInstance(x, y)).build();
    }

}
