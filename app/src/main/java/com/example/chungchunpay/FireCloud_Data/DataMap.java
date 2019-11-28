package com.example.chungchunpay.FireCloud_Data;

public class DataMap {
    String name, MungMuiName;
    double latitude,longitude;

    DataMap(){ }

    DataMap(String name, double latitude, double longitude, String MungMuiName){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.MungMuiName = MungMuiName;
    }

    public String getMungMuiName() {
        return MungMuiName;
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
