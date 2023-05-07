package com.example.btlandroidav.networks;

import com.example.btlandroidav.response.DateAndYear;
import com.example.btlandroidav.response.TransactionHistory;
import com.example.btlandroidav.response.tuple.Triplet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TransactionApi {
    @GET("transactions")
    Call<Triplet<List<DateAndYear>, Integer, String>> getAllTransactionByUserId(@Query("user_id") int user_id, @Query("description") String description);

    @GET("transactions/details")
    Call<Triplet<List<TransactionHistory>, Integer, String>> getDetailTransaction(@Query("user_id") int id, @Query("month") int month, @Query("year") int year, @Query("description") String description);

}
