package com.summary.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;
import com.summary.dao.AdminDao;
import com.summary.dao.ExamDao;
import com.summary.dao.QuestionDao;
import com.summary.entity.*;
import com.summary.service.AdminService;
import com.summary.service.Impl.AdminServiceImpl;
import com.summary.service.Impl.StudentServiceImpl;
import com.summary.service.Impl.TeacherServiceImpl;
import com.summary.service.StudentService;
import com.summary.service.TeacherService;
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
 * 学生的功能
 */

public class StudentController extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        switch (uri){

            case "/student/submitOptionFill":
                doSubmitOptionFill(request, response);
                break;
            case "/student/submitEssay":
                doSubmitEssay(request, response);
                break;
//            case "/student/selectTeacher":
//                doSelectTeacher(request,response);
//                break;
            case "/student/selectExamByName":
                doSelectExamByName(request,response);
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
            case "/student/examPage":
                doExamPage(request, response);
                break;

            case "/student/quesOptionFillPage":
                doQuesOptionFillPage(request, response);
                break;
            case "/student/EssayPage":
                doEssayPage(request, response);
                break;
            case "/student/checkMyOptionFillAnswerPage":
                doCheckMyOptionAnswerPage(request, response);
                break;
            case "/student/checkMyEssayAnswerPage":
                doCheckMyEssayAnswerPage(request, response);
                break;
            case "/student/gradePage":
                doGradePage(request, response);
                break;
//            case "/student/teacherPage":
//                doTeacherPage(request, response);
//                break;
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
     * 学生端试卷基本信息分页
     * @param request
     * @param response
     * @throws IOException
     */
    private void doExamPage(HttpServletRequest request, HttpServletResponse response)throws IOException {
        StudentService studentService = new StudentServiceImpl();
        int pageNum = Integer.decode(request.getParameter("pageNum"));
        int pageSize = Integer.decode(request.getParameter("pageSize"));
        HttpSession session = request.getSession();
        User user1;
        user1 = (User) session.getAttribute("user");
        int id = user1.getId();
//        int id = 2019000001;
        ExamDao examDao = new ExamDao();
        int flag1 = examDao.updateIsfinish();

        Page<Exam> page = studentService.getStuExamPage(pageNum, pageSize, id);
        int total = 0;
        total = studentService.getStuExamPageNum(id);
        List<Exam> MyStuExamList = new ArrayList<>();
        for (Exam exam : page.getList()) {
            MyStuExamList.add(exam);
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("data", MyStuExamList);
        map.put("total", total);
//        map.put("User",user1);
        String jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 学生端选择题渲染
     * @param request
     * @param response
     * @throws IOException
     */
    private void doQuesOptionFillPage(HttpServletRequest request, HttpServletResponse response)throws IOException {
        HttpSession session = request.getSession();
        User user;
        user = (User) session.getAttribute("user");
        int id = user.getId();
//        int exam_id= 2009000001;
        StudentService studentService = new StudentServiceImpl();
        ExamDao examDao = new ExamDao();
        int flag1 = examDao.updateIsfinish();
        int pageNum = Integer.decode(request.getParameter("pageNum"));
        int pageSize = Integer.decode(request.getParameter("pageSize"));
        int exam_id = Integer.decode(request.getParameter("exam_id"));
        Page<QuesOption> page = studentService.getStuOptionPage(pageNum, pageSize, exam_id);
        int total = 0;
        total = studentService.getStuOptionPageNum(exam_id);
        //学生开始考试
        int succeed = examDao.isCompleteNow(exam_id,id);
        List<QuesOption> MyStuOptionList = new ArrayList<>();
        for (QuesOption quesOption : page.getList()) {
            MyStuOptionList.add(quesOption);
        }

        Page<QuesFill> page1 = studentService.getStuFillPage(pageNum, pageSize, exam_id);
        int total1 = studentService.getStuFillPageNum(exam_id);
        List<QuesFill> MyStuFillList = new ArrayList<>();
        for(QuesFill quesFill : page1.getList()){

            MyStuFillList.add(quesFill);
        }

        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("Option", MyStuOptionList);
        map.put("Fill", MyStuFillList);
        map.put("optionTotal", total);
        map.put("fillTotal",total1);

        String jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 问答题页面渲染
     * @param request
     * @param response
     * @throws IOException
     */
    private void doEssayPage(HttpServletRequest request, HttpServletResponse response)throws IOException {

        StudentService studentService = new StudentServiceImpl();
        int pageNum = Integer.decode(request.getParameter("pageNum"));
        int pageSize = Integer.decode(request.getParameter("pageSize"));
        int exam_id = Integer.decode(request.getParameter("exam_id"));
        Page<QuesEssay> page = studentService.getStuEssayPage(pageNum, pageSize, exam_id);
        int total = 0;
        total = studentService.getStuEssayPageNum(exam_id);
        List<QuesEssay> MyStuEssayList = new ArrayList<>();
        for (QuesEssay quesEssay : page.getList()) {
            MyStuEssayList.add(quesEssay);
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("data", MyStuEssayList);
        map.put("total", total);
        String jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }


    /**
     * 学生端选择答题情况页面渲染
     * @param request
     * @param response
     * @throws IOException
     */
    private void doCheckMyOptionAnswerPage(HttpServletRequest request, HttpServletResponse response)throws IOException {
//        HttpSession session = request.getSession();
//        User user;
//        user = (User) session.getAttribute("user");
//        int id = user.getId();
//        int exam_id= 2009000001;
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
        List<QuesOption> MyCheckOptionList = new ArrayList<>();
        for (QuesOption quesOption : page.getList()) {
            MyCheckOptionList.add(quesOption);
        }

        Page<QuesFill> page1 = studentService.getCheckMyFillAnswerPage(pageNum, pageSize, stu_id,exam_id);
        int total1 = studentService.getCheckMyFillPageNum(stu_id,exam_id);
        List<QuesFill> MyCheckFillList = new ArrayList<>();
        for(QuesFill quesFill : page1.getList()){

            MyCheckFillList.add(quesFill);
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("option", MyCheckOptionList);
        map.put("optionTotal", total);
        map.put("fill", MyCheckFillList);
        map.put("fillTotal", total1);
        String jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 学生端问答答题情况页面渲染
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
     * 学生端所有成绩查询基本信息分页
     * @param request
     * @param response
     * @throws IOException
     */
    private void doGradePage(HttpServletRequest request, HttpServletResponse response)throws IOException {
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
     * 学生提交单选题
     * @param request QuesOption
     * @param response
     * @throws IOException
     */

    private void doSubmitOptionFill(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        StudentService studentService = new StudentServiceImpl();
        //解析成对象
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        //再从对象中解析多数组
        JSONArray QuesOptions= (JSONArray) jsonObject.get("QuesOption");
        JSONArray QuesFills= (JSONArray) jsonObject.get("QuesFill");
        //将数组解析
        List<QuesOption> optionAnswerLists = JSONArray.parseArray(QuesOptions.toJSONString(), QuesOption.class);
        List<QuesFill>  fillAnswerLists = JSONArray.parseArray(QuesFills.toJSONString(), QuesFill.class);
//        int exam_id = jsonString
        double op_grade = 0;
        HttpSession session = request.getSession();
        User user;
        user = (User) session.getAttribute("user");
        int stu_id = user.getId();
//        int stu_id = 2019000001;
//选择题
        for(QuesOption optionAnswerList : optionAnswerLists){
            QuestionDao questionDao = new QuestionDao();
            //获得选择题的内容
            QuesOption quesOption = questionDao.selectQuesOption(optionAnswerList);
            //，从获得的选择题答案中，判断是否正确
            boolean flag1 = (quesOption.getAnswer()).equals(optionAnswerList.getOp_res()) ;
            int flag2 = 0;
            if (flag1){
                int flag = 0;
                //如果是真的的，那么定义为1(isright),加入到数据库中  //加入
                flag = studentService.addMyOption(optionAnswerList,stu_id,1);
                flag2 = 1;
            }else{
                int flag = 0;
                flag = studentService.addMyOption(optionAnswerList,stu_id,0);

            }
            //计算选择题的所有答案
            op_grade = quesOption .getOp_score()* flag2 + op_grade;

        }
//填空题
        double fill_grade=0;
        for(QuesFill fillAnswerList : fillAnswerLists){
            QuestionDao questionDao = new QuestionDao();
            QuesFill quesFill = questionDao.selectQuesFill(fillAnswerList);
            boolean flag3 = (quesFill.getFill_answer()).equals(fillAnswerList.getFill_res()) ;
            int flag4 = 0;
            if (flag3){

                //加入  //如果是真的的，那么定义为1(isright),加入到数据库中
                int flag = studentService.addMyFill(fillAnswerList,stu_id,1);
                flag4 = 1;
            }else{

                int flag = studentService.addMyFill(fillAnswerList,stu_id,0);

            }
            //计算选择题的所有答案
            fill_grade = quesFill .getFill_score()* flag4 + fill_grade;

        }




        int exam_id = getExam_idFromList(optionAnswerLists);

        ExamDao examDao = new ExamDao();
        //获得成绩
        int succeed = examDao.insertGradeOp(stu_id , exam_id , op_grade,fill_grade);
//        int succeed = examDao.insertGradeOp(stu_id , exam_id , op_grade;
        Map map = new HashMap();
        map.put("code",1);
        map.put("op_grade",op_grade);
        map.put("fill_grade",fill_grade);
        map.put("msg","添加成功");
        Gson gson = new Gson();
        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();

    }
    private int getExam_idFromList(List<QuesOption> optionLists){
        for(QuesOption optionList : optionLists){
            return optionList.getExam_id();
        }
           return 0 ;
    }


    /**
     * 学生提交主观题(顺便给老师)
     * @param request QuesEssay
     * @param response
     * @throws IOException
     */

    private void doSubmitEssay(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        StudentService studentService = new StudentServiceImpl();
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        JSONArray QuesEssays= (JSONArray) jsonObject.get("QuesEssay");
        List<QuesEssay> essayAnswerLists = JSONArray.parseArray(QuesEssays.toJSONString(), QuesEssay.class);

        int flag = 0;
        HttpSession session = request.getSession();
        User user;
        user = (User) session.getAttribute("user");
        int stu_id = user.getId();
//        int stu_id = 2019000001;
        ExamDao examDao = new ExamDao();
        for(QuesEssay essayAnswerList : essayAnswerLists){
            flag = examDao.addMyEssay(essayAnswerList,stu_id);

        }
        int exam_id = getExamFromEsList(essayAnswerLists);
        int succeed = examDao.isCompleteStu(exam_id,stu_id);
        Map map = new HashMap();
        if(flag != 0)
        {

            map.put("code",1);
            map.put("msg","提交成功");
        }else{
            map.put("code",0);
            map.put("msg","提交失败");
        }
        Gson gson = new Gson();
        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();

    }

    private int getExamFromEsList(List<QuesEssay> essayLists){
        for(QuesEssay essayList : essayLists){
            return essayList.getExam_id();
        }
        return 0 ;
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


}
