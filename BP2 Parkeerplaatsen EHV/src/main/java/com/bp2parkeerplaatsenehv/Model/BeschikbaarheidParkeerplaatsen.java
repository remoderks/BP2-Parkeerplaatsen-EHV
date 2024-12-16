package com.bp2parkeerplaatsenehv.Model;

import java.sql.Date;
import java.sql.Time;

public class BeschikbaarheidParkeerplaatsen {
    private Time tijdslot;
    private Date datum;

    public BeschikbaarheidParkeerplaatsen(Time tijdslot, Date datum) {
        this.tijdslot = tijdslot;
        this.datum = datum;
    }
    // Getters
    public Time getTijdslot() {
        return tijdslot;
    }
    public Date getDatum() {
        return datum;
    }
    // Setters
    public void setTijdslot(Time tijdslot) {
        this.tijdslot = tijdslot;
    }
    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
