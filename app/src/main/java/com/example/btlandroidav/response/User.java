package com.example.btlandroidav.response;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User {
    @SerializedName("Id")
    private Integer id;
    @SerializedName("FullName")
    private String FullName;
    @SerializedName("PhoneNumber")
    private String PhoneNumber;
    @SerializedName("Email")
    private String Email;
    @SerializedName("PersonalNumber")
    private String PersonalNumber;
    @SerializedName("Address")
    private String Address;
    @SerializedName("Coin")
    private double Coin;
    @SerializedName("BirthDay")
    private Date BirthDay;

    public String getFullName() {
        return FullName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public String getPersonalNumber() {
        return PersonalNumber;
    }

    public String getAddress() {
        return Address;
    }

    public double getCoin() {
        return Coin;
    }

    public Date getBirthDay() {
        return BirthDay;
    }

    public Integer getId(){
        return id;
    }

    public User() {}

    public User(Integer id, String fullName, Long coin) {
        this.id = id;
        FullName = fullName;
        Coin = coin;
    }
}
