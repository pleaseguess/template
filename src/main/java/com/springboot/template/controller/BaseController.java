package com.springboot.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by jinyu on 2018/9/28.
 */

@Controller
public class BaseController {


    public HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


    public String getString(String name,String defaultValue){
        String resultStr = getRequest().getParameter(name);
        if (resultStr == null || "".equals(resultStr) || "null".equals(resultStr) || "undefined".equals(resultStr)) {
            return defaultValue;
        } else {
            return resultStr;
        }
    }

    public String getString(String name){
        return getString(name,"");
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public Long getTimeStamp(){
        return (System.currentTimeMillis());
    }

    /**
     * 获取uuid
     * @return
     */
    public String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
