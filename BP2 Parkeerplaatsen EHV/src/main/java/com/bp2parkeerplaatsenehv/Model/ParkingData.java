package com.bp2parkeerplaatsenehv.Model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ParkingData {
    @SerializedName("results")
    public List<ParkingObject> parkingObjects = new ArrayList<>(); // Initialize as empty list
    public List<ParkingObject> getParkingObjects() {
        return parkingObjects;
    }
    public void setParkingObjects(List<ParkingObject> parkingObjects) {
        this.parkingObjects = parkingObjects;
    }
}
