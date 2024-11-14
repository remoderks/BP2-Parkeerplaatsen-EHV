package com.bp2parkeerplaatsenehv.Model;

import com.google.gson.annotations.SerializedName;

public class ParkingObject {
    @SerializedName("objectid")
    public String ObjectId;

    @SerializedName("straat")
    public String Straat;

    @SerializedName("type_en_merk")
    public String TypeEnMerk;

    @SerializedName("geo_point_2d")
    public GeoPoint2d GeoPoint;

    public ParkingObject() {}

    // Getter methods
    public String getObjectId() {
        return ObjectId;
    }

    public String getStraat() {
        return Straat;
    }

    public String getTypeEnMerk() {
        return TypeEnMerk;
    }

    public GeoPoint2d getGeoPoint() {
        return GeoPoint;
    }

    // Setter methods
    public void setObjectId(String objectId) {
        this.ObjectId = objectId;
    }

    public void setStraat(String straat) {
        this.Straat = straat;
    }

    public void setTypeEnMerk(String typeEnMerk) {
        this.TypeEnMerk = typeEnMerk;
    }

    public void setGeoPoint(GeoPoint2d geoPoint) {
        this.GeoPoint = geoPoint;
    }
}