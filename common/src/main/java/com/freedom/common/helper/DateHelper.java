package com.freedom.common.helper;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {

    // 日期格式化字符串
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    // 日期时间格式化字符串
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // 短日期时间格式化字符串
    private static final String SHORT_TIME_FORMAT = "MM-dd HH:mm";

    // 得到当前时间
    public static Date getNow() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MILLISECOND, - now.get(Calendar.MILLISECOND));
        return now.getTime();
    }

    // 得到两个日期之间的日期差
    public static int getDaysBetween(Date fromDate, Date toDate) {
        if (fromDate == null) return Integer.MAX_VALUE;
        if (toDate == null) return Integer.MIN_VALUE;
        return (int) ((toDate.getTime() - fromDate.getTime()) / 86400000);
    }

}
