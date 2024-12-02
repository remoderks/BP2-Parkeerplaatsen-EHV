package com.bp2parkeerplaatsenehv.Model;

import com.google.gson.annotations.SerializedName;

public class ParkingObject {
    public ParkingObject(int objectid, String straat, String type_en_merk) {
        this.objectId = objectid;
        this.straat = straat;
        this.typeEnMerk = type_en_merk;
    }

    // @SerializedName annotation is used to map the JSON keys to the Java fields
    @SerializedName("objectid")
    public Integer objectId;
    @SerializedName("straat")
    public String straat;
    @SerializedName("type_en_merk")
    public String typeEnMerk;
    @SerializedName("geo_point_2d")
    public GeoPoint2d geoPoint;

    // Getter methods
    public Integer getObjectId() {
        return objectId;
    }
    public String getStraat() {
        return straat;
    }
    public String getTypeEnMerk() {
        return typeEnMerk;
    }
    public GeoPoint2d getGeoPoint() {
        return geoPoint;
    }

    // Setter methods
    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }
    public void setStraat(String straat) {
        this.straat = straat;
    }
    public void setTypeEnMerk(String typeEnMerk) {
        this.typeEnMerk = typeEnMerk;
    }
    public void setGeoPoint(GeoPoint2d geoPoint) {
        this.geoPoint = geoPoint;
    }
}