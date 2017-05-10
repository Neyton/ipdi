package com.karash.DTO;

public class Delivery {
    private TimeWindows[] time_windows;

    private String duration;

    private Address address;

    public TimeWindows[] getTime_windows() {
        return time_windows;
    }

    public void setTime_windows(TimeWindows[] time_windows) {
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
