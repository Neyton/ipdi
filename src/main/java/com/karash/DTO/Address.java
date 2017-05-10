package com.karash.DTO;

public class Address {
    private Double lon;
    private String name;
    private Double lat;
    private String location_id;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
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
        return "ClassPojo [lon = " + lon + ", name = " + name + ", lat = " + lat + ", location_id = " + location_id + "]";
    }
}
