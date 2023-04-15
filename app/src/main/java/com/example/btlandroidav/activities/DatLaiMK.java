package com.example.btlandroidav.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btlandroidav.R;
import com.example.btlandroidav.networks.RetrofitClient;
import com.example.btlandroidav.request.PasswordResetSchema;
import com.example.btlandroidav.response.Triplet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatLaiMK extends AppCompatActivity {

    private EditText otp;
    private EditText password;
    private EditText password_xn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_lai_mk);

        init();
    }

    private void init(){
        otp = findViewById(R.id.dlmk_et_otp);
        password = findViewById(R.id.dlmk_et_newpass);
        password_xn = findViewById(R.id.dlmk_et_xacnhanpass);
    }

    public void on_click_dlmk_return(View view){
        Intent intent = new Intent();
        intent.setClass(view.getContext(), QuenMK.class);
        startActivity(intent);
    }

    public void on_click_dlmk_doimk(View view){
        String strOtp = otp.getText().toString();
        String strPassword = password.getText().toString();
        String strPasswordXN = password_xn.getText().toString();

        if(strPassword.equals(strPasswordXN)){
            PasswordResetSchema passwordResetSchema = new PasswordResetSchema(strOtp, strPassword);
            resetPassword(passwordResetSchema);
        }
        else
            Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp nhau", Toast.LENGTH_LONG).show();
    }

    public void resetPassword(PasswordResetSchema passwordResetSchema){
        Call<Triplet<Integer, Integer, String>> call = RetrofitClient.getInstance().getMyApi().resetPassword(passwordResetSchema);
        call.enqueue(new Callback<Triplet<Integer, Integer, String>>() {
            @Override
            public void onResponse(Call<Triplet<Integer, Integer, String>> call, Response<Triplet<Integer, Integer, String>> response) {
                Triplet<Integer, Integer, String> results = response.body();
                if(results.getSecond() != 0){
                    Toast.makeText(getApplicationContext(), "OTP not exist", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Reset password success", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Triplet<Integer, Integer, String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
}