package com.example.btlandroidav.response.tuple;

import com.google.gson.annotations.SerializedName;

public class Unit <T, U>{
    @SerializedName("total")
    private final T first;
    @SerializedName("items")
    private final U second;

    public Unit(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}
