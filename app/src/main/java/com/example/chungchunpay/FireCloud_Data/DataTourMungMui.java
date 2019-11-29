package com.example.chungchunpay.FireCloud_Data;

public class DataTourMungMui {
    String PointName, MungMuiName;

    DataTourMungMui(){
    }

    DataTourMungMui(String PointName, String MungMuiName){
        this.MungMuiName = MungMuiName;
        this.PointName = PointName;
    }

    public String getMungMuiName() {
        return MungMuiName;
    }

    public String getPointName() {
        return PointName;
    }
}
