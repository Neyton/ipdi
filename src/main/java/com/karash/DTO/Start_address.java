package com.karash.DTO;

public class Start_address {
    private String lon;

    private String lat;

    private String location_id;

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    @Override
    public String toString() {
        return "ClassPojo [lon = " + lon + ", lat = " + lat + ", location_id = " + location_id + "]";
    }
}
