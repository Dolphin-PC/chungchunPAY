package com.example.chungchunpay.FireCloud_Data;

public class DataMap {
    String name;
    double latitude,longitude;

    DataMap(){ }

    DataMap(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
