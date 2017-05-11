package com.karash.DTO;

public class Services {
    private String id;
    private String[] required_skills;
    private Time_windows[] time_windows;
    private double duration;
    private String[] allowed_vehicles;
    private Address address;
    private String name;
    private String type;
    private String[] size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getRequired_skills() {
        return required_skills;
    }

    public void setRequired_skills(String[] required_skills) {
        this.required_skills = required_skills;
    }

    public Time_windows[] getTime_windows() {
        return time_windows;
    }

    public void setTime_windows(Time_windows[] time_windows) {
        this.time_windows = time_windows;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String[] getAllowed_vehicles() {
        return allowed_vehicles;
    }

    public void setAllowed_vehicles(String[] allowed_vehicles) {
        this.allowed_vehicles = allowed_vehicles;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getSize() {
        return size;
    }

    public void setSize(String[] size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", required_skills = " + required_skills + ", time_windows = " + time_windows + ", duration = " + duration + ", allowed_vehicles = " + allowed_vehicles + ", address = " + address + ", name = " + name + ", type = " + type + ", size = " + size + "]";
    }
}
