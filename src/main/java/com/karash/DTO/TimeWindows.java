package com.karash.DTO;

public class TimeWindows {
    private String earliest;

    private String latest;

    public String getEarliest() {
        return earliest;
    }

    public void setEarliest(String earliest) {
        this.earliest = earliest;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    @Override
    public String toString() {
        return "ClassPojo [earliest = " + earliest + ", latest = " + latest + "]";
    }
}
