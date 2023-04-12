package com.example.btlandroidav.response;

import com.google.gson.annotations.SerializedName;

public class GateHistory {
    @SerializedName("Id")
    private Integer id;
    @SerializedName("CheckInDate")
    private String checkInDate;
    @SerializedName("CheckOutDate")
    private String checkOutDate;
    @SerializedName("NumberPlate")
    private String numberPlate;
    @SerializedName("UserId")
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
