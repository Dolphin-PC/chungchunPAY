package com.example.chungchunpay.FireCloud_Data;

public class DataUser {
    private String UserName, KakaoSerialNumber, ProfileUrl;
    private int Point;

    public DataUser(){}

    public DataUser(String UserName, String KakaoSerialNumber, String ProfileURL, int Point){
        this.UserName = UserName;
        this.KakaoSerialNumber = KakaoSerialNumber;
        this.ProfileUrl = ProfileURL;
        this.Point = Point;
    }

    public String getProfileURL() {
        return ProfileUrl;
    }

    public int getPoint() {
        return Point;
    }

    public String getID() {
        return KakaoSerialNumber;
    }

    public String getUserName() {
        return UserName;
    }
}
