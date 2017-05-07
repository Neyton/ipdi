package com.karash.DTO;

public class Pickup {
    private Time_windows[] time_windows;

    private String duration;

    private Address address;

    public Time_windows[] getTime_windows() {
        return time_windows;
    }

    public void setTime_windows(Time_windows[] time_windows) {
        this.time_windows = time_windows;
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
        return "ClassPojo [time_windows = " + time_windows + ", duration = " + duration + ", address = " + address + "]";
    }
}
