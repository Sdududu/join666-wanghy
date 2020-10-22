package com.summary.filter;

import com.google.gson.Gson;
import com.summary.entity.User;
import com.summary.service.Impl.UserServiceImpl;
import com.summary.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取用户登录信息,在点击链接时，会通过code(0为没有用户)值来判断有登录信息,如果没有用户信息则交给前端跳转到登录注册
        HttpSession session = request.getSession();
        User user;
        user = (User) session.getAttribute("user");
        Gson gson = new Gson();
        Map map = new HashMap();
        String jsonString;
        UserService userService= new UserServiceImpl();

        if(user==null){
            map.put("code", 5); //表示code为5没有登录无法查看
            map.put("msg", "没有进行登录");

        }
        else{
            int flag = 0;
            flag = userService.isUser(user);
            if(flag ==0 ){
                filterChain.doFilter(servletRequest, servletResponse);
                return;

            }
            else{

                map.put("code", 5); //表示code为5没有登录无法查看
                map.put("msg", "没有进行登录");

            }
        }
        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);  //给前端发送是否有用户信息的消息，没有前端跳转
        out.flush();
        //        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        //获取用户登录信息,在点击链接时，会通过code(0为没有用户)值来判断有登录信息,如果没有用户信息则交给前端跳转到登录注册
//        HttpSession session = request.getSession();
//        User user;
//        user = (User) session.getAttribute("user");
//        Gson gson = new Gson();
//        Map map = new HashMap();
//        String jsonString;
//        UserService userService= new UserServiceImpl();
//
//        int flag = 0;
//         flag = userService.isUser(user);
//        if(flag ==0 ){
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//
//        }
//        else{
//
//            map.put("code", 5); //表示code为5没有登录无法查看
//            map.put("msg", "没有进行登录");
//            jsonString = gson.toJson(map);
//            PrintWriter out = response.getWriter();
//            out.print(jsonString);  //给前端发送是否有用户信息的消息，没有前端跳转
//            out.flush();
//        }
//        if (user==null) {
//            map.put("code", 5); //表示code为5没有登录无法查看
//            map.put("msg", "没有进行登录");
//            jsonString = gson.toJson(map);
//            PrintWriter out = response.getWriter();
//            out.print(jsonString);  //给前端发送是否有用户信息的消息，没有前端跳转
//            out.flush();
//        } else {
//
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
    }
    @Override
    public void destroy () {

    }

}