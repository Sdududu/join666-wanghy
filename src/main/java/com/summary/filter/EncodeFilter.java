package com.summary.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodeFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String uri = request.getRequestURI();
//        if (uri.indexOf(".jpg") > 0 || uri.indexOf(".png") > 0
//                || uri.indexOf(".gif") > 0 || uri.indexOf(".css") > 0
//                || uri.indexOf("js") > 0||uri.indexOf(".img")>0) {
//            filterChain.doFilter(request, response);
//            return;
//        }

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");


        filterChain.doFilter(servletRequest,servletResponse);
        return;
    }

    @Override
    public void destroy() {

    }
}
