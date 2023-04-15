package com.example.btlandroidav.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btlandroidav.R;
import com.example.btlandroidav.networks.RetrofitClient;
import com.example.btlandroidav.request.EmailSchema;
import com.example.btlandroidav.response.Triplet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuenMK extends AppCompatActivity {
    EditText et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mk);

        init();
    }

    private void init(){
        et_email = findViewById(R.id.qmk_et_email);
    }

    public void on_click_qmk_huy(View view) {
        Intent intent = new Intent();
        intent.setClass(view.getContext(), MainActivity.class);
        startActivity(intent);
    }

    public void on_click_qmk_btn_tieptuc(View view){
        EmailSchema emailSchema = new EmailSchema(et_email.getText().toString());
        sendEmail(emailSchema, view);
    }

    public void sendEmail(EmailSchema emailSchema, View view){
        Call<Triplet<String, Integer, String>> call = RetrofitClient.getInstance().getMyApi().sendMail(emailSchema);
        call.enqueue(new Callback<Triplet<String, Integer, String>>() {
            @Override
            public void onResponse(Call<Triplet<String, Integer, String>> call, Response<Triplet<String, Integer, String>> response) {
                Triplet<String, Integer, String> results = response.body();
                if(results.getSecond() != 0){
                    Toast.makeText(getApplicationContext(), "Email not exist", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Code sent to email", Toast.LENGTH_LONG).show();
                    intentToDatLaiMK(view);
                }
            }

            @Override
            public void onFailure(Call<Triplet<String, Integer, String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void intentToDatLaiMK(View view){
        Intent intent = new Intent();
        intent.setClass(view.getContext(), DatLaiMK.class);
        startActivity(intent);
    }
}