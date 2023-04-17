package com.example.btlandroidav.networks;

public interface Api extends UserApi, GateHistoryApi, PlateApi{
    String BASE_URL = "http://192.168.1.15:8000/api/v1/";
}