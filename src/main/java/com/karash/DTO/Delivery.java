
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
    "address",
    "duration",
    "time_windows"
})
public class Delivery {

    @JsonProperty("address")
    private Address__ address;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("time_windows")
    private List<TimeWindow__> timeWindows = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("address")
    public Address__ getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address__ address) {
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

    @JsonProperty("time_windows")
    public List<TimeWindow__> getTimeWindows() {
        return timeWindows;
    }

    @JsonProperty("time_windows")
    public void setTimeWindows(List<TimeWindow__> timeWindows) {
        this.timeWindows = timeWindows;
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
