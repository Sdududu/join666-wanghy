package com.summary.filter;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ImgFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        if (uri.indexOf(".jpg") > 0 || uri.indexOf(".png") > 0
                || uri.indexOf(".gif") > 0 || uri.indexOf(".css") > 0
                || uri.indexOf("js") > 0||uri.indexOf(".img")>0) {
            filterChain.doFilter(request, response);
            return;
        }
        else{

            Gson gson = new Gson();
            String jsonString;
            Map map = new HashMap();
            map.put("code",4);
            map.put("msg","图片格式不对");
            jsonString = gson.toJson(map);
            PrintWriter out = response.getWriter();
            out.print(jsonString);  //给前端发送是否有用户信息的消息，没有前端跳转
            out.flush();

        }
        filterChain.doFilter(request, response);
        return;

    }

    @Override
    public void destroy() {

    }
}

