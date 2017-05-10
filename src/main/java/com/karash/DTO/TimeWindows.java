package com.karash.DTO;

public class TimeWindows {
    private double earliest;

    private double latest;

    public double getEarliest() {
        return earliest;
    }

    public void setEarliest(double earliest) {
        this.earliest = earliest;
    }

    public double getLatest() {
        return latest;
    }

    public void setLatest(double latest) {
        this.latest = latest;
    }

    @Override
    public String toString() {
        return "ClassPojo [earliest = " + earliest + ", latest = " + latest + "]";
    }
}
