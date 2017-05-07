
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
    "type",
    "name",
    "address",
    "duration",
    "size",
    "time_windows",
    "required_skills",
    "allowed_vehicles"
})
public class Service {

    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("size")
    private List<Integer> size = null;
    @JsonProperty("time_windows")
    private List<TimeWindow> timeWindows = null;
    @JsonProperty("required_skills")
    private List<Object> requiredSkills = null;
    @JsonProperty("allowed_vehicles")
    private List<String> allowedVehicles = null;
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

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("duration")
    public Integer getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @JsonProperty("size")
    public List<Integer> getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(List<Integer> size) {
        this.size = size;
    }

    @JsonProperty("time_windows")
    public List<TimeWindow> getTimeWindows() {
        return timeWindows;
    }

    @JsonProperty("time_windows")
    public void setTimeWindows(List<TimeWindow> timeWindows) {
        this.timeWindows = timeWindows;
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
    public List<String> getAllowedVehicles() {
        return allowedVehicles;
    }

    @JsonProperty("allowed_vehicles")
    public void setAllowedVehicles(List<String> allowedVehicles) {
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
