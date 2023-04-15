package com.example.btlandroidav.networks;

import com.example.btlandroidav.response.GateHistory;
import com.example.btlandroidav.response.Triplet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GateHistoryApi {
    @GET("gate_histories")
    Call<Triplet<List<GateHistory>, Integer, String>> gate_histories(@Query("id") int id);
}
