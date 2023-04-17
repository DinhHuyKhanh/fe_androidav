package com.example.btlandroidav.networks;

import com.example.btlandroidav.response.Plate;
import com.example.btlandroidav.response.tuple.Triplet;
import com.example.btlandroidav.response.tuple.Unit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlateApi {
    @GET("plates")
    Call<Triplet<Unit<Integer, List<Plate>>, Integer, String>> get_all_plate(@Query("user_id") Integer user_id);
}
