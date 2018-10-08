package com.springboot.template.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.websocket.Session;

/**
 * 实现步骤:
 *  ①创建监听实现类,继承监听器接口。如下
 *  ②启动类加上注解@ServletComponentScan,让服务器扫描到该监听
 */
@WebListener
public class TmpListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //这里可以操作上下文变量
        ServletContext servletContext = se.getSession().getServletContext();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}
