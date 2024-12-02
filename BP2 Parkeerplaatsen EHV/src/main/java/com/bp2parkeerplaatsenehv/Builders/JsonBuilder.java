package com.bp2parkeerplaatsenehv.Builders;
import com.bp2parkeerplaatsenehv.Model.ParkingData;
import com.google.gson.Gson;

public class JsonBuilder {
    public final Gson gson;

    public JsonBuilder() {
        this.gson = new Gson();
    }

    public String buildJson(ParkingData data) {
        return gson.toJson(data);
    }
}