package com.karash.DTO;

public class Shipments {
    private String id;
    private String[] requiredSkills;
    private Pickup pickup;
    private String[] allowedVehicles;
    private String name;
    private Delivery delivery;
    private Integer[] size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String[] requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Pickup getPickup() {
        return pickup;
    }

    public void setPickup(Pickup pickup) {
        this.pickup = pickup;
    }

    public String[] getAllowedVehicles() {
        return allowedVehicles;
    }

    public void setAllowedVehicles(String[] allowedVehicles) {
        this.allowedVehicles = allowedVehicles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Integer[] getSize() {
        return size;
    }

    public void setSize(Integer[] size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", requiredSkills = " + requiredSkills + ", pickup = " + pickup + ", allowedVehicles = " + allowedVehicles + ", name = " + name + ", delivery = " + delivery + ", size = " + size + "]";
    }
}
