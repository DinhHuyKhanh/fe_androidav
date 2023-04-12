package com.example.btlandroidav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CaiDat extends AppCompatActivity {
    private int user_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);

        user_id = getInfoFromIntent();
    }

    private int getInfoFromIntent(){
        Intent intent = getIntent();
        return intent.getIntExtra("id_user", 0);
    }

    public void onlick_cai_dat_return(View view){
        Intent intent = new Intent();
        intent.putExtra("id_user", user_id);
        intent.setClass(view.getContext(), TrangChu.class);
        startActivity(intent);
    }

    public void on_click_cai_dat_doiMK(View view){
        Intent intent = new Intent();
        intent.putExtra("id_user", user_id);
        intent.setClass(view.getContext(), DoiMatKhau.class);
        startActivity(intent);
    }

    public void on_click_cai_dat_qrCode(View view){
        Intent intent = new Intent();
        intent.putExtra("id_user", user_id);
        intent.setClass(view.getContext(), QrCode.class);
        startActivity(intent);
    }
}