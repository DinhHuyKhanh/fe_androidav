package com.example.btlandroidav.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btlandroidav.R;
import com.example.btlandroidav.networks.RetrofitClient;
import com.example.btlandroidav.request.UpdatePasswordSchema;
import com.example.btlandroidav.response.tuple.Triplet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoiMatKhau extends AppCompatActivity {

    private Integer user_id = 0;
    private TextView tv_old_password;
    private TextView tv_new_password;
    private TextView tv_xn_new_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        init();
        getInfoFromIntent();
    }

    private void init(){
        tv_old_password = findViewById(R.id.doiMK_et_mat_khau_cu);
        tv_new_password = findViewById(R.id.doiMK_et_mat_khau_moi);
        tv_xn_new_password = findViewById(R.id.doiMK_et_mat_khau_xac_nhan);
    }

    private void getInfoFromIntent(){
        Intent intent = getIntent();
        user_id = intent.getIntExtra("id_user", 0);
    }

    public void onlick_doiMK_return(View view){
        Intent intent = new Intent();
        intent.putExtra("id_user", user_id);
        intent.setClass(view.getContext(), CaiDat.class);
        startActivity(intent);
    }

    public void on_click_doiMK_btn_doimk(View view){
        String old_password = tv_old_password.getText().toString();
        String new_password = tv_new_password.getText().toString();
        String new_password_xn = tv_xn_new_password.getText().toString();

        if(new_password.equals(new_password_xn)){
            UpdatePasswordSchema updatePasswordSchema = new UpdatePasswordSchema(old_password, new_password);
            updatePassword(updatePasswordSchema);
        }
        else
            Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp nhau", Toast.LENGTH_LONG).show();
    }

    public void updatePassword(UpdatePasswordSchema updatePasswordSchema){
        Call<Triplet<Integer, Integer, String>> call = RetrofitClient.getInstance().getMyApi().updatePassword(user_id, updatePasswordSchema);
        call.enqueue(new Callback<Triplet<Integer, Integer, String>>() {
            @Override
            public void onResponse(Call<Triplet<Integer, Integer, String>> call, Response<Triplet<Integer, Integer, String>> response) {
                Triplet<Integer, Integer, String> results = response.body();
                Toast.makeText(getApplicationContext(), results.getThird(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Triplet<Integer, Integer, String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
}