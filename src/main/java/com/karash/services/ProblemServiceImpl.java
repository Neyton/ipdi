package com.karash.services;

import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.cost.VehicleRoutingTransportCosts;
import com.graphhopper.jsprit.core.problem.job.Shipment;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.util.Coordinate;
import com.graphhopper.jsprit.core.util.Solutions;
import com.graphhopper.jsprit.core.util.VehicleRoutingTransportCostsMatrix;
import com.karash.DTO.ProblemDTO;
import com.karash.DTO.Shipments;
import com.karash.DTO.Vehicle_types;
import com.karash.DTO.Vehicles;

import java.math.BigDecimal;
import java.util.*;

@org.springframework.stereotype.Service
public class ProblemServiceImpl implements ProblemService {

    @Override
    public VehicleRoutingProblemSolution getSolution(ProblemDTO pojo) {

        VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance(pojo.getVehicle_types()[0].getProfile())
                .addCapacityDimension(0, 2).setCostPerTime(2);
        VehicleType vehicleType = vehicleTypeBuilder.build();


        VehicleImpl.Builder vehicleBuilder1 = VehicleImpl.Builder.newInstance("[1]");
        vehicleBuilder1.setStartLocation(loc(pojo.getVehicles()[0].getStartAddress().getLocationId(), Coordinate.newInstance(Double.valueOf(pojo.getVehicles()[0].getStartAddress().getLat()), Double.valueOf(pojo.getVehicles()[0].getStartAddress().getLon())))).setReturnToDepot(false);
        vehicleBuilder1.setType(vehicleType);
        vehicleBuilder1.setEarliestStart(1493964000);
        VehicleImpl vehicle1 = vehicleBuilder1.build();

        VehicleImpl.Builder vehicleBuilder2 = VehicleImpl.Builder.newInstance("[2]");
        vehicleBuilder2.setStartLocation(loc(pojo.getVehicles()[1].getStartAddress().getLocationId(), Coordinate.newInstance(Double.valueOf(pojo.getVehicles()[1].getStartAddress().getLat()), Double.valueOf(pojo.getVehicles()[1].getStartAddress().getLon())))).setReturnToDepot(false);
        vehicleBuilder2.setEarliestStart(1493964000);
        vehicleBuilder2.setType(vehicleType);
        VehicleImpl vehicle2 = vehicleBuilder2.build();


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
//        Shipment shipment1 = Shipment.Builder.newInstance("1").addSizeDimension(0, 1)
//                .setPickupLocation(loc(pojo.getShipments()[0].getPickup().getAddress().getLocationId(),
//                        Coordinate.newInstance(Double.valueOf(pojo.getShipments()[0].getPickup().getAddress().getLat()), Double.valueOf(pojo.getShipments()[0].getPickup().getAddress().getLon()))))
//                .setDeliveryLocation(loc(pojo.getShipments()[0].getDelivery().getAddress().getLocationId(),
//                        Coordinate.newInstance(Double.valueOf(pojo.getShipments()[0].getDelivery().getAddress().getLat()), Double.valueOf(pojo.getShipments()[0].getDelivery().getAddress().getLat()))))
//
//                .addPickupTimeWindow(Double.parseDouble(pojo.getShipments()[0].getPickup().getTimeWindows()[0].getEarliest()),
//                        Double.parseDouble(pojo.getShipments()[0].getPickup().getTimeWindows()[0].getLatest()))
//                .addDeliveryTimeWindow(Double.parseDouble(pojo.getShipments()[0].getDelivery().getTime_windows()[0].getEarliest()),
//                        Double.parseDouble(pojo.getShipments()[0].getDelivery().getTime_windows()[0].getLatest())).build();

        List<Shipment> shipments = new ArrayList<>();
        for (Shipments shipment : pojo.getShipments()) {
            Shipment shipment1 = Shipment.Builder.newInstance("shipment" + shipment.getId()).addSizeDimension(0, 1)
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
            VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance("vehicle" + vehicle.getType_id())
                    .addCapacityDimension(0, Integer.parseInt(vehicle.getCapacity()[0])).setCostPerTime(2);
            vehicleTypes.add(vehicleTypeBuilder.build());
        }
        return vehicleTypes;
    }

    private List<VehicleImpl> buildVehicles(ProblemDTO pojo) {
        List<VehicleImpl> vehicles = new ArrayList<>();
        List<VehicleType> vehicleTypes = this.buildVehicleTypes(pojo);
        for (Vehicles vehicle : pojo.getVehicles()) {
            VehicleImpl.Builder vehicleBuilder1 = VehicleImpl.Builder.newInstance(vehicle.getVehicleId());
            vehicleBuilder1.setStartLocation(loc(vehicle.getStartAddress().getLocationId(),
                    vehicle.getStartAddress().getLat(), vehicle.getStartAddress().getLon())).
                    setReturnToDepot(vehicle.getReturnToDepot());
            vehicleBuilder1.setType(vehicleTypes.stream().filter(v -> v.getTypeId().equals(vehicle.getTypeId())).findFirst().get());
            vehicleBuilder1.setEarliestStart(Double.parseDouble(vehicle.getEarliestStart()));
            vehicles.add(vehicleBuilder1.build());
        }
        return vehicles;
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
