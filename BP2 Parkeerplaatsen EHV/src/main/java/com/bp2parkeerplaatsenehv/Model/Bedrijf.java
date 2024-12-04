package com.bp2parkeerplaatsenehv.Model;
/**
 * The Bedrijf class represents a company customer, which is a specific type of Klant.
 * It includes additional attributes such as the company name and the Chamber of Commerce number.
 */
public class Bedrijf extends Klant {
    // The Chamber of Commerce number of the company
    private String kvkNumber;
    /**
     * Constructor to initialize a Bedrijf object with company details, name, and license plate number.
     * @param kvkNumber The Chamber of Commerce number of the company
     * @param naam The name of the customer
     * @param kenteken The license plate number of the customer's vehicle
     */
    public Bedrijf(String companyName, String kvkNumber, String naam, String kenteken) {
        super(naam, kenteken);
        this.kvkNumber = kvkNumber;
    }

    /**
     * Gets the Chamber of Commerce number of the company.
     * @return The Chamber of Commerce number
     */
    public String getKvkNumber() {
        return kvkNumber;
    }
    /**
     * Sets the Chamber of Commerce number of the company.
     * @param kvkNumber The new Chamber of Commerce number
     */
    public void setKvkNumber(String kvkNumber) {
        this.kvkNumber = kvkNumber;
    }
}