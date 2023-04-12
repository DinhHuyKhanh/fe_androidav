package com.example.btlandroidav.api;

import com.example.btlandroidav.request.EmailSchema;
import com.example.btlandroidav.request.LoginSchema;
import com.example.btlandroidav.request.PasswordResetSchema;
import com.example.btlandroidav.request.UpdatePasswordSchema;
import com.example.btlandroidav.response.Triplet;
import com.example.btlandroidav.response.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApi {
    @POST("email")
    Call<Triplet<String, Integer, String>> sendMail(@Body EmailSchema emailSchema);

    @PUT("users/forget_password")
    Call<Triplet<Integer, Integer, String>> resetPassword(@Body PasswordResetSchema passwordResetSchema);

    @POST("users/login")
    Call<Triplet<User, Integer, String>> login(@Body LoginSchema loginSchema);

    @GET("users/")
    Call<Triplet<List<User>, Integer, String>> get_all_user();

    @GET("users/{id}")
    Call<Triplet<User, Integer, String>> get_by_id(@Path("id") int id);

    @PUT("users/{id}/updatePassword")
    Call<Triplet<Integer, Integer, String>> updatePassword(@Path("id") int id, @Body UpdatePasswordSchema updatePasswordSchema);
}
