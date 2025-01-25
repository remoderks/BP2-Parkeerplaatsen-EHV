package com.bp2parkeerplaatsenehv.Builders;
import com.bp2parkeerplaatsenehv.Model.ParkingObject;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingObjectFilter {
    private final List<ParkingObject> parkingObjects;

    public ParkingObjectFilter(List<ParkingObject> parkingObjects) {
        this.parkingObjects = parkingObjects;
    }

    public List<ParkingObject> filter(Integer objectId, String straat, String typeEnMerk) {
        return parkingObjects.stream()
                .filter(po -> (objectId == null || po.getObjectId().equals(objectId)) &&
                        (straat == null || po.getStraat().equalsIgnoreCase(straat)) &&
                        (typeEnMerk == null || po.getTypeEnMerk().equalsIgnoreCase(typeEnMerk)))
                .collect(Collectors.toList());
    }
}

