package com.karash.DTO;

public class Vehicle_types {
    private String service_time_factor;

    private String speed_factor;

    private String[] capacity;

    private String type_id;

    private String profile;

    public String getService_time_factor() {
        return service_time_factor;
    }

    public void setService_time_factor(String service_time_factor) {
        this.service_time_factor = service_time_factor;
    }

    public String getSpeed_factor() {
        return speed_factor;
    }

    public void setSpeed_factor(String speed_factor) {
        this.speed_factor = speed_factor;
    }

    public String[] getCapacity() {
        return capacity;
    }

    public void setCapacity(String[] capacity) {
        this.capacity = capacity;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "ClassPojo [service_time_factor = " + service_time_factor + ", speed_factor = " + speed_factor + ", capacity = " + capacity + ", type_id = " + type_id + ", profile = " + profile + "]";
    }
}
