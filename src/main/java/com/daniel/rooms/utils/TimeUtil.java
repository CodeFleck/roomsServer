package com.daniel.rooms.utils;

import org.springframework.stereotype.Component;

@Component
public class TimeUtil {

    public static String getTimeFromDate(String date) {
        if (date.length() > 5) {
            return date.substring(16, 21);
        } else {
            return date;
        }
    }
}
