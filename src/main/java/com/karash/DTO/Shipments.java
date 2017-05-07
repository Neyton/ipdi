package com.karash.DTO;

public class Shipments {
    private String id;

    private String[] required_skills;

    private Pickup pickup;

    private String[] allowed_vehicles;

    private String name;

    private Delivery delivery;

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

    public Pickup getPickup() {
        return pickup;
    }

    public void setPickup(Pickup pickup) {
        this.pickup = pickup;
    }

    public String[] getAllowed_vehicles() {
        return allowed_vehicles;
    }

    public void setAllowed_vehicles(String[] allowed_vehicles) {
        this.allowed_vehicles = allowed_vehicles;
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

    public String[] getSize() {
        return size;
    }

    public void setSize(String[] size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", required_skills = " + required_skills + ", pickup = " + pickup + ", allowed_vehicles = " + allowed_vehicles + ", name = " + name + ", delivery = " + delivery + ", size = " + size + "]";
    }
}
