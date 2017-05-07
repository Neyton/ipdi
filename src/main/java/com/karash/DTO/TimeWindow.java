
package com.karash.DTO;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "earliest",
    "latest"
})
public class TimeWindow {

    @JsonProperty("earliest")
    private Integer earliest;
    @JsonProperty("latest")
    private Integer latest;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("earliest")
    public Integer getEarliest() {
        return earliest;
    }

    @JsonProperty("earliest")
    public void setEarliest(Integer earliest) {
        this.earliest = earliest;
    }

    @JsonProperty("latest")
    public Integer getLatest() {
        return latest;
    }

    @JsonProperty("latest")
    public void setLatest(Integer latest) {
        this.latest = latest;
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
