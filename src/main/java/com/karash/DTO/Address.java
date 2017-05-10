package com.karash.DTO;

public class Address {
    private Double lon;
    private Double lat;
    private String name;
    private String locationId;

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

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "ClassPojo [lon = " + lon + ", name = " + name + ", lat = " + lat + ", locationId = " + locationId + "]";
    }
}
