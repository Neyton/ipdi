
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
    "vehicle_id",
    "start_address",
    "type_id",
    "return_to_depot",
    "skills",
    "earliest_start",
    "latest_end"
})
public class Vehicle {

    @JsonProperty("vehicle_id")
    private String vehicleId;
    @JsonProperty("start_address")
    private StartAddress startAddress;
    @JsonProperty("type_id")
    private String typeId;
    @JsonProperty("return_to_depot")
    private Boolean returnToDepot;
    @JsonProperty("skills")
    private List<String> skills = null;
    @JsonProperty("earliest_start")
    private Integer earliestStart;
    @JsonProperty("latest_end")
    private Integer latestEnd;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("vehicle_id")
    public String getVehicleId() {
        return vehicleId;
    }

    @JsonProperty("vehicle_id")
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @JsonProperty("start_address")
    public StartAddress getStartAddress() {
        return startAddress;
    }

    @JsonProperty("start_address")
    public void setStartAddress(StartAddress startAddress) {
        this.startAddress = startAddress;
    }

    @JsonProperty("type_id")
    public String getTypeId() {
        return typeId;
    }

    @JsonProperty("type_id")
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @JsonProperty("return_to_depot")
    public Boolean getReturnToDepot() {
        return returnToDepot;
    }

    @JsonProperty("return_to_depot")
    public void setReturnToDepot(Boolean returnToDepot) {
        this.returnToDepot = returnToDepot;
    }

    @JsonProperty("skills")
    public List<String> getSkills() {
        return skills;
    }

    @JsonProperty("skills")
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @JsonProperty("earliest_start")
    public Integer getEarliestStart() {
        return earliestStart;
    }

    @JsonProperty("earliest_start")
    public void setEarliestStart(Integer earliestStart) {
        this.earliestStart = earliestStart;
    }

    @JsonProperty("latest_end")
    public Integer getLatestEnd() {
        return latestEnd;
    }

    @JsonProperty("latest_end")
    public void setLatestEnd(Integer latestEnd) {
        this.latestEnd = latestEnd;
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
