package com.example.chungchunpay.FireCloud_Data;

public class DataTourMungMui {
    String PointName, MungMuiName,ImageUrl;

    DataTourMungMui(){
    }

    DataTourMungMui(String PointName, String MungMuiName,String ImageUrl){
        this.MungMuiName = MungMuiName;
        this.PointName = PointName;
        this.ImageUrl = ImageUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getMungMuiName() {
        return MungMuiName;
    }

    public String getPointName() {
        return PointName;
    }
}
