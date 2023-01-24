package com.xml.patent.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String DATE_FORMAT = "dd/MM/yyyy";

    public static String dateToStr(Date date) {
        SimpleDateFormat sdt = new SimpleDateFormat(DATE_FORMAT);
        return sdt.format(date);
    }

    public static Date strToDate(String string) {
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdt.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
