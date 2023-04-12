package com.example.btlandroidav.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btlandroidav.R;
import com.example.btlandroidav.response.GateHistory;
import com.example.btlandroidav.utils.Helper;
import java.util.List;

public class GateHistoryViewAdapter extends BaseAdapter {
    final List<GateHistory> listProduct;

    public GateHistoryViewAdapter(List<GateHistory> listProduct) {
        this.listProduct = listProduct;
    }

    @Override
    public int getCount() {
        return listProduct.size();
    }

    @Override
    public Object getItem(int i) {
        return listProduct.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listProduct.get(i).getId();
    }

    private TextView time_gui_xe;
    private TextView time_lay_xe;
    private TextView plate;
    private TextView money;
    private TextView maGD;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewGate;
        if (view == null) {
            viewGate = View.inflate(viewGroup.getContext(), R.layout.gate_view, null);
        } else viewGate = view;

        init(viewGate);

        GateHistory gateHistory = (GateHistory) getItem(i);
        time_gui_xe.setText(Helper.formatDateTime(gateHistory.getCheckInDate()));
        time_lay_xe.setText(Helper.formatDateTime(gateHistory.getCheckOutDate()));
        plate.setText(gateHistory.getNumberPlate());
        money.setText("0.000");
        maGD.setText(gateHistory.getId().toString());

        return viewGate;
    }

    private void init(View viewGate){
        time_gui_xe = viewGate.findViewById(R.id.gate_view_tv_set_time_gui);
        time_lay_xe = viewGate.findViewById(R.id.gate_view_tv_set_time_lay);
        plate = viewGate.findViewById(R.id.gate_view_tv_set_plate);
        money = viewGate.findViewById(R.id.gate_view_tv_set_money);
        maGD = viewGate.findViewById(R.id.gate_view_tv_set_magd);
    }
}
