package com.bp2parkeerplaatsenehv.Model;

public class Reserveringsafspraken {
    private String kenteken;
    private String objectID;
    private String tijdslot;
    private String datum;

    // Constructor
    public Reserveringsafspraken(String kenteken, String objectID, String tijdslot, String datum) {
        this.kenteken = kenteken;
        this.objectID = objectID;
        this.tijdslot = tijdslot;
        this.datum = datum;
    }

    // Getter methods
    public String getKenteken() {
        return kenteken;
    }
    public String getObjectID() {
        return objectID;
    }
    public String getTijdslot() {
        return tijdslot;
    }
    public String getDatum() {
        return datum;
    }

    // Setter methods
    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }
    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }
    public void setTijdslot(String tijdslot) {
        this.tijdslot = tijdslot;
    }
    public void setDatum(String datum) {
        this.datum = datum;
    }
}