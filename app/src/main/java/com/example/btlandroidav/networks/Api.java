package com.example.btlandroidav.networks;

public interface Api extends UserApi, GateHistoryApi, PlateApi, TransactionApi, NotificationApi{
    String BASE_URL = "http://192.168.1.7:8000/api/v1/";
}