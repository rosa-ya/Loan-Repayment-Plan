package com.lendico.loan.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {


    public static String addMonthsToDate(String date, int months) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return LocalDateTime.parse(date,dateTimeFormatter).plusMonths(months).format(dateTimeFormatter);

    }

}
