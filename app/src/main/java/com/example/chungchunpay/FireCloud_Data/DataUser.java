package com.example.chungchunpay.FireCloud_Data;

public class DataUser {
    private String UserName, KakaoSerialNumber, ProfileUrl,Gender,Hobby,Age;
    private int Point;

    public DataUser(){}

    public DataUser(String UserName, String KakaoSerialNumber, String ProfileURL, int Point,String Gender, String Hobby, String Age){
        this.UserName = UserName;
        this.KakaoSerialNumber = KakaoSerialNumber;
        this.ProfileUrl = ProfileURL;
        this.Point = Point;
        this.Age = Age;
        this.Gender = Gender;
        this.Hobby = Hobby;
    }

    public void setPoint(int point) {
        Point = point;
    }

    public void setAge(String age) {
        Age = age;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setHobby(String hobby) {
        Hobby = hobby;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getAge() {
        return Age;
    }

    public String getHobby() {
        return Hobby;
    }

    public String getGender() {
        return Gender;
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
