package com.xml.autorsko_pravo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String DATE_FORMAT = "dd/MM/yyyy";

    public static final String PRILOZI_DIR = "content/prilozi";

    public static String dateToStr(Date date) {
        SimpleDateFormat sdt = new SimpleDateFormat(DATE_FORMAT);
        return sdt.format(date);
    }
}
