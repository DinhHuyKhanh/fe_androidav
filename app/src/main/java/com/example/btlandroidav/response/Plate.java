package com.example.btlandroidav.response;

import com.google.gson.annotations.SerializedName;

public class Plate {
    @SerializedName("Id")
    private Integer id;
    @SerializedName("UserId")
    private Integer userId;
    @SerializedName("NumberPlate")
    private String numberPlate;

    public Plate(Integer id, Integer userId, String numberPlate) {
        this.id = id;
        this.userId = userId;
        this.numberPlate = numberPlate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getNumberPlate() {
        return numberPlate;
    }
}
