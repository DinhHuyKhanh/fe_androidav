package com.example.btlandroidav.networks;

import com.example.btlandroidav.response.DateAndYear;
import com.example.btlandroidav.response.NotificationHistory;
import com.example.btlandroidav.response.TransactionHistory;
import com.example.btlandroidav.response.tuple.Triplet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api extends UserApi, GateHistoryApi, PlateApi{
    String BASE_URL = "http://192.168.1.102:8000/api/v1/";

    @GET("tran/{id}")
    Call<Triplet<List<DateAndYear>, Integer, String>> get_date_created(@Path("id") int id, @Query("act") String act);

    @GET("tran/")
    Call<Triplet<List<TransactionHistory>, Integer, String>> getDetailTransaction(@Query("id") int id, @Query("month") int month, @Query("year") int year, @Query("act") String act);

    @GET("notify/{user_id}")
    Call<Triplet<List<NotificationHistory>, Integer, String>> get_all_notification(@Path("user_id") int user_id);
}