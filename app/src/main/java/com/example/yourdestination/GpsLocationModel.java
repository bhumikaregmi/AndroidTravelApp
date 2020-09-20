package com.example.yourdestination;

public class GpsLocationModel {
    private double lat;
    private double log;

    @Override
    public String toString() {
        return "GpsLocationModel{" +
                "lat=" + lat +
                ", log=" + log +
                '}';
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }

    public GpsLocationModel(double lat, double log) {
        this.lat = lat;
        this.log = log;
    }
}
