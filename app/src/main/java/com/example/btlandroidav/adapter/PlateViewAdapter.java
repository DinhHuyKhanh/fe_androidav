package com.example.btlandroidav.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btlandroidav.R;
import com.example.btlandroidav.response.Plate;

import java.util.List;

public class PlateViewAdapter extends BaseAdapter {
    final List<Plate> listPlate;

    public PlateViewAdapter(List<Plate> listPlate) {
        this.listPlate = listPlate;
    }

    @Override
    public int getCount() {
        return listPlate.size();
    }

    @Override
    public Object getItem(int i) {
        return listPlate.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listPlate.get(i).getId();
    }

    private TextView car_view_tv_plate;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewPlate;
        if (view == null) {
            viewPlate = View.inflate(viewGroup.getContext(), R.layout.car_view, null);
        } else viewPlate = view;

        init(viewPlate);

        Plate plate = (Plate) getItem(i);
        car_view_tv_plate.setText(plate.getNumberPlate());

        return viewPlate;
    }

    private void init(View viewGate){
        car_view_tv_plate = viewGate.findViewById(R.id.car_view_tv_plate);
    }
}