package com.example.btlandroidav.networks;

import com.example.btlandroidav.response.NotificationHistory;
import com.example.btlandroidav.response.tuple.Triplet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NotificationApi {
    @GET("notify/{user_id}")
    Call<Triplet<List<NotificationHistory>, Integer, String>> get_all_notification(@Path("user_id") int user_id);
}
