package com.smaato.api.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RequestUtil {

    public static String getCurrentTime(){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String format = time.format(formatter);
        return format;
    }
    public static String getPreviousMinute(){
        LocalTime time = LocalTime.now().minusMinutes(1);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter1);

    }
}
