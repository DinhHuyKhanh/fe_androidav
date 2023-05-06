package com.example.btlandroidav.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btlandroidav.R;
import com.example.btlandroidav.response.NotificationHistory;
import com.example.btlandroidav.utils.Helper;

import java.util.List;

public class ThongBaoViewAdapter extends BaseAdapter {
    final List<NotificationHistory> listNotificationHistory;

    public ThongBaoViewAdapter(List<NotificationHistory> listNotificationHistory) {
        this.listNotificationHistory = listNotificationHistory;
    }

    @Override
    public int getCount() {
        return listNotificationHistory.size();
    }

    @Override
    public Object getItem(int i) {
        return listNotificationHistory.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listNotificationHistory.get(i).getId();
    }

    private TextView tvMessage;
    private TextView tvDate;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewGate;
        if (view == null) {
            viewGate = View.inflate(viewGroup.getContext(), R.layout.thong_bao_view, null);
        } else viewGate = view;

        init(viewGate);

        NotificationHistory notificationHistory = (NotificationHistory) getItem(i);
        tvMessage.setText(notificationHistory.getMessage());
        tvDate.setText(Helper.formatDateTime(notificationHistory.getCreatedDate()));

        return viewGate;
    }

    private void init(View viewGate){
        tvDate = viewGate.findViewById(R.id.tb_view_date);
        tvMessage = viewGate.findViewById(R.id.tb_view_message);
    }
}
