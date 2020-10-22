package com.summary.web;

import com.google.gson.Gson;
import com.summary.dao.AdminDao;
import com.summary.dao.ExamDao;
import com.summary.dao.UserDao;
import com.summary.entity.*;
import com.summary.service.AdminService;
import com.summary.service.Impl.AdminServiceImpl;
import com.summary.service.Impl.StudentServiceImpl;
import com.summary.service.Impl.TeacherServiceImpl;
import com.summary.service.Impl.UserServiceImpl;
import com.summary.service.StudentService;
import com.summary.service.TeacherService;
import com.summary.service.UserService;
import com.summary.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员的功能
 */


public class AdminController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        switch (uri){

            //模糊搜索对科目名称
            case "/admin/selectExamByName":
                doSelectExamByName(request,response);
                break;
            case "/admin/selectStuByName":
                doSelectStuByName(request,response);
                break;
            case "/admin/deleteUser":
                doDeleteUser(request,response);
                break;
//            case "/admin/deleteExam":
//                doDeleteExam(request,response);
//                break;
            default:
                doNULL(request,response);
                break;

        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        switch (uri) {
//            case "/admin/teacherPage":
//                doTeacherPage(request, response);
//                break;
            case "/admin/viewExamGradePage":
                doViewExamGradePage(request, response);
                break;
            case "/admin/viewStuGradePage":
                doStuGradePage(request, response);
                break;
            //查看学生的答题
            case "/admin/checkMyOptionFillAnswerPage":
                doCheckMyOptionAnswerPage(request, response);
                break;
            case "/admin/checkMyEssayAnswerPage":
                doCheckMyEssayAnswerPage(request, response);
                break;
            default:
                doNULL(request,response);
                break;

        }}

    /**
     * 删除老师
     * @param request
     * @param response
     * @throws IOException
     */
    private void doDeleteUser (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        User user = gson.fromJson(jsonString, User.class);
//        HttpSession session = request.getSession();
        UserService userService = new UserServiceImpl();
        UserDao userDao = new UserDao();
        int flag = userService.deleteUser(user);
//        if(user.getStatus()==0){
//            int flge1 = userDao.deleteStuMess(user.getId());
//        }else if(user.getStatus()==1)
//        {
//            int flge1 = userDao.deleteTeaMess(user.getId());
//        }
        Map map = new HashMap();
        if (flag == 0) {
            //返回值构造
            map.put("code", 0);
            map.put("msg", "删除失败");
        } else {
            //返回值构建
            Map data = new HashMap();

            map.put("code", 1);
            map.put("msg", "删除成功");
        }


        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();


    }


    /**
     * 从试卷名称查找
     * @param request
     * @param response
     * @throws IOException
     */
    private void doSelectExamByName(HttpServletRequest request, HttpServletResponse response)throws IOException {

        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        Exam exam = gson.fromJson(jsonString, Exam.class);
        AdminService adminService = new AdminServiceImpl();
        List<Exam> examList;
        String subject = exam.getSubject();
        examList = adminService.selectExamByName(subject);
        AdminDao adminDao = new AdminDao();
        int total = adminDao.selectExamByNameNum(subject);
        Map map = new HashMap();
        if(examList == null){
            map.put("code",0);
            map.put("msg","查询失败，没有想要的信息");
        } else  {
            map.put("code",1);
            map.put("total",total);
            map.put("data",examList);
            map.put("msg","查询这些试卷");
        }
        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();

    }

    /**
     * 从学生名称所有学生查找
     * @param request
     * @param response
     * @throws IOException
     */
    private void doSelectStuByName(HttpServletRequest request, HttpServletResponse response)throws IOException {

        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        User user = gson.fromJson(jsonString, User.class);
        AdminService adminService = new AdminServiceImpl();
        List<User> userList;
        String userName = user.getUserName();
        userList = adminService.selectStuByName(userName);
        AdminDao adminDao = new AdminDao();
        int total = adminDao.selectStuByNameNum(userName);
        Map map = new HashMap();
        if(userList == null){
            map.put("code",0);
            map.put("msg","查询失败，没有想要的信息");
        } else  {
            map.put("code",1);
            map.put("total",total);
            map.put("data",userList);
            map.put("msg","查询成功");
        }
        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();

    }


        /**
         * 学生选择答题情况页面渲染
         * @param request
         * @param response
         * @throws IOException
         */
    private void doCheckMyOptionAnswerPage(HttpServletRequest request, HttpServletResponse response)throws IOException {


        StudentService studentService = new StudentServiceImpl();
        ExamDao examDao = new ExamDao();
        int flag1 = examDao.updateIsfinish();
        int pageNum = Integer.decode(request.getParameter("pageNum"));
        int pageSize = Integer.decode(request.getParameter("pageSize"));
        int stu_id = Integer.decode(request.getParameter("stu_id"));
        int exam_id = Integer.decode(request.getParameter("exam_id"));

        Page<QuesOption> page = studentService.getCheckMyOptionAnswerPage(pageNum, pageSize, stu_id,exam_id);
        int total = 0;
        total = studentService.getCheckMyOptionAnswerPageNum(stu_id,exam_id);
        List<QuesOption> myCheckOptionList = new ArrayList<>();
        for (QuesOption quesOption : page.getList()) {
            myCheckOptionList.add(quesOption);
        }

        Page<QuesFill> page1 = studentService.getCheckMyFillAnswerPage(pageNum, pageSize, stu_id,exam_id);
        int total1 = studentService.getCheckMyFillPageNum(stu_id,exam_id);
        List<QuesFill> MyCheckFillList = new ArrayList<>();
        for(QuesFill quesFill : page1.getList()){

            MyCheckFillList.add(quesFill);
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("option", myCheckOptionList);
        map.put("optionTotal", total);
        map.put("fill", MyCheckFillList);
        map.put("fillTotal", total1);
        String jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
//        StudentService studentService = new StudentServiceImpl();
//        ExamDao examDao = new ExamDao();
//        int flag1 = examDao.updateIsfinish();
//        int pageNum = Integer.decode(request.getParameter("pageNum"));
//        int pageSize = Integer.decode(request.getParameter("pageSize"));
//        int stu_id = Integer.decode(request.getParameter("stu_id"));
//        int exam_id = Integer.decode(request.getParameter("exam_id"));
//
//        Page<QuesOption> page = studentService.getCheckMyOptionAnswerPage(pageNum, pageSize, stu_id,exam_id);
//        int total = 0;
//        total = studentService.getCheckMyOptionAnswerPageNum(stu_id,exam_id);
//        List<QuesOption> MyCheckOptionList = new ArrayList<>();
//        for (QuesOption quesOption : page.getList()) {
//            MyCheckOptionList.add(quesOption);
//        }
//        Gson gson = new Gson();
//        Map map = new HashMap();
//        map.put("data", MyCheckOptionList);
//        map.put("total", total);
//        String jsonString = gson.toJson(map);
//        PrintWriter out = response.getWriter();
//        out.print(jsonString);
//        out.flush();
    }

    /**
     * 学生问答答题情况页面渲染
     * @param request
     * @param response
     * @throws IOException
     */

    private void doCheckMyEssayAnswerPage(HttpServletRequest request, HttpServletResponse response)throws IOException {

        StudentService studentService = new StudentServiceImpl();
        int pageNum = Integer.decode(request.getParameter("pageNum"));
        int pageSize = Integer.decode(request.getParameter("pageSize"));
//        HttpSession session = request.getSession();
//        User user;
//        user = (User) session.getAttribute("user");
//        int stu_id = user.getId();
        int stu_id = Integer.decode(request.getParameter("stu_id"));
        int exam_id = Integer.decode(request.getParameter("exam_id"));

        Page<QuesEssay> page = studentService.getCheckMyEssayAnswerPage(pageNum, pageSize, stu_id,exam_id);
        int total = 0;
        total = studentService.getCheckMyEssayAnswerPageNum(stu_id,exam_id);
        List<QuesEssay> MyCheckEssayList = new ArrayList<>();
        for (QuesEssay quesEssay : page.getList()) {
            MyCheckEssayList.add(quesEssay);
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("data", MyCheckEssayList);
        map.put("total", total);
        String jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }


    /**
     * 管理员试卷所有成绩查询基本信息分页
     * @param request
     * @param response
     * @throws IOException
     */
    private void doViewExamGradePage(HttpServletRequest request, HttpServletResponse response)throws IOException {
        TeacherService teacherService = new TeacherServiceImpl();
        int pageNum = Integer.decode(request.getParameter("pageNum"));
        int pageSize = Integer.decode(request.getParameter("pageSize"));
        int exam_id =  Integer.decode(request.getParameter("exam_id"));
//        HttpSession session = request.getSession();
//        User user1;
//        user1 = (User) session.getAttribute("user");
//        int id = user1.getId();
//        int id = 2019000001;
        ExamDao examDao = new ExamDao();
        int flag1 = examDao.updateIsfinish();

        Page<Grade> page = teacherService.doViewStuGradePage(pageNum, pageSize, exam_id);
        int total = 0;
        total = teacherService.doViewStuGradePageNum(exam_id);
        List<Grade> MyViewStuGradeList = new ArrayList<>();
        for (Grade grade : page.getList()) {
            MyViewStuGradeList.add(grade);
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("data", MyViewStuGradeList);
        map.put("total", total);
        String jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }


    /**
     * 管理员学生所有成绩查询基本信息分页
     * @param request
     * @param response
     * @throws IOException
     */
    private void doStuGradePage(HttpServletRequest request, HttpServletResponse response)throws IOException {
        StudentService studentService = new StudentServiceImpl();
        int pageNum = Integer.decode(request.getParameter("pageNum"));
        int pageSize = Integer.decode(request.getParameter("pageSize"));
        int id = Integer.decode(request.getParameter("stu_id"));

//        HttpSession session = request.getSession();
//        User user1;
//        user1 = (User) session.getAttribute("user");
//        int id = user1.getId();
//        int id = 2019000001;
        ExamDao examDao = new ExamDao();
        int flag1 = examDao.updateIsfinish();

        Page<Grade> page = studentService.getStuGradePage(pageNum, pageSize, id);
        int total = 0;
        total = studentService.getStuGradePageNum(id);
        List<Grade> MyStuGradeList = new ArrayList<>();
        for (Grade grade : page.getList()) {
            MyStuGradeList.add(grade);
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("data", MyStuGradeList);
        map.put("total", total);
        String jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }





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

}
