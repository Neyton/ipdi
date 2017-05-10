package com.karash.DTO;

public class Vehicles {
    private String[] skills;
    private String latestEnd;
    private Start_address startAddress;
    private Boolean returnToDepot;
    private String vehicleId;
    private String earliestStart;
    private String typeId;

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String getLatestEnd() {
        return latestEnd;
    }

    public void setLatestEnd(String latestEnd) {
        this.latestEnd = latestEnd;
    }

    public Start_address getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(Start_address startAddress) {
        this.startAddress = startAddress;
    }

    public Boolean getReturnToDepot() {
        return returnToDepot;
    }

    public void setReturnToDepot(Boolean returnToDepot) {
        this.returnToDepot = returnToDepot;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getEarliestStart() {
        return earliestStart;
    }

    public void setEarliestStart(String earliestStart) {
        this.earliestStart = earliestStart;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "ClassPojo [skills = " + skills + ", latestEnd = " + latestEnd + ", startAddress = " + startAddress + ", returnToDepot = " + returnToDepot + ", vehicleId = " + vehicleId + ", earliestStart = " + earliestStart + ", typeId = " + typeId + "]";
    }
}
