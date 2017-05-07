
package com.karash.DTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "pickup",
    "delivery",
    "size",
    "required_skills",
    "allowed_vehicles"
})
public class Shipment {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pickup")
    private Pickup pickup;
    @JsonProperty("delivery")
    private Delivery delivery;
    @JsonProperty("size")
    private List<Integer> size = null;
    @JsonProperty("required_skills")
    private List<Object> requiredSkills = null;
    @JsonProperty("allowed_vehicles")
    private List<Object> allowedVehicles = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("pickup")
    public Pickup getPickup() {
        return pickup;
    }

    @JsonProperty("pickup")
    public void setPickup(Pickup pickup) {
        this.pickup = pickup;
    }

    @JsonProperty("delivery")
    public Delivery getDelivery() {
        return delivery;
    }

    @JsonProperty("delivery")
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @JsonProperty("size")
    public List<Integer> getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(List<Integer> size) {
        this.size = size;
    }

    @JsonProperty("required_skills")
    public List<Object> getRequiredSkills() {
        return requiredSkills;
    }

    @JsonProperty("required_skills")
    public void setRequiredSkills(List<Object> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    @JsonProperty("allowed_vehicles")
    public List<Object> getAllowedVehicles() {
        return allowedVehicles;
    }

    @JsonProperty("allowed_vehicles")
    public void setAllowedVehicles(List<Object> allowedVehicles) {
        this.allowedVehicles = allowedVehicles;
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
