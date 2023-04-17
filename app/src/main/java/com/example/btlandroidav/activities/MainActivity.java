package com.example.btlandroidav.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btlandroidav.R;
import com.example.btlandroidav.networks.RetrofitClient;
import com.example.btlandroidav.request.LoginSchema;
import com.example.btlandroidav.response.tuple.Triplet;
import com.example.btlandroidav.response.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tv_quenmk;
    EditText et_email;
    EditText et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        tv_quenmk = findViewById(R.id.tv_quenmk);
    }

    public void on_click_dn_qmk(View view){
        Intent intent = new Intent();
        intent.setClass(view.getContext(), QuenMK.class);
        startActivity(intent);
    }

    public boolean validateEmail(String email){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.matches(emailPattern))
        {
            Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
            return true;
        }

        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void on_click_dn_login(View view){
        String email = et_email.getText().toString().trim();
        String password = et_pass.getText().toString();

        if(validateEmail(email)){
            LoginSchema loginSchema = new LoginSchema(email, password);
            login(loginSchema, view);
        }
    }

    public void login(LoginSchema loginSchema, View view){
        Call<Triplet<User, Integer, String>> call = RetrofitClient.getInstance().getMyApi().login(loginSchema);
        call.enqueue(new Callback<Triplet<User, Integer, String>>() {
            @Override
            public void onResponse(Call<Triplet<User, Integer, String>> call, Response<Triplet<User, Integer, String>> response) {
                Triplet<User, Integer, String> results = response.body();
                if(results.getSecond() == 0){
                    Toast.makeText(getApplicationContext(), results.getThird(), Toast.LENGTH_LONG).show();
                    intentToTrangChu(view, results.getFirst());
                }
                else
                    Toast.makeText(getApplicationContext(), results.getThird(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Triplet<User, Integer, String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void intentToTrangChu(View view, User user){
        Intent intent = new Intent();
        intent.putExtra("id_user", user.getId());
        intent.setClass(view.getContext(), TrangChu.class);
        startActivity(intent);
    }
}