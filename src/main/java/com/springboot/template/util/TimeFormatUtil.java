package com.springboot.template.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jinyu on 2018/9/28.
 */
public class TimeFormatUtil {

    /**
     * 国际化时间格式 2017-11-7T17:02:07+08:00
     *
     * @Date:17:01 2017/11/7
     */
    private static final String YMDHMSXXX = "yyyy-MM-dd'T'HH:mm:ssXXX";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    private static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    /**
     * dd-MM-yyyy
     */
    private static final String DMY = "dd-MM-yyyy";
    /**
     * MM-dd-yyyy
     */
    private static final String MDY = "MM-dd-yyyy";
    /**
     * dd-MM-yyyy HH:mm
     */
    private static final String DMYHM = "dd-MM-yyyy HH:mm";



    /**
     * 时间格式化
     */
    public static String format(Date date, String formatPattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat f = new SimpleDateFormat(formatPattern);
        return f.format(date);
    }

    /**
     * 时间格式化
     */
    public static String format(Long date, String formatPattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat f = new SimpleDateFormat(formatPattern);
        return f.format(date);
    }

}
