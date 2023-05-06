package com.example.btlandroidav.response;

import com.google.gson.annotations.SerializedName;

public class TransactionHistory {
    @SerializedName("UserId")
    public int userId;
    @SerializedName("Coin")
    public double coin;
    @SerializedName("Description")
    public String description;
    @SerializedName("CreatedDate")
    public String createdDate;
    @SerializedName("Id")
    public int id;
    @SerializedName("LastCoin")
    public double lastCoin;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLastCoin() {
        return lastCoin;
    }

    public void setLastCoin(long lastCoin) {
        this.lastCoin = lastCoin;
    }
}
