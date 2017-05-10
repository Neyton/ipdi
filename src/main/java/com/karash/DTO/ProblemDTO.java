package com.karash.DTO;

public class ProblemDTO {
    private Vehicle_types[] vehicle_types;
    private Services[] services;
    private Shipments[] shipments;
    private Vehicles[] vehicles;
    private Object cost_matrices;

    public Object getCost_matrices() {
        return cost_matrices;
    }

    public void setCost_matrices(Object cost_matrices) {
        this.cost_matrices = cost_matrices;
    }

    public Vehicle_types[] getVehicle_types() {
        return vehicle_types;
    }

    public void setVehicle_types(Vehicle_types[] vehicle_types) {
        this.vehicle_types = vehicle_types;
    }

    public Services[] getServices() {
        return services;
    }

    public void setServices(Services[] services) {
        this.services = services;
    }

    public Shipments[] getShipments() {
        return shipments;
    }

    public void setShipments(Shipments[] shipments) {
        this.shipments = shipments;
    }

    public Vehicles[] getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicles[] vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "ClassPojo [vehicle_types = " + vehicle_types + ", services = " + services + ", shipments = " + shipments + ", vehicles = " + vehicles + "]";
    }
}
