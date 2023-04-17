package com.example.btlandroidav.response.tuple;

import com.google.gson.annotations.SerializedName;

public class Triplet<T, U, V> {

    @SerializedName("data")
    private final T first;
    @SerializedName("code")
    private final U second;
    @SerializedName("message")
    private final V third;

    public Triplet(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() { return first; }
    public U getSecond() { return second; }
    public V getThird() { return third; }
}
