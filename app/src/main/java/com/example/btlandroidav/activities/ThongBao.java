package com.example.btlandroidav.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.btlandroidav.R;
import com.example.btlandroidav.adapter.ThongBaoViewAdapter;
import com.example.btlandroidav.networks.RetrofitClient;
import com.example.btlandroidav.response.NotificationHistory;
import com.example.btlandroidav.response.tuple.Triplet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongBao extends AppCompatActivity {
    ThongBaoViewAdapter thongBaoViewAdapter;
    ListView listViewThongBao;
    private int user_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);

        getInfoFromIntent();
        get_all_notification(user_id);
    }

    private void getInfoFromIntent(){
        Intent intent = getIntent();
        user_id = intent.getIntExtra("id_user", 0);
    }

    public void on_click_thong_bao_trang_chu(View view){
        Intent intent = new Intent();
        intent.putExtra("id_user", user_id);
        intent.setClass(view.getContext(), TrangChu.class);
        startActivity(intent);
    }

    public void on_click_thong_bao_tai_khoan(View view){
        Intent intent = new Intent();
        intent.putExtra("id_user", user_id);
        intent.setClass(view.getContext(), TaiKhoan.class);
        startActivity(intent);
    }

    public void get_all_notification(Integer user_id){
        Call<Triplet<List<NotificationHistory>, Integer, String>> call = RetrofitClient.getInstance().getMyApi().get_all_notification(user_id);
        call.enqueue(new Callback<Triplet<List<NotificationHistory>, Integer, String>>() {
            @Override
            public void onResponse(Call<Triplet<List<NotificationHistory>, Integer, String>> call, Response<Triplet<List<NotificationHistory>, Integer, String>> response) {
                Triplet<List<NotificationHistory>, Integer, String> results = response.body();
                Toast.makeText(getApplicationContext(), results.getThird(), Toast.LENGTH_LONG).show();

                if(results.getSecond() == 0)
                {
                    thongBaoViewAdapter = new ThongBaoViewAdapter(results.getFirst());
                    listViewThongBao = findViewById(R.id.list_view_thong_bao);
                    listViewThongBao.setAdapter(thongBaoViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Triplet<List<NotificationHistory>, Integer, String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}