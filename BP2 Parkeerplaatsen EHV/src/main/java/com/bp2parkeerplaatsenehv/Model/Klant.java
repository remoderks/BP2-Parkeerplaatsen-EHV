package com.bp2parkeerplaatsenehv.Model;

/**
 * The Klant class represents a customer with a name and a license plate number.
 * It serves as a parent class for other types of customers such as individuals and companies.
 */
public class Klant {
    // The name of the customer
    private String naam;
    // The license plate number of the customer's vehicle
    private String kenteken;
    /**
     * Constructor to initialize a Klant object with a name and a license plate number.
     * @param naam The name of the customer
     * @param kenteken The license plate number of the customer's vehicle
     */
    public Klant(String naam, String kenteken) {
        this.naam = naam;
        this.kenteken = kenteken;
    }
    /**
     * Gets the name of the customer.
     * @return The name of the customer
     */
    public String getNaam() {
        return naam;
    }
    /**
     * Sets the name of the customer.
     * @param naam The new name of the customer
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }
    /**
     * Gets the license plate number of the customer's vehicle.
     * @return The license plate number
     */
    public String getKenteken() {
        return kenteken;
    }
    /**
     * Sets the license plate number of the customer's vehicle.
     * @param kenteken The new license plate number
     */
    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }
}