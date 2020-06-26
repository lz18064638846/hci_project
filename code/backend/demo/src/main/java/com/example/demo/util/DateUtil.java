package com.example.demo.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Zhe Li
 * @date 2020/06/26
 */
public class DateUtil {
    /**
     * 获取当天的零点时间戳
     *
     * @return 当天的零点时间戳
     */
    public static long getTodayStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }
}
