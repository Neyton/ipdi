package com.karash.DTO;

public class Pickup {
    private TimeWindows[] timeWindows;

    private String duration;

    private Address address;

    public TimeWindows[] getTimeWindows() {
        return timeWindows;
    }

    public void setTimeWindows(TimeWindows[] timeWindows) {
        this.timeWindows = timeWindows;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ClassPojo [timeWindows = " + timeWindows + ", duration = " + duration + ", address = " + address + "]";
    }
}
