package com.project.hotelreservationsystem.util;

import com.project.hotelreservationsystem.exceptions.InvalidDataException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Util {

    public static String getCurrentDate(){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String currentDate = formatter.format(calendar.getTime());
        return currentDate;
    }

    public static List<String> getNextDates(String startDate, int limit){
        SimpleDateFormat sdf = null;
        Calendar calendar = null;
        List<String> nextDates = new ArrayList<>();
        try {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            calendar = Calendar.getInstance();
            for (int i=1 ; i<=limit; i++){
                calendar.setTime(sdf.parse(startDate));
                calendar.add(Calendar.DATE, 1);
                String nextDate = sdf.format(calendar.getTime());
                nextDates.add(nextDate);
                startDate = nextDate;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nextDates;
    }

    public static boolean compareDates(String date1, String date2){
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        Date current = null;
        try {
            d1 = sdformat.parse(date1);
            d2 = sdformat.parse(date2);
            current = sdformat.parse(getCurrentDate());
            if(d1.compareTo(d2) > 0 || d1.compareTo(current)<0 || d2.compareTo(current)<0) {
               return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean isAfter(String date1, String date2) throws InvalidDataException {
        boolean isAfter = false;
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        Date current = null;
        try {
            d1 = sdformat.parse(date1);
            d2 = sdformat.parse(date2);
            current = sdformat.parse(getCurrentDate());
            if(d1.compareTo(d2)>=0) {
                isAfter = true;
            }else if(d1.compareTo(d2)<0){
                isAfter =  false;
            }else{
                isAfter = false;
            }
        } catch (ParseException e) {
            throw new InvalidDataException("Invalid date format. Follow yyyy-MM-dd format");
        }
        return isAfter;
    }

}
