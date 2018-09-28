package com.springboot.template;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by jinyu on 2018/9/28.
 */
public class DateTest {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sf.format(System.currentTimeMillis());
        System.out.println(currentTime);
        System.out.println(sf.parse(currentTime));
    }
}
