package com.example.btlandroidav.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btlandroidav.R;
import com.example.btlandroidav.adapter.CustomExpandableListview;
import com.example.btlandroidav.networks.RetrofitClient;
import com.example.btlandroidav.response.DateAndYear;
import com.example.btlandroidav.response.TransactionHistory;
import com.example.btlandroidav.response.User;
import com.example.btlandroidav.response.tuple.Triplet;
import com.example.btlandroidav.utils.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaiKhoan extends AppCompatActivity {
    ExpandableListView expandableListView;
    CustomExpandableListview expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<TransactionHistory>> expandableListDetail;
    TextView tvCoin;
    private int user_id;
    ImageView ivTrangChu;
    View lineTatCa;
    View lineTienRa;
    View lineTienVao;
    private String ACT_ADD = "add";
    private String ACT_SUB = "car deposit payment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);

        init();
        getInfoFromIntent();
        getCoinOfUser(user_id);
        getThoiGianLSGiaoDich(user_id, null);
        handleListener(user_id, null);
    }

    private void init(){
        expandableListView = findViewById(R.id.tai_khoan_expandableListView);
        expandableListDetail = new HashMap<>();
        expandableListTitle = new ArrayList<String>();
        tvCoin = findViewById(R.id.tai_khoan_tv_so_tien);
        ivTrangChu = findViewById(R.id.tai_khoan_iv_trang_chu);
        lineTatCa = findViewById(R.id.tai_khoan_line_of_tatca);
        lineTienRa = findViewById(R.id.tai_khoan_line_of_tienra);
        lineTienVao = findViewById(R.id.tai_khoan_line_of_tienvao);
    }

    private void getInfoFromIntent(){
        Intent intent = getIntent();
        user_id = intent.getIntExtra("id_user", 0);
    }

    private void setVisibilityOfView(int vlineTatCa, int vlineTienVao, int vlineTienRa){
        lineTatCa.setVisibility(vlineTatCa);
        lineTienVao.setVisibility(vlineTienVao);
        lineTienRa.setVisibility(vlineTienRa);
    }

    public void on_click_tai_khoan_tat_ca(View view){
        setVisibilityOfView(View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
        getThoiGianLSGiaoDich(user_id, null);
        handleListener(user_id, null);
    }

    public void on_click_tai_khoan_trang_chu(View view){
        Intent intent = new Intent();
        intent.putExtra("id_user", user_id);
        intent.setClass(view.getContext(), TrangChu.class);
        startActivity(intent);
    }

    public void on_click_tai_khoan_thong_bao(View view){
        Intent intent = new Intent();
        intent.putExtra("id_user", user_id);
        intent.setClass(view.getContext(), ThongBao.class);
        startActivity(intent);
    }

    public void on_click_tai_khoan_tien_vao(View view){
        setVisibilityOfView(View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
        getThoiGianLSGiaoDich(user_id, ACT_ADD);
        handleListener(user_id, ACT_ADD);
    }

    public void on_click_tai_khoan_tien_ra(View view){
        setVisibilityOfView(View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
        getThoiGianLSGiaoDich(user_id, ACT_SUB);
        handleListener(user_id, ACT_SUB);
    }

    private void handleListener(int user_id, String act){
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                String title = expandableListTitle.get(i);
                String[] words = title.split("/");
                getDetailLSGiaoDich(user_id, Integer.parseInt(words[0]), Integer.parseInt(words[1]), act);
                return false;
            }
        });
    }

    public void getCoinOfUser(int user_id){
        Call<Triplet<User, Integer, String>> call = RetrofitClient.getInstance().getMyApi().get_by_id(user_id);
        call.enqueue(new Callback<Triplet<User, Integer, String>>() {
            @Override
            public void onResponse(Call<Triplet<User, Integer, String>> call, Response<Triplet<User, Integer, String>> response) {
                Triplet<User, Integer, String> results = response.body();
                Toast.makeText(getApplicationContext(), results.getThird(), Toast.LENGTH_LONG).show();
                if(results.getSecond() == 0)
                {
                    User user = results.getFirst();
                    Toast.makeText(getApplicationContext(), "Get users success", Toast.LENGTH_LONG).show();
                    tvCoin.setText(Helper.formatMoney(user.getCoin()));
                }
            }

            @Override
            public void onFailure(Call<Triplet<User, Integer, String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getThoiGianLSGiaoDich(Integer user_id, String act){
        Call<Triplet<List<DateAndYear>, Integer, String>> call = RetrofitClient.getInstance().getMyApi().getAllTransactionByUserId(user_id, act);
        call.enqueue(new Callback<Triplet<List<DateAndYear>, Integer, String>>() {
            @Override
            public void onResponse(Call<Triplet<List<DateAndYear>, Integer, String>> call, Response<Triplet<List<DateAndYear>, Integer, String>> response) {
                Triplet<List<DateAndYear>, Integer, String> results = response.body();
                if(results.getSecond() == 0)
                {
                    List<DateAndYear> dateAndYears = results.getFirst();
                    expandableListDetail.clear();
                    for(int i = 0; i < dateAndYears.size(); ++i)
                    {
                        String month = dateAndYears.get(i).getMonth();
                        String year = dateAndYears.get(i).getYear();
                        expandableListDetail.put(month + "/" + year, new ArrayList<>());
                    }

                    expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                    expandableListAdapter = new CustomExpandableListview(TaiKhoan.this, expandableListTitle, expandableListDetail);
                    expandableListView.setAdapter(expandableListAdapter);
                }
            }

            @Override
            public void onFailure(Call<Triplet<List<DateAndYear>, Integer, String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getDetailLSGiaoDich(int user_id, int month, int year, String act){
        Call<Triplet<List<TransactionHistory>, Integer, String>> call = RetrofitClient.getInstance().getMyApi().getDetailTransaction(user_id, month, year, act);
        call.enqueue(new Callback<Triplet<List<TransactionHistory>, Integer, String>>() {
            @Override
            public void onResponse(Call<Triplet<List<TransactionHistory>, Integer, String>> call, Response<Triplet<List<TransactionHistory>, Integer, String>> response) {
                Triplet<List<TransactionHistory>, Integer, String> results = response.body();
                if(results.getSecond() == 0)
                {
                    expandableListDetail.get(month + "/" + year).clear();
                    List<TransactionHistory> dateAndYears = results.getFirst();
                    for(int i = 0; i < dateAndYears.size(); ++i)
                    {
                        expandableListDetail.get(month + "/" + year).add(dateAndYears.get(i));
                    }

                    expandableListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Triplet<List<TransactionHistory>, Integer, String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
