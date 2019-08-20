package id.chessburger.wecare.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by aflah on 06/07/19
 * Email  : aflahtaqiusondha@gmail.com
 * Github : https://github.com/aflahtaqiu
 */


public class DateTimeUtils {

    public static final String FORMAT_DDMMYYYY = "dd-MM-yyyy";
    public static final String FORMAT_EEEDDMMMYYYY = "EEE, dd MMM yyyy";
    public static final String FORMAT_EEEEDDMMMYYYY = "EEEE, dd MMM yyyy";
    public static final String FORMAT_EEEE = "EEEE";
    public static final String FORMAT_MMMM = "MMMM";
    public static final String FORMAT_HMMA = "h:mm a";
    public static final String FORMAT_HHMMA = "hh:mm a";
    public static final String FORMAT_HHMMSS = "HH:mm:ss";
    public static final String FORMAT_HHMM = "HH:mm";
    public static final String FORMAT_DDMMMMYY = "dd MMMM yyyy";
    public static final String FORMAT_YYYYMMDD_HHMMSS = "yyyy-dd-MM HH:mm:ss";

    private Calendar calendar;

    public DateTimeUtils() {
        calendar = Calendar.getInstance();
    }

    public static String dateToString (Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public Date stringToDate (String string, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Depend on localization
    public String getDayName (Date date) {
        return dateToString(date, FORMAT_EEEE);
    }

    public String getMonthName (Date date) {
        return dateToString(date, FORMAT_MMMM);
    }

    public int getToday (Date date) {
        if (date!=null)
            calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    public int getToday () {
        return getToday(null);
    }

    public int getMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }

    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public long getDayDifference (Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
    }

    public long getYearDifference (Date date1, Date date2) {
        return getDayDifference(date1, date2) / 365;
    }
}
