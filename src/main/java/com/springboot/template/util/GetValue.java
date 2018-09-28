package com.springboot.template.util;

/**
 * Created by jinyu on 2018/9/28.
 */
public class GetValue {

    public static String getString(Object o){
        if( o != null ){
            return o.toString();
        }else{
            return "";
        }
    }
}
