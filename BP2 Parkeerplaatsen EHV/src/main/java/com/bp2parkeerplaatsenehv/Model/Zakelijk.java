package com.bp2parkeerplaatsenehv.Model;
/**
 * The Bedrijf class represents a company customer, which is a specific type of Klant.
 * It includes additional attributes such as the company name and the Chamber of Commerce number.
 */
public class Zakelijk extends Klant {
    // The Chamber of Commerce number of the company
    private Integer kvkNumber;
    /**
     * Constructor to initialize a Bedrijfsobject with company details, name, and license plate number.
     * @param kvkNumber The Chamber of Commerce number of the company
     * @param naam The name of the customer
     * @param kenteken The license plate number of the customer's vehicle
     */
    public Zakelijk(Integer kvkNumber, String naam, String kenteken) {
        super(naam, kenteken);
        this.kvkNumber = kvkNumber;
    }

    /**
     * Gets the Chamber of Commerce number of the company.
     * @return The Chamber of Commerce number
     */
    public Integer getKvkNumber() {
        return kvkNumber;
    }
    /**
     * Sets the Chamber of Commerce number of the company.
     * @param kvkNumber The new Chamber of Commerce number
     */
    public void setKvkNumber(Integer kvkNumber) {
        this.kvkNumber = kvkNumber;
    }
}