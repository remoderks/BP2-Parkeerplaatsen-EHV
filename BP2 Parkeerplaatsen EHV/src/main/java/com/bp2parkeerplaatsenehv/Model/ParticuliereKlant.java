package com.bp2parkeerplaatsenehv.Model;

public class ParticuliereKlant extends Klant {
    private String email;

    // constructor
    public ParticuliereKlant(String naam, String kenteken, String email) {
        super(naam, kenteken);
        this.email = naam;
    }
    // getter
    public String getEmail() {
        return email;
    }
    // setter
    public void setEmail(String email) {
        this.email = email;
    }
}
