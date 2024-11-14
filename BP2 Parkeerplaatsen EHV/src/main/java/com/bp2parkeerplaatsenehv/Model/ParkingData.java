package com.bp2parkeerplaatsenehv.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ParkingData {
    @SerializedName("results")
    public List<ParkingObject> ParkingObjects;

    public ParkingData() {}

    // Getter methods
    public List<ParkingObject> getParkingObjects() {
        return ParkingObjects;
    }

    // Setter methods
    public void setParkingObjects(List<ParkingObject> parkingObjects) {
        this.ParkingObjects = parkingObjects;
    }
}


