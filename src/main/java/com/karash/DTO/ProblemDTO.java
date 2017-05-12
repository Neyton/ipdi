package com.karash.DTO;

public class ProblemDTO {
    private Vehicle_types[] vehicle_types;
    private Services[] services;
    private Shipments[] shipments;
    private Vehicles[] vehicles;
    private Object cost_matrices;
    private Integer max_iterations;
    private Integer max_threads;
    private Integer noIterations;
    private Double variationCoefficientThreshold;

    public Integer getNoIterations() {
        return noIterations;
    }

    public void setNoIterations(Integer noIterations) {
        this.noIterations = noIterations;
    }

    public Double getVariationCoefficientThreshold() {
        return variationCoefficientThreshold;
    }

    public void setVariationCoefficientThreshold(Double variationCoefficientThreshold) {
        this.variationCoefficientThreshold = variationCoefficientThreshold;
    }

    public Integer getMax_threads() {
        return max_threads;
    }

    public void setMax_threads(Integer max_threads) {
        this.max_threads = max_threads;
    }

    public Integer getMax_iterations() {
        return max_iterations;
    }

    public void setMax_iterations(Integer max_iterations) {
        this.max_iterations = max_iterations;
    }

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
