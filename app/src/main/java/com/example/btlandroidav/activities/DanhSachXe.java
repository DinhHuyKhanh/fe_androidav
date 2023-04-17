package com.example.btlandroidav.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btlandroidav.R;
import com.example.btlandroidav.adapter.PlateViewAdapter;
import com.example.btlandroidav.networks.RetrofitClient;
import com.example.btlandroidav.response.Plate;
import com.example.btlandroidav.response.tuple.Triplet;
import com.example.btlandroidav.response.tuple.Unit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachXe extends AppCompatActivity {
    private PlateViewAdapter plateViewAdapter;
    private ListView listViewPlate;
    private TextView slXe;
    private Integer user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_xe);

        init();
        getInfoFromIntent();
        getAllPlate();
    }

    private void init(){
        slXe = findViewById(R.id.dsx_tv_so_luong_xe);
        listViewPlate = findViewById(R.id.dsx_listViewCar);
    }

    private void getInfoFromIntent(){
        Intent intent = getIntent();
        user_id = intent.getIntExtra("id_user", 0);
    }

    public void onlick_dsx_return(View view){
        Intent intent = new Intent();
        intent.putExtra("id_user", user_id);
        intent.setClass(view.getContext(), TrangChu.class);
        startActivity(intent);
    }

    public void getAllPlate(){
        Call<Triplet<Unit<Integer, List<Plate>>, Integer, String>> call = RetrofitClient.getInstance().getMyApi().get_all_plate(user_id);
        call.enqueue(new Callback<Triplet<Unit<Integer, List<Plate>>, Integer, String>>() {
            @Override
            public void onResponse(Call<Triplet<Unit<Integer, List<Plate>>, Integer, String>> call, Response<Triplet<Unit<Integer, List<Plate>>, Integer, String>> response) {
                Triplet<Unit<Integer, List<Plate>>, Integer, String> results = response.body();
                Toast.makeText(getApplicationContext(), results.getThird(), Toast.LENGTH_LONG).show();

                if(results.getSecond() == 0)
                {
                    List<Plate> plates = results.getFirst().getSecond();
                    slXe.setText("Số lượng xe: " + plates.size());
                    plateViewAdapter = new PlateViewAdapter(plates);
                    listViewPlate.setAdapter(plateViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Triplet<Unit<Integer, List<Plate>>, Integer, String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}