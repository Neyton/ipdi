package com.karash.DTO;

public class Start_address {
    private Double lon;
    private Double lat;
    private String locationId;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
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
        return "ClassPojo [lon = " + lon + ", lat = " + lat + ", locationId = " + locationId + "]";
    }
}
