package com.example.btlandroidav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.btlandroidav.adapter.GateHistoryViewAdapter;
import com.example.btlandroidav.request.EmailSchema;
import com.example.btlandroidav.response.GateHistory;
import com.example.btlandroidav.response.Triplet;
import com.example.btlandroidav.response.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichSuGuiXe extends AppCompatActivity {

    GateHistoryViewAdapter productListViewAdapter;
    ListView listViewProduct;
    private int user_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_gui_xe);

        user_id = getInfoFromIntent();
        get_all_gate_histories(user_id);
    }

    private int getInfoFromIntent(){
        Intent intent = getIntent();
        return intent.getIntExtra("id_user", 0);
    }

    public void onlick_lsgs_return(View view){
        Intent intent = new Intent();
        intent.putExtra("id_user", user_id);
        intent.setClass(view.getContext(), TrangChu.class);
        startActivity(intent);
    }

    public void get_all_gate_histories(Integer user_id){
        Call<Triplet<List<GateHistory>, Integer, String>> call = RetrofitClient.getInstance().getMyApi().gate_histories(user_id);
        call.enqueue(new Callback<Triplet<List<GateHistory>, Integer, String>>() {
            @Override
            public void onResponse(Call<Triplet<List<GateHistory>, Integer, String>> call, Response<Triplet<List<GateHistory>, Integer, String>> response) {
                Triplet<List<GateHistory>, Integer, String> results = response.body();
                Toast.makeText(getApplicationContext(), results.getThird(), Toast.LENGTH_LONG).show();

                if(results.getSecond() == 0)
                {
                    Toast.makeText(getApplicationContext(), results.getThird(), Toast.LENGTH_LONG).show();
                    productListViewAdapter = new GateHistoryViewAdapter(results.getFirst());
                    listViewProduct = findViewById(R.id.listViewProduct);
                    listViewProduct.setAdapter(productListViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Triplet<List<GateHistory>, Integer, String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}