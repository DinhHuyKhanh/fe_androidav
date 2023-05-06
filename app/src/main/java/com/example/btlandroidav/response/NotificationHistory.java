package com.example.btlandroidav.response;

import com.google.gson.annotations.SerializedName;

public class NotificationHistory {
    @SerializedName("UserId")
    public int userId;
    @SerializedName("Message")
    public String message;
    @SerializedName("CreatedDate")
    public String createdDate;
    @SerializedName("Id")
    public int id;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
