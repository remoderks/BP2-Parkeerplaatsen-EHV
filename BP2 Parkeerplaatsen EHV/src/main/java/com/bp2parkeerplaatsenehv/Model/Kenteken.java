package com.bp2parkeerplaatsenehv.Model;

/**
 * The Kenteken class represents a vehicle's license plate.
 */
public class Kenteken {
    // The license plate number
    private String kenteken;
    /**
     * Constructor to initialize a Kenteken object with a license plate number.
     * @param kenteken The license plate number
     */
    public Kenteken(String kenteken) {
        this.kenteken = kenteken;
    }
    /**
     * Gets the license plate number.
     * @return The license plate number
     */
    public String getKenteken() {
        return kenteken;
    }
    /**
     * Sets the license plate number.
     * @param kenteken The new license plate number
     */
    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }
}