package com.bp2parkeerplaatsenehv.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

/**
 * The ParkingData class represents the data structure for parking information.
 * It contains a list of ParkingObject instances, which are deserialized from JSON using Gson.
 */
public class ParkingData {
    // The list of parking objects, mapped from the JSON key "results"
    @SerializedName("results")
    public List<ParkingObject> parkingObjects;
    /**
     * Gets the list of parking objects.
     * @return The list of parking objects
     */
    public List<ParkingObject> getParkingObjects() {
        return parkingObjects;
    }
    /**
     * Sets the list of parking objects.
     * @param parkingObjects The new list of parking objects
     */
    public void setParkingObjects(List<ParkingObject> parkingObjects) {
        this.parkingObjects = parkingObjects;
    }
}