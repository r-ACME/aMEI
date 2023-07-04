package com.example.amei.Negocios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String formatDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        return dateFormat.format(date);
    }

    public static Date parseStringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
