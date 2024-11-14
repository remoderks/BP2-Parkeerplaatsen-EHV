package com.bp2parkeerplaatsenehv.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ParkingData {
    @SerializedName("results")
    public List<ParkingObject> parkingObjects;

    // Getter methods
    public List<ParkingObject> getParkingObjects() {
        return parkingObjects;
    }

    // Setter methods
    public void setParkingObjects(List<ParkingObject> parkingObjects) {
        this.parkingObjects = parkingObjects;
    }
}


