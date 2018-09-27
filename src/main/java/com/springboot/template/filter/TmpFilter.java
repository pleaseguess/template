package com.springboot.template.filter;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;



/**
 * Created by jinyu on 2018/8/29.
 * 过滤器：在客户端发起请求时触发
 *
 */
@Order(1)   //filter执行的顺序
@WebFilter(filterName = "AllRequestFilter",urlPatterns = "/test")
public class TmpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //System.out.println("--------------------filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(  ((HttpServletRequest)request).getSession().getId());
        System.out.println("--------------------filter excute");
        /*过器链路，将请求向下传递滤*/
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //System.out.println("--------------------filter destroy");
    }
}
