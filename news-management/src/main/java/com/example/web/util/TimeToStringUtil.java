package com.example.web.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeToStringUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getNowTime() {
        LocalDateTime now = LocalDateTime.now();

        return now.format(formatter);
    }


    public static String timestampToString(Timestamp time) {

        LocalDateTime localDateTime = time.toLocalDateTime();
        String format = localDateTime.format(formatter);
        return format.substring(0, 19);
    }

}
