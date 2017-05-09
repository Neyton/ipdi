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
import com.karash.DTO.Vehicles;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Map;

@org.springframework.stereotype.Service
public class ProblemServiceImpl implements ProblemService {

    @Override
    public VehicleRoutingProblemSolution getSolution(ProblemDTO pojo) {

        VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance(pojo.getVehicle_types()[0].getProfile())
                .addCapacityDimension(0, 2).setCostPerTime(1);
        VehicleType vehicleType = vehicleTypeBuilder.build();
//        VehicleType vehicleType = VehicleTypeImpl.Builder.newInstance("type").addCapacityDimension(0, 2).setCostPerDistance(1).setCostPerTime(2).build();
//        VehicleImpl vehicle = VehicleImpl.Builder.newInstance("vehicle").setStartLocation(Location.newInstance("0")).setType(vehicleType).build();

        VehicleImpl.Builder vehicleBuilder1 = VehicleImpl.Builder.newInstance("[1]");
        vehicleBuilder1.setStartLocation(loc(pojo.getVehicles()[0].getStart_address().getLocation_id(), Coordinate.newInstance(Double.valueOf(pojo.getVehicles()[0].getStart_address().getLat()), Double.valueOf(pojo.getVehicles()[0].getStart_address().getLon())))).setReturnToDepot(false);
        vehicleBuilder1.setType(vehicleType);
        vehicleBuilder1.setEarliestStart(1493964000-1493931600);
        VehicleImpl vehicle1 = vehicleBuilder1.build();

        VehicleImpl.Builder vehicleBuilder2 = VehicleImpl.Builder.newInstance("[2]");
        vehicleBuilder2.setStartLocation(loc(pojo.getVehicles()[1].getStart_address().getLocation_id(), Coordinate.newInstance(Double.valueOf(pojo.getVehicles()[1].getStart_address().getLat()), Double.valueOf(pojo.getVehicles()[1].getStart_address().getLon())))).setReturnToDepot(false);
        vehicleBuilder2.setEarliestStart(1493964000-1493931600);
        vehicleBuilder2.setType(vehicleType);
        VehicleImpl vehicle2 = vehicleBuilder2.build();


        Shipment shipment1 = Shipment.Builder.newInstance("1").addSizeDimension(0, 1)
                .setPickupLocation(loc(pojo.getShipments()[0].getPickup().getAddress().getLocation_id(), Coordinate.newInstance(Double.valueOf(pojo.getShipments()[0].getPickup().getAddress().getLat()), Double.valueOf(pojo.getShipments()[0].getPickup().getAddress().getLon()))))
                .setDeliveryLocation(loc(pojo.getShipments()[0].getDelivery().getAddress().getLocation_id(), Coordinate.newInstance(Double.valueOf(pojo.getShipments()[0].getDelivery().getAddress().getLat()), Double.valueOf(pojo.getShipments()[0].getDelivery().getAddress().getLat()))))
                .addPickupTimeWindow(1493967600-1493931600, 1493969400-1493931600).addDeliveryTimeWindow(1493971200-1493931600, 1493973000-1493931600).build();

        Shipment shipment2 = Shipment.Builder.newInstance("2").addSizeDimension(0, 1)
                .setPickupLocation(loc(pojo.getShipments()[1].getPickup().getAddress().getLocation_id(), Coordinate.newInstance(Double.valueOf(pojo.getShipments()[1].getPickup().getAddress().getLat()), Double.valueOf(pojo.getShipments()[1].getPickup().getAddress().getLon()))))
                .setDeliveryLocation(loc(pojo.getShipments()[1].getDelivery().getAddress().getLocation_id(), Coordinate.newInstance(Double.valueOf(pojo.getShipments()[1].getDelivery().getAddress().getLat()), Double.valueOf(pojo.getShipments()[1].getDelivery().getAddress().getLat()))))
                .addPickupTimeWindow(1493974800-1493931600, 1493982000-1493931600).addDeliveryTimeWindow(1493985600-1493931600, 1493989200-1493931600).build();

        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        vrpBuilder.addVehicle(vehicle1).addVehicle(vehicle2);
        vrpBuilder.addJob(shipment1).addJob(shipment2);
        vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.FINITE);

        VehicleRoutingTransportCostsMatrix.Builder costMatrixBuilder = VehicleRoutingTransportCostsMatrix.Builder.newInstance(true);
//        costMatrixBuilder.addTransportDistance("[1]", "1", 1072);
//        costMatrixBuilder.addTransportDistance("[1]", "2", 164);
//        costMatrixBuilder.addTransportDistance("[2]", "1", 1072);
//        costMatrixBuilder.addTransportDistance("[2]", "2", 164);
//        costMatrixBuilder.addTransportDistance("1", "2", 617);
        Map<String, Map<String, Integer>> cost_matrices = (Map<String, Map<String, Integer>>) pojo.getCost_matrices();

        cost_matrices.forEach((s, stringStringMap) -> stringStringMap.forEach((k, v) -> {
            costMatrixBuilder.addTransportDistance(s, k, v);
            costMatrixBuilder.addTransportTime(s, k, v);
        }));

        VehicleRoutingTransportCosts costMatrix = costMatrixBuilder.build();


        VehicleRoutingProblem vrp = VehicleRoutingProblem.Builder.newInstance().setFleetSize(VehicleRoutingProblem.FleetSize.INFINITE).setRoutingCost(costMatrix)
                .addVehicle(vehicle1).addVehicle(vehicle2).addJob(shipment1).addJob(shipment2).build();

        VehicleRoutingAlgorithm vra = Jsprit.createAlgorithm(vrp);

        Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();
        VehicleRoutingProblemSolution vehicleRoutingProblemSolution = Solutions.bestOf(solutions);
        vehicleRoutingProblemSolution.getRoutes().stream().findFirst().orElse(null).getTourActivities().getActivities().forEach(t -> {
            DecimalFormat decimalFormat = new DecimalFormat("#");
            decimalFormat.setMaximumFractionDigits(10);
            System.out.println(decimalFormat.format((Double) t.getArrTime()));
        });
        return null;

    }

    private VehicleImpl.Builder createVehicle(Vehicles vehicles) {
        VehicleImpl.Builder vehicleBuilder = VehicleImpl.Builder.newInstance("vehicle");
        vehicleBuilder.setStartLocation(Location.newInstance(vehicles.getStart_address().getLocation_id()));
        return vehicleBuilder;
    }

    private Location loc(String id, Coordinate coordinate) {
        return Location.Builder.newInstance().setId(id).setCoordinate(coordinate).build();
    }
}
