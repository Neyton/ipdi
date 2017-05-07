
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
    "type_id",
    "profile",
    "capacity",
    "speed_factor",
    "service_time_factor"
})
public class VehicleType {

    @JsonProperty("type_id")
    private String typeId;
    @JsonProperty("profile")
    private String profile;
    @JsonProperty("capacity")
    private List<Integer> capacity = null;
    @JsonProperty("speed_factor")
    private Double speedFactor;
    @JsonProperty("service_time_factor")
    private Double serviceTimeFactor;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type_id")
    public String getTypeId() {
        return typeId;
    }

    @JsonProperty("type_id")
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @JsonProperty("profile")
    public String getProfile() {
        return profile;
    }

    @JsonProperty("profile")
    public void setProfile(String profile) {
        this.profile = profile;
    }

    @JsonProperty("capacity")
    public List<Integer> getCapacity() {
        return capacity;
    }

    @JsonProperty("capacity")
    public void setCapacity(List<Integer> capacity) {
        this.capacity = capacity;
    }

    @JsonProperty("speed_factor")
    public Double getSpeedFactor() {
        return speedFactor;
    }

    @JsonProperty("speed_factor")
    public void setSpeedFactor(Double speedFactor) {
        this.speedFactor = speedFactor;
    }

    @JsonProperty("service_time_factor")
    public Double getServiceTimeFactor() {
        return serviceTimeFactor;
    }

    @JsonProperty("service_time_factor")
    public void setServiceTimeFactor(Double serviceTimeFactor) {
        this.serviceTimeFactor = serviceTimeFactor;
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
