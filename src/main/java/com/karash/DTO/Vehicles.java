package com.karash.DTO;

public class Vehicles {
    private String[] skills;

    private String latest_end;

    private Start_address start_address;

    private String return_to_depot;

    private String vehicle_id;

    private String earliest_start;

    private String type_id;

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String getLatest_end() {
        return latest_end;
    }

    public void setLatest_end(String latest_end) {
        this.latest_end = latest_end;
    }

    public Start_address getStart_address() {
        return start_address;
    }

    public void setStart_address(Start_address start_address) {
        this.start_address = start_address;
    }

    public String getReturn_to_depot() {
        return return_to_depot;
    }

    public void setReturn_to_depot(String return_to_depot) {
        this.return_to_depot = return_to_depot;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getEarliest_start() {
        return earliest_start;
    }

    public void setEarliest_start(String earliest_start) {
        this.earliest_start = earliest_start;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "ClassPojo [skills = " + skills + ", latest_end = " + latest_end + ", start_address = " + start_address + ", return_to_depot = " + return_to_depot + ", vehicle_id = " + vehicle_id + ", earliest_start = " + earliest_start + ", type_id = " + type_id + "]";
    }
}
