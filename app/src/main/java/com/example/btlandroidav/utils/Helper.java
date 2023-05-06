package com.example.btlandroidav.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Helper {
    public static final String formatDateTime(String dateTime){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date result = df.parse(dateTime);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            return sdf.format(result);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String formatMoney(double money){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(localeVN);
        return numberFormat.format(money);
    }
}
