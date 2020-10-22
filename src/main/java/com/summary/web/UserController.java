package com.summary.web;

import com.google.gson.Gson;
import com.summary.dao.UserDao;
import com.summary.entity.User;
import com.summary.service.Impl.UserServiceImpl;
import com.summary.service.UserService;
import com.summary.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
/**
 * 用户的功能
 */

public class UserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        switch (uri){
            case "/user/login":
                doLogin(request,response);
                break;
            case "/user/register":
                doRegister(request,response);
                break;
            case "/user/update":
                doUpdate(request,response);
                break;
            case "/user/getUser":
                doGetUser(request,response);
                break;
            default:
                doNULL(request,response);
                break;
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        switch (uri) {
            case "/user/LogOut":
                doLogout(request, response);
                break;
            default:
                doNULL(request,response);
                break;
        }}





    /**
     * 不存在这个
     * @param request
     * @param response
     * @throws IOException
     */
    private void doNULL(HttpServletRequest request, HttpServletResponse response)throws IOException {

        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("code",6);
        map.put("msg","该页面不存在");

        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();

    }
    /**
     * 用户登录
     * @param request
     * @param response
     * @throws IOException
     */
        private void doLogin (HttpServletRequest request, HttpServletResponse response) throws IOException {
            String jsonString = JSONUtil.getJson(request);
            Gson gson = new Gson();
            User user = gson.fromJson(jsonString, User.class);
            HttpSession session = request.getSession();
            UserService userService = new UserServiceImpl();

            User user1 = userService.userLogin(user);
            Map map = new HashMap();
            if (user1 == null) {
                //返回值构造
                map.put("code", 0);
                map.put("msg", "用户不存在，登录失败");
            } else {
                //返回值构建
                Map data = new HashMap();
                data.put("user", user1);

                map.put("code", 1);
                map.put("data", data);
                session.setAttribute("user", user1);
            }


            jsonString = gson.toJson(map);
            PrintWriter out = response.getWriter();
            out.print(jsonString);
            out.flush();


        }

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws IOException
     */

        private void doRegister (HttpServletRequest request, HttpServletResponse response)throws IOException {
            Map map = new HashMap();
            String jsonString = JSONUtil.getJson(request);
            Gson gson = new Gson();
            User user = gson.fromJson(jsonString, User.class);
            UserService userService = new UserServiceImpl();
            int flag = 0;
            flag = userService.userRegister(user);
            UserDao userDao = new UserDao();
            User user1 = userDao.getUserId(user);
            if (flag != 0) {
                //返回值构造
                Map data = new HashMap();
                data.put("user", user1);

                map.put("code", 1);
                map.put("data", data);
            } else {
                //返回值构建
                map.put("code", 0);
                map.put("msg", "用户注册失败");
            }
            jsonString = gson.toJson(map);
            PrintWriter out = response.getWriter();
            out.print(jsonString);
            out.flush();

        }

    /**
     * 更新用户信息
     * @param request
     * @param response
     * @throws IOException
     */
    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        User user = gson.fromJson(jsonString, User.class);
        HttpSession session = request.getSession();
        UserService userService = new UserServiceImpl();
        int flag = 0;
        flag = userService.userUpdate(user);

        Map map = new HashMap();
        if (flag == 0) {
            //返回值构造
            map.put("code", 0);
            map.put("msg", "用户更改失败");
        } else {
            //返回值构建
            map.put("code", 1);
            map.put("msg", "用户更改成功");
            session.setAttribute("user", user);
        }


        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();


    }

    /**
     * 获得用户的信息
     * @param request
     * @param response
     * @throws IOException
     */
    private void doGetUser (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        User user = gson.fromJson(jsonString, User.class);
        HttpSession session = request.getSession();
        UserService userService = new UserServiceImpl();

        User user1;
        user1 = (User) session.getAttribute("user");

        Map map = new HashMap();
        if (user1 == null) {
            //返回值构造
            map.put("code", 0);
            map.put("msg", "用户不存在，没有获取成功");
        } else {
            //返回值构建
            Map data = new HashMap();
            data.put("user", user1);

            map.put("code", 1);
            map.put("data", data);
            session.setAttribute("user", user1);
        }


        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();


    }

    /**
     * 注销用户
     * @param request
     * @param response
     * @throws IOException
     */

        public void doLogout (HttpServletRequest request, HttpServletResponse response) throws IOException {
            Gson gson = new Gson();
            HttpSession session = request.getSession();
//            session.invalidate();
//            session.removeAttribute("user");
            session.setAttribute("user", null);

            User user =(User) session.getAttribute("user");
            Map map = new HashMap();
            if(user == null ){
                map.put("msg", "退出登陆成功");
                map.put("code", 1);
            }
            else{

                map.put("msg", "退出登陆成功");
                map.put("code", 0);
            }
            String jsonString = gson.toJson(map);
            PrintWriter out = response.getWriter();
            out.println(jsonString);
            out.flush();
        }


    }


