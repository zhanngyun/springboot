package com.baidu.ueditor.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具了
 */
public class DateUtil {
    public final static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DISPLAY_DATE_FORMAT_STRING = "yyyy-MM-dd";

    public static String format(Date date) {
        return format(date, DEFAULT_PATTERN);
    }

    /**
     * date转String
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * String 转 Date
     *
     * @param aMask
     * @param strDate
     * @return
     */
    public static Date convertStringToDate(String aMask, String strDate) {
        if (StringUtils.isBlank(strDate)) return null;
        Date date = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat(aMask);
            date = df.parse(strDate);
        } catch (Exception pe) {
            pe.printStackTrace();
        }
        return date;
    }

    /**
     * 日期进行加减运算
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static Date add(Date date, int field, int amount) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(field, amount);
        return ca.getTime();
    }

    /**
     * 判断两个日期是否是同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        Assert.notNull(date1, "判断两个日期是否是同一天，第一个参数不能为null！");
        Assert.notNull(date2, "判断两个日期是否是同一天，第二个参数不能为null！");

        String date1Str = format(date1, DISPLAY_DATE_FORMAT_STRING);
        String date2Str = format(date2, DISPLAY_DATE_FORMAT_STRING);
        return date1Str.equals(date2Str);
    }
}
