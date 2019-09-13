package com.farm.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description: 时间工具类
 * @author: xyc
 * @create: 2019-08-25 11:40
 */
public class DateUtils {


    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDD = "yyyyMMdd";
    // 2017-08-09 上午/下午
    public static final String YYYY_MM_DD_A = "yyyy-MM-dd a";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";


    public static Date getStartTime(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    /**
     * stringToDate
     *
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static Date stringToDate(String dateStr) throws Exception {
        return stringToDate(dateStr, YYYY_MM_DD_HH_MM_SS);
    }

    public static Date stringToDate(String dateStr, String pattern) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (Exception e) {
            throw new Exception("日期字符格式错误：" + dateStr);
        }
        return date;
    }

    public static String dateToString(Date date) throws Exception {
        if (date == null) {
            return "";
        }
        return dateToString(date, YYYY_MM_DD_HH_MM_SS);
    }


    public static String dateToString(Date date, String pattern) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String result;
        try {
            result = simpleDateFormat.format(date);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return result;
    }

    /**
     * @param date
     * @param day  想要获取的日期与传入日期的差值 比如想要获取传入日期前四天的日期 day=-4即可
     * @return
     */
    public static Date getSomeDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 日期差天数、小时、分钟、秒数组
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long[] getDisTime(Date startDate, Date endDate) {
        long timesDis = Math.abs(startDate.getTime() - endDate.getTime());
        long day = timesDis / (1000 * 60 * 60 * 24);
        long hour = timesDis / (1000 * 60 * 60) - day * 24;
        long min = timesDis / (1000 * 60) - day * 24 * 60 - hour * 60;
        long sec = timesDis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
        return new long[]{day, hour, min, sec};
    }

    /**
     * 日期差天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDisDay(Date startDate, Date endDate) {
        long[] dis = getDisTime(startDate, endDate);
        long day = dis[0];
        if (dis[1] > 0 || dis[2] > 0 || dis[3] > 0) {
            day += 1;
        }
        return day;
    }

    /**
     * 日期差文字描述
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getDisTimeStr(Date startDate, Date endDate) {
        long[] dis = getDisTime(startDate, endDate);
        return new StringBuilder().append(dis[0]).append("天").append(dis[1]).append("小时").append(dis[2]).append("分钟")
                .append(dis[3]).append("秒").toString();
    }


    public static boolean isWorkDay(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int dayOfWeek = instance.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek > 1 && dayOfWeek < 7) {
            return true;
        }
        return false;
    }

    /**
     * 获取季度
     *
     * @return
     */
    public static int getQuarter(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int m = cal.get(Calendar.MONTH) + 1;
        int quarter = 1;
        if (m >= 1 && m == 3) {
            quarter = 1;
        }
        if (m >= 4 && m <= 6) {
            quarter = 2;
        }
        if (m >= 7 && m <= 9) {
            quarter = 3;
        }
        if (m >= 10 && m <= 12) {
            quarter = 4;
        }
        return quarter;
    }

    /**
     * 获取年份
     *
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);

    }

}
