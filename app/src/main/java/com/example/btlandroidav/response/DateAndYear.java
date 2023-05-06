package com.example.btlandroidav.response;

import com.google.gson.annotations.SerializedName;

public class DateAndYear {
    @SerializedName("month")
    private String month;
    @SerializedName("year")
    private String year;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
