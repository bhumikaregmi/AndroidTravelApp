package com.example.yourdestination;

public class Places {

    public String id;
    public String placeName;
    public String placeDescription;
    public String imgName;
    public int isFeatured;
    public GpsLocationModel gpsLocationModel;

    @Override
    public String toString() {
        return "Places{" +
                "id='" + id + '\'' +
                ", placeName='" + placeName + '\'' +
                ", placeDescription='" + placeDescription + '\'' +
                ", imgName='" + imgName + '\'' +
                ", isFeatured=" + isFeatured +
                ", gpsLocationModel=" + gpsLocationModel +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(int isFeatured) {
        this.isFeatured = isFeatured;
    }

    public GpsLocationModel getGpsLocationModel() {
        return gpsLocationModel;
    }

    public void setGpsLocationModel(GpsLocationModel gpsLocationModel) {
        this.gpsLocationModel = gpsLocationModel;
    }
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getPlaceName() {
//        return placeName;
//    }
//
//    public void setPlaceName(String placeName) {
//        this.placeName = placeName;
//    }
//
//    public String getPlaceDescription() {
//        return placeDescription;
//    }
//
//    public void setPlaceDescription(String placeDescription) {
//        this.placeDescription = placeDescription;
//    }
//
//    public String getImgName() {
//        return imgName;
//    }
//
//    public void setImgName(String imgName) {
//        this.imgName = imgName;
//    }
//
//    public int getIsFeatured() {
//        return isFeatured;
//    }
//
//    public void setIsFeatured(int isFeatured) {
//        this.isFeatured = isFeatured;
//    }
//
//    public GpsLocationModel getLocation() {
//        return gpsLocationModel;
//    }
//
//    public void setLocation(GpsLocationModel gpsLocationModel) {
//        this.gpsLocationModel = gpsLocationModel;
//    }

    public Places(String id, String placeName,
                  String placeDescription, String imgName,
                  int isFeatured, GpsLocationModel gpsLocationModel) {
        this.id = id;
        this.placeName = placeName;
        this.placeDescription = placeDescription;
        this.imgName = imgName;
        this.isFeatured = isFeatured;
        this.gpsLocationModel = gpsLocationModel;
    }
}
