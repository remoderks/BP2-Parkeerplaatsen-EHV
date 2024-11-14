package com.bp2parkeerplaatsenehv.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeoPoint2d {
    @SerializedName("lon")
    public double longitude;

    @SerializedName("lat")
    public double latitude;

    public GeoPoint2d() {}

    // Getter methods
    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    // Setter methods
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}