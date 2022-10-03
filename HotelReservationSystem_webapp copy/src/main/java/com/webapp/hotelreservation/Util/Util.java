package com.webapp.hotelreservation.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Util {

    public static String getCurrentDate(){
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        String currentDate = formatter.format(calendar.getTime());
        return currentDate;
    }

    public static List<String> getNextOrPreviousDates(String startDate, int limit, boolean nextDay){
        SimpleDateFormat sdf = null;
        Calendar calendar = null;
        List<String> nextDates = new ArrayList<>();
        int amount = 0;
        if (nextDay){
            amount = 1;
        }else{
            amount = -1;
        }
        try {
            sdf = new SimpleDateFormat("MM/dd/yyyy");
            calendar = Calendar.getInstance();
            for (int i=1 ; i<=limit; i++){
                calendar.setTime(sdf.parse(startDate));
                calendar.add(Calendar.DATE, amount);
                String nextDate = sdf.format(calendar.getTime());
                nextDates.add(nextDate);
                startDate = nextDate;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nextDates;
    }

}
