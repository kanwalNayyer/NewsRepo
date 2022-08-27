package com.hisham.news.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateParser {

    public static String parseDateForFeed(String date){
        Calendar calendar = Calendar.getInstance();
//        Initial Format: 2019-02-12T18:24:58.589Z
        String dateWithDash = date.split("T")[0];// 2019-02-12
        String[] individualDateArray = dateWithDash.split("-");
        calendar.set(Calendar.YEAR, Integer.parseInt(individualDateArray[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(individualDateArray[1])-1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(individualDateArray[2]));

        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());

        return formatter.format(calendar.getTime());

    }


}
