package com.example.btlandroidav.activities;

import static com.example.btlandroidav.networks.Api.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.btlandroidav.R;
import com.squareup.picasso.Picasso;

public class QrCode extends AppCompatActivity {
    private ImageView iv_qrCode;
    private ImageView iv_qrCode_return;
    private int user_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        init();
        getInfoFromIntent();
        getQrCode(user_id);
    }

    private void init(){
        iv_qrCode = findViewById(R.id.qrCode_iv_qrcode);
        iv_qrCode_return = findViewById(R.id.qrCode_iv_return);
    }

    public void onlick_qrcode_return(View view){
        Intent intent = new Intent();
        intent.putExtra("id_user", user_id);
        intent.setClass(view.getContext(), CaiDat.class);
        startActivity(intent);
    }

    private void getInfoFromIntent(){
        Intent intent = getIntent();
        user_id = intent.getIntExtra("id_user", 0);
    }

    public void getQrCode(Integer user_id){
        Picasso.with(getApplicationContext())
                .load(  BASE_URL + "qrcode?id=" + user_id.toString())
                .fit()
                .into(iv_qrCode);
    }
}