
package com.karash.DTO;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "vehicle_types",
    "vehicles",
    "services",
    "shipments",
    "cost_matrices"
})
public class Dummy {

    @JsonProperty("vehicle_types")
    private List<VehicleType> vehicleTypes = null;
    @JsonProperty("vehicles")
    private List<Vehicle> vehicles = null;
    @JsonProperty("services")
    private List<Service> services = null;
    @JsonProperty("shipments")
    private List<Shipment> shipments = null;
    @JsonProperty("cost_matrices")
    private CostMatrices costMatrices;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("vehicle_types")
    public List<VehicleType> getVehicleTypes() {
        return vehicleTypes;
    }

    @JsonProperty("vehicle_types")
    public void setVehicleTypes(List<VehicleType> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    @JsonProperty("vehicles")
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    @JsonProperty("vehicles")
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @JsonProperty("services")
    public List<Service> getServices() {
        return services;
    }

    @JsonProperty("services")
    public void setServices(List<Service> services) {
        this.services = services;
    }

    @JsonProperty("shipments")
    public List<Shipment> getShipments() {
        return shipments;
    }

    @JsonProperty("shipments")
    public void setShipments(List<Shipment> shipments) {
        this.shipments = shipments;
    }

    @JsonProperty("cost_matrices")
    public CostMatrices getCostMatrices() {
        return costMatrices;
    }

    @JsonProperty("cost_matrices")
    public void setCostMatrices(CostMatrices costMatrices) {
        this.costMatrices = costMatrices;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
