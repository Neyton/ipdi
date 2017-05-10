package com.karash.DTO;

public class Pickup {
    private Time_windows[] timeWindows;

    private String duration;

    private Address address;

    public Time_windows[] getTimeWindows() {
        return timeWindows;
    }

    public void setTimeWindows(Time_windows[] timeWindows) {
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
