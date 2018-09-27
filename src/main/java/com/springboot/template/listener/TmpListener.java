package com.springboot.template.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.websocket.Session;

/**
 * Created by jinyu on 2018/8/30.
 */
@WebListener
public class TmpListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {   //session创建时调用，测试时在操作session的时候调用
        /*存入redis的时候可以先将session作为key存进去，然后在controller里面将用户信息作为值更新到该key里面*/
        //System.out.println("Session监听器初始化" + se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Session监听器销毁" + "我试试是不是关闭浏览器就能触发");
    }
}
