package com.karash.services;

import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.algorithm.termination.VariationCoefficientTermination;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.Skills;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProblemServiceImpl implements ProblemService {
    @Value(value = "${max_threads}")
    private Integer maxThreads;

    @Value(value = "${max_iterations}")
    private Integer maxIterations;

    @Value(value = "${variation_coefficient_threshold}")
    private Double variationCoefficientThreshold;

    @Value(value = "${no_iterations}")
    private Integer noIterations;


    @Override
    public VehicleRoutingProblemSolution getSolution(ProblemDTO data) {
        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        this.buildVehicles(data).forEach(v -> vrpBuilder.addVehicle(v));
        this.buildShipments(data).forEach(s -> vrpBuilder.addJob(s));
        this.buildServices(data).forEach(s -> vrpBuilder.addJob(s));
        vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.FINITE);

        VehicleRoutingTransportCostsMatrix.Builder costMatrixBuilder = VehicleRoutingTransportCostsMatrix.Builder.newInstance(true);

        Map<String, Map<String, Integer>> cost_matrices = (Map<String, Map<String, Integer>>) data.getCost_matrices();
        cost_matrices.forEach((s, stringStringMap) -> stringStringMap.forEach((k, v) -> {
            costMatrixBuilder.addTransportTime(s, k, v);
        }));
        vrpBuilder.setRoutingCost(costMatrixBuilder.build());
        if (data.getMax_threads() != null) {
            maxThreads = data.getMax_threads();
        }
        VehicleRoutingProblem build = vrpBuilder.build();
        VehicleRoutingAlgorithm vra = Jsprit.Builder.newInstance(build).setProperty(Jsprit.Parameter.THREADS, String.valueOf(maxThreads)).buildAlgorithm();

        buildTerminate(vra, data);
        if (data.getMax_iterations() != null) {
            this.maxIterations = data.getMax_iterations();
        }
        vra.setMaxIterations(maxIterations);
        return Solutions.bestOf(vra.searchSolutions());
    }

    private void buildTerminate(VehicleRoutingAlgorithm vra, ProblemDTO data) {
        if (data.getNo_iterations() != null) {
            this.noIterations = data.getNo_iterations();
        }
        if (data.getVariation_coefficient_threshold() != null) {
            this.variationCoefficientThreshold = data.getVariation_coefficient_threshold();
        }
        VariationCoefficientTermination termination = new VariationCoefficientTermination(this.noIterations, this.variationCoefficientThreshold);
        vra.setPrematureAlgorithmTermination(termination);
        vra.addListener(termination);
    }

    private List<Shipment> buildShipments(ProblemDTO data) {
        List<Shipment> shipments = new ArrayList<>();
        if (data.getShipments() != null) {
            for (Shipments shipment : data.getShipments()) {
                Shipment.Builder shipmentBuilder = Shipment.Builder.newInstance(shipment.getId());
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
                for (String rs : shipment.getAllowed_vehicles()) {
                    shipmentBuilder.addRequiredSkill("vehicle_" + rs);
                }
                shipments.add(shipmentBuilder.build());
            }
        }
        return shipments;
    }

    private List<VehicleType> buildVehicleTypes(ProblemDTO data) {
        List<VehicleType> vehicleTypes = new ArrayList<>();
        for (Vehicle_types vehicle : data.getVehicle_types()) {
            VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance(vehicle.getType_id());
            for (int i = 0; i < vehicle.getCapacity().length; i++) {
                vehicleTypeBuilder.addCapacityDimension(i, Integer.parseInt(vehicle.getCapacity()[i]));
            }
            vehicleTypeBuilder.setCostPerTime(1);
            vehicleTypeBuilder.setProfile(vehicle.getProfile());
            vehicleTypes.add(vehicleTypeBuilder.build());
        }
        return vehicleTypes;
    }

    private List<VehicleImpl> buildVehicles(ProblemDTO data) {
        List<VehicleImpl> vehicles = new ArrayList<>();
        List<VehicleType> vehicleTypes = this.buildVehicleTypes(data);
        for (Vehicles vehicle : data.getVehicles()) {
            VehicleImpl.Builder builder = VehicleImpl.Builder.newInstance(vehicle.getVehicle_id());
            builder.setStartLocation(loc(vehicle.getStart_address().getLocation_id(),
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

    private List<Service> buildServices(ProblemDTO data) {
        List<Service> services = new ArrayList<>();
        for (Services service : data.getServices()) {
            Service.Builder serviceBuilder = Service.Builder.newInstance(service.getId());
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

    private void addDeliveryTimeWindow(Shipments shipment, Shipment.Builder builder) {
        for (Time_windows timeWindow : shipment.getDelivery().getTime_windows()) {
            builder.addDeliveryTimeWindow(timeWindow.getEarliest(), timeWindow.getLatest());
        }
    }

    private void addPickupTimeWindow(Shipments shipment, Shipment.Builder builder) {
        for (Time_windows timeWindow : shipment.getPickup().getTime_windows()) {
            builder.addPickupTimeWindow(timeWindow.getEarliest(), timeWindow.getLatest());
        }
    }

    private Shipment.Builder setShipmentPickUpLocation(Shipments shipment, Shipment.Builder builder) {
        return builder.setPickupLocation(loc(shipment.getPickup().getAddress().getLocation_id(),
                shipment.getPickup().getAddress().getLat(), shipment.getPickup().getAddress().getLon())).setName(shipment.getPickup().getAddress().getName());
    }

    private Shipment.Builder setShipmentDeliveryLocation(Shipments shipment, Shipment.Builder builder) {
        return builder.setDeliveryLocation(loc(shipment.getDelivery().getAddress().getLocation_id(),
                shipment.getDelivery().getAddress().getLat(), shipment.getDelivery().getAddress().getLon())).setName(shipment.getDelivery().getAddress().getName());
    }

    private Location loc(String id, Double x, Double y) {
        return Location.Builder.newInstance().setCoordinate(Coordinate.newInstance(x, y)).setId(id).build();
    }
}
