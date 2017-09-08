package com.example.account.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ASUS on 2017/9/8.
 */

public class StringUtil {


    public static String formatShortDate(String str) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        String format = null;
        Date date = null;
        try {
            date = sdf1.parse(str);
            format = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format;
    }


    public static Date stringToDate(String dateString) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date dateValue = simpleDateFormat.parse(dateString, position);
        return dateValue;
    }


    public static String formatDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(date);
    }
}
