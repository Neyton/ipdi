package com.karash.DTO;

public class Time_windows {
    private String earliest;

    private Double latest;

    public String getEarliest() {
        return earliest;
    }

    public void setEarliest(String earliest) {
        this.earliest = earliest;
    }

    public Double getLatest() {
        return latest;
    }

    public void setLatest(Double latest) {
        this.latest = latest;
    }

    @Override
    public String toString() {
        return "ClassPojo [earliest = " + earliest + ", latest = " + latest + "]";
    }
}
