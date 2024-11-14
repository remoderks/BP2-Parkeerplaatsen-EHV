package com.bp2parkeerplaatsenehv.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeoPoint2d {
    @SerializedName("lon")
    public double Longitude;

    @SerializedName("lat")
    public double Latitude;

    public GeoPoint2d() {}

    // Getter methods
    public double getLongitude() {
        return Longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    // Setter methods
    public void setLongitude(double longitude) {
        this.Longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.Latitude = latitude;
    }
}