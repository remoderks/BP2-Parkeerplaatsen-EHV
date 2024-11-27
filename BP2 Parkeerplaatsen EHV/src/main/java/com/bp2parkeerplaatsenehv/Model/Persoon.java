package com.bp2parkeerplaatsenehv.Model;

public class Persoon extends Klant {
    private String email;

    // constructor
    public Persoon(String naam, String kenteken) {
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
