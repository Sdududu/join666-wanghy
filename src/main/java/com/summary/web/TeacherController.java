package com.summary.web;
import com.google.gson.Gson;
import com.summary.dao.AdminDao;
import com.summary.dao.ExamDao;
import com.summary.dao.QuestionDao;
import com.summary.dao.UserDao;
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
 * 老师的功能
 */

public class TeacherController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        switch (uri){
            //试卷
            case "/teacher/addExam":
                doAddExam(request,response);
                break;
            case "/teacher/deleteExam":
                doDeleteExam(request,response);
                break;
            case "/teacher/updateExam":
                doUpdateExam(request,response);
                break;
             //选择题
            case "/teacher/addOption":
                doAddOption(request,response);
                break;
            case "/teacher/deleteOption":
                doDeleteQuesOption(request,response);
                break;
            case "/teacher/updateOption":
                doUpdateOption(request,response);
                break;
            //填空题
            case "/teacher/addFill":
                doAddFill(request,response);
                break;
            case "/teacher/deleteFill":
                doDeleteFill(request,response);
                break;
            case "/teacher/updateFill":
                doUpdateFill(request,response);
                break;
            //问答题
            case "/teacher/addEssay":
                doAddEssay(request,response);
                break;
            case "/teacher/deleteEssay":
                doDeleteEssay(request,response);
                break;
            case "/teacher/updateEssay":
                doUpdateEssay(request,response);
                break;
             //老师批改问答题
            case "/teacher/MarkEssay":
                doMarkEssay(request,response);
                break;
            case "/teacher/MarkEssayFinish":
                doMarkEssayFinish(request,response);
                break;
            //模糊查询
            case "/teacher/selectStuByGrade" :
               doSelectStuByGrade(request,response);
               break;
            case "/teacher/choiceStu":
                doChoiceStu(request,response);
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
            case "/teacher/examPage":
                doExamPage(request, response);
                break;
            case "/teacher/quesOptionFillPage":
                doQuesOptionFillPage(request, response);
                break;
            case "/teacher/EssayPage":
                doEssayPage(request, response);
                break;
                //查看学生
            case "/teacher/viewStuNowPage":
                doViewStuNowPage(request,response);
                break;
               //查看学生的答题
            case "/teacher/checkMyOptionFillAnswerPage":
                doCheckMyOptionAnswerPage(request, response);
                break;
            case "/teacher/checkMyEssayAnswerPage":
                doCheckMyEssayAnswerPage(request, response);
                break;
//            //查看学生成绩
            case "/teacher/viewStuGradePage":
                doViewStuGradePage(request,response);
                break;
            case "/teacher/viewStuGradeAllPage":
                doViewStuGradeAllPage(request,response);
                break;
             //所有学生页面渲染
            case "/teacher/viewStuPage" :
                doViewStuPage(request,response);
                break;
            //根据试卷信息查看没有批改的学生
            case "/teacher/viewStuByNoCheck" :
                 doViewStuByNoCheck(request,response);
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
     * 添加一张新试卷
     * @param request
     * @param response
     * @throws IOException
     */
    private void doAddExam(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        Exam exam = gson.fromJson(jsonString, Exam.class);
        TeacherService teacherService = new TeacherServiceImpl();
        HttpSession session = request.getSession();
        User user;
        user = (User) session.getAttribute("user");
        int id = user.getId();
//        int id = 2009000001;

        int flag = teacherService.addExam(exam,id);
        ExamDao examDao = new ExamDao();
        Exam exam1 = new Exam();
        exam1 = examDao.selectExamId(exam);
        int flag1 = examDao.addExamIdInStu(exam1,id);
        Map map = new HashMap();
        if((flag == 0)|| (flag1)==0){

            map.put("code",0);
            map.put("msg","添加失败，再次尝试");
            jsonString = gson.toJson(map);
        }

        else {
            if(exam1 !=null)
            {
                Map data = new HashMap();
                data.put("exam",exam1);
                map.put("code", 1);

                map.put("data",data);
                jsonString = gson.toJson(map);
            }
            else{
                map.put("code",0);
                map.put("msg","添加失败，再次尝试");
                jsonString = gson.toJson(map);
            }


        }

        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 删除一张新试卷
     * @param request
     * @param response
     * @throws IOException
     */
    private void doDeleteExam(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        Exam exam = gson.fromJson(jsonString, Exam.class);
        TeacherService teacherService = new TeacherServiceImpl();
        int exam_id = exam.getExam_id();

        int flag1 =0;// 试卷数据库删除
        Map map = new HashMap();

        flag1 = teacherService.deleteExam(exam_id);
        ExamDao examDao = new ExamDao();
        int flag = examDao.deleteExamInStu(exam_id);
        if(flag1!=0)
        {
            map.put("code", 1);
            map.put("msg", "删除成功");

        }
        else{
            map.put("code",0);
            map.put("msg","删除失败");
        }

         jsonString = gson.toJson(map);


        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 更改试卷信息
     * @param request
     * @param response
     * @throws IOException
     */
    private void doUpdateExam(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        Exam exam = gson.fromJson(jsonString, Exam.class);
        TeacherService teacherService = new TeacherServiceImpl();
        int flag = 0;
        flag = teacherService.updateExam(exam);

            Map map = new HashMap();
            if (flag ==0) {
                map.put("code", 0);
                map.put("msg", "修改失败，再次尝试");
            } else {
                map.put("code", 1);
                map.put("msg", "修改成功");
            }

        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }


    /**
     * 添加新的一题选择题
     * @param request
     * @param response
     * @throws IOException
     */
    private void doAddOption(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        QuesOption quesOption = gson.fromJson(jsonString, QuesOption.class);
        TeacherService teacherService = new TeacherServiceImpl();
        Map map = new HashMap();
        int flag = 0;
        //比较题目是否重复
        if(flag==0) {
            int flag1= 0;
            flag1 = teacherService.addQuesOption(quesOption);
            //比较加入成功
            if(flag1 ==1){

                QuestionDao questionDao = new QuestionDao();
                QuesOption quesOption1 = questionDao.selectQuesOption(quesOption);
                //找出所以数据
                if (quesOption1 != null) {
                    //返回值构建
                    Map data = new HashMap();
                    data.put("quesOption1", quesOption1);

                    map.put("code", 1);
                    map.put("data", data);

                } else {
                    map.put("code", 0);
                    map.put("msg", "选择题添加失败，再次尝试");
                }
            }
            else{
                map.put("code", 0);
                map.put("msg", "选择题添加失败，再次尝试");
            }

        }
        else{
            map.put("code", 0);
            map.put("msg", "题目序号不能重复");
        }
        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 删除一张选择题
     * @param request
     * @param response
     * @throws IOException
     */
    private void doDeleteQuesOption(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        QuesOption quesOption = gson.fromJson(jsonString, QuesOption.class);
        TeacherService teacherService = new TeacherServiceImpl();

        // 题目数据库删除
        int flag1 =0;
        Map map = new HashMap();

        flag1 = teacherService.deleteQuesOption(quesOption);

        if(flag1!=0)
        {
            map.put("code", 1);
            map.put("msg", "删除成功");

        }
        else{
            map.put("code",0);
            map.put("msg","删除失败，再次尝试");
        }

        jsonString = gson.toJson(map);


        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 更改题目信息
     * @param request
     * @param response
     * @throws IOException
     */
    private void doUpdateOption(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        QuesOption quesOption = gson.fromJson(jsonString, QuesOption.class);
        TeacherService teacherService = new TeacherServiceImpl();

        Map map = new HashMap();
        int flag1 = 0;
//        flag1 = teacherService.isRepeatNumber(quesOption.getExam_id(),quesOption.getNumber());
        if(flag1 == 0) {


            int flag = 0;
            flag = teacherService.updateQuesOption(quesOption);
            if (flag == 0) {
                map.put("code", 0);
                map.put("msg", "选择题更改失败，再次尝试");
            } else {
                QuestionDao questionDao = new QuestionDao();
                QuesOption quesOption1 = questionDao.selectQuesOption(quesOption);

                //找出所以数据
                if (quesOption1 != null) {
                    //返回值构建
                    Map data = new HashMap();
                    data.put("quesOption1", quesOption1);

                    map.put("code", 0);
                    map.put("data", data);

                } else {
                    map.put("code", 0);
                    map.put("msg", "选择题添加失败，再次尝试");
                }

            }
        }else{
            map.put("code", 0);
            map.put("msg", "题目序号不能重复");
        }

        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }



    /**
     * 添加新的一题填空题
     * @param request
     * @param response
     * @throws IOException
     */
    private void doAddFill(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        QuesFill quesFill = gson.fromJson(jsonString, QuesFill.class);
        TeacherService teacherService = new TeacherServiceImpl();
        Map map = new HashMap();
        int flag = 0;
        flag = teacherService.isRepeatNumberFill(quesFill.getExam_id(),quesFill.getNumber());

        if(flag==0) {  //比较题目是否重复
            int flag1= 0;
            flag1 = teacherService.addQuesFill(quesFill);
            if(flag1 ==1){ //比较加入成功

                QuestionDao questionDao = new QuestionDao();
                QuesFill quesFill1 = questionDao.selectQuesFill(quesFill);

                //找出所以数据
                if (quesFill1 != null) {
                    //返回值构建
                    Map data = new HashMap();
                    data.put("quesFill1", quesFill1);

                    map.put("code", 1);
                    map.put("data", data);

                } else {
                    map.put("code", 0);
                    map.put("msg", "选择题添加失败，再次尝试");
                }
            }
            else{
                map.put("code", 0);
                map.put("msg", "选择题添加失败，再次尝试");
            }

        }
        else{
            map.put("code", 2);
            map.put("msg", "题目序号不能重复");
        }
        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 删除一张问答题
     * @param request
     * @param response
     * @throws IOException
     */
    private void doDeleteFill(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        QuesFill quesFill = gson.fromJson(jsonString, QuesFill.class);
        TeacherService teacherService = new TeacherServiceImpl();

        int flag1 =0;// 题目数据库删除
        Map map = new HashMap();

        flag1 = teacherService.deleteQuesFill(quesFill);

        if(flag1!=0)
        {
            map.put("code", 1);
            map.put("msg", "删除成功");

        }
        else{
            map.put("code",0);
            map.put("msg","删除失败，再次尝试");
        }

        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 更改题目信息
     * @param request
     * @param response
     * @throws IOException
     */
    private void doUpdateFill(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        QuesFill quesFill = gson.fromJson(jsonString, QuesFill.class);
        TeacherService teacherService = new TeacherServiceImpl();

        Map map = new HashMap();
        int flag1 = 0;
//        flag1 = teacherService.isRepeatNumber(quesFill.getExam_id(),quesFill.getNumber());
        if(flag1 == 0) {


            int flag = 0;
            flag = teacherService.updateQuesFill(quesFill);
            if (flag == 0) {
                map.put("code", 0);
                map.put("msg", "填空题更改失败，再次尝试");
            } else {
                QuestionDao questionDao = new QuestionDao();
                QuesFill quesFill1 = questionDao.selectQuesFill(quesFill);

                if (quesFill1 != null) { //找出所以数据
                    //返回值构建
                    Map data = new HashMap();
                    data.put("quesFill1", quesFill1);

                    map.put("code", 0);
                    map.put("data", data);

                } else {
                    map.put("code", 0);
                    map.put("msg", "填空题添加失败，再次尝试");
                }

            }
        }else{
            map.put("code", 0);
            map.put("msg", "题目序号不能重复");
        }

        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 添加新的一题问答题
     * @param request
     * @param response
     * @throws IOException
     */
    private void doAddEssay(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        QuesEssay quesEssay = gson.fromJson(jsonString, QuesEssay.class);
        TeacherService teacherService = new TeacherServiceImpl();
        Map map = new HashMap();
        int flag = 0;
//        if((quesOption.getNumber())==1){
//            flag = 1;  //一般以第一题为主，若是第一题可以直接添加
//        }
//        else{
        flag = teacherService.isRepeatNumberEss(quesEssay.getExam_id(),quesEssay.getNumber());

//        }
        //比较题目是否重复
        if(flag==0) {
            int flag1= 0;
            flag1 = teacherService.addQuesEssay(quesEssay);
            //比较加入成功
            if(flag1 !=0 ){

                QuestionDao questionDao = new QuestionDao();
                QuesEssay quesEssay1 = questionDao.selectQuesEssay(quesEssay);

                if (quesEssay1 != null) { 
                    //返回值构建
                    Map data = new HashMap();
                    data.put("quesEssay1", quesEssay1);

                    map.put("code", 1);
                    map.put("data", data);

                } else {
                    map.put("code", 0);
                    map.put("msg", "选择题添加失败，再次尝试");
                }
            }
            else{
                map.put("code", 0);
                map.put("msg", "选择题添加失败，再次尝试");
            }

        }
        else{
            map.put("code", 0);
            map.put("msg", "题目序号不能重复");
        }
        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 删除一张问答题
     * @param request
     * @param response
     * @throws IOException
     */
    private void doDeleteEssay(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        QuesEssay quesEssay = gson.fromJson(jsonString, QuesEssay.class);
        TeacherService teacherService = new TeacherServiceImpl();

        // 题目数据库删除
        int flag1 =0;
        Map map = new HashMap();

        flag1 = teacherService.deleteQuesEssay(quesEssay);

        if(flag1!=0)
        {
            map.put("code", 1);
            map.put("msg", "删除成功");

        }
        else{
            map.put("code",0);
            map.put("msg","删除失败，再次尝试");
        }

        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 更改问答题
     * @param request
     * @param response
     * @throws IOException
     */
    private void doUpdateEssay(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        QuesEssay quesEssay = gson.fromJson(jsonString, QuesEssay.class);
        TeacherService teacherService = new TeacherServiceImpl();

        Map map = new HashMap();
        int flag1 = 0;
//        flag1 = teacherService.isRepeatNumberEss(quesEssay.getExam_id(),quesEssay.getNumber());
        if(flag1 == 0) {


            int flag = 0;
            flag = teacherService.updateQuesEssay(quesEssay);
            if (flag == 0) {
                map.put("code", 0);
                map.put("msg", "选择题更改失败，再次尝试");
            } else {
                QuestionDao questionDao = new QuestionDao();
                QuesEssay quesEssay1 = questionDao.selectQuesEssay(quesEssay);

                if (quesEssay1 != null) {
                    //返回值构建
                    Map data = new HashMap();
                    data.put("quesEssay1", quesEssay1);

                    map.put("code", 0);
                    map.put("data", data);

                } else {
                    map.put("code", 0);
                    map.put("msg", "选择题添加失败，再次尝试");
                }

            }
        }else{
            map.put("code", 0);
            map.put("msg", "题目序号不能重复");
        }

        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }
    /**
     * 批改题目
     * @param request
     * @param response
     * @throws IOException
     */
    private void doMarkEssay(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        QuesEssay quesEssay = gson.fromJson(jsonString, QuesEssay.class);
        TeacherService teacherService = new TeacherServiceImpl();
        int flag = 0;
        flag = teacherService.markEssay(quesEssay);

        Map map = new HashMap();
        if (flag ==0) {
            map.put("code", 0);
            map.put("msg", "批改失败，再次尝试");
        } else {
            map.put("code", 1);
            map.put("msg", "批改成功");
        }

        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 老师选择学生
     * @param request
     * @param response
     * @throws IOException
     */
    private void doChoiceStu(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        HttpSession session = request.getSession();
        User user1;
        user1 = (User) session.getAttribute("user");
        int id = user1.getId();
        User student = gson.fromJson(jsonString, User.class);
        UserDao userDao = new UserDao();
        int stu_id = student.getId();
        int flag = userDao.addChioceStu(stu_id,id);
        Map map = new HashMap();
        if(flag != 0 )
        {
            map.put("code", 1);
            map.put("msg", "添加成功");

        }
        else{
            map.put("code",0);
            map.put("msg","添加失败，再次尝试");
        }

        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 批改当前试卷完成题目
     * @param request
     * @param response
     * @throws IOException
     */
    private void doMarkEssayFinish(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        QuesEssay quesEssay = gson.fromJson(jsonString, QuesEssay.class);
        TeacherService teacherService = new TeacherServiceImpl();
        QuestionDao questionDao = new QuestionDao();
        double es_grades = questionDao.getEssGrades(quesEssay);
         int flag1 = questionDao.addEsGrades(es_grades,quesEssay.getExam_id(),quesEssay.getStu_id());
        int flag = 0;
        double fill_grade= questionDao.getFillGrade(quesEssay.getExam_id(),quesEssay.getStu_id());
        double op_grade= questionDao.getOptionGrade(quesEssay.getExam_id(),quesEssay.getStu_id());
        double grade= es_grades+fill_grade + op_grade;
        flag = teacherService.markEssayFinish(quesEssay,grade);//批改完成，可以显示成绩了

        Map map = new HashMap();
        if (flag ==0) {
            map.put("code", 0);
            map.put("msg", "修改失败，再次尝试");
        } else {
            map.put("code", 1);
            map.put("msg", "修改成功");
        }

        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 查看当前考试的人数信息，以及没考试的人数
     * @param request
     * @param response
     * @throws IOException
     */
    private void doViewStuNowPage(HttpServletRequest request, HttpServletResponse response)throws IOException {
        TeacherService teacherService = new TeacherServiceImpl();
        int pageNum = Integer.decode(request.getParameter("pageNum"));
        int pageSize = Integer.decode(request.getParameter("pageSize"));
//        HttpSession session = request.getSession();
//        User user1;
//        user1 = (User) session.getAttribute("user");
//        int id = user1.getId();

        int exam_id = Integer.decode(request.getParameter("exam_id"));
        ExamDao examDao = new ExamDao();

        int peopleNow = examDao.viewPeopleNow(exam_id);
        int unDone = examDao.viewUndone(exam_id);

        Page<User> page = teacherService.getViewUndonePage(pageNum, pageSize, exam_id);
        List<User> MyStuIdList = new ArrayList<>();
        for (User user : page.getList()) {
            MyStuIdList.add(user);
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("data", MyStuIdList);
        map.put("peopleNow",peopleNow );
        map.put("unDone",unDone);
        String jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 老师端试卷基本信息分页
     * @param request
     * @param response
     * @throws IOException
     */
    private void doExamPage(HttpServletRequest request, HttpServletResponse response)throws IOException {
        TeacherService teacherService = new TeacherServiceImpl();
        int pageNum = Integer.decode(request.getParameter("pageNum"));
        int pageSize = Integer.decode(request.getParameter("pageSize"));
        HttpSession session = request.getSession();
        User user1;
        user1 = (User) session.getAttribute("user");
        int id = user1.getId();

//        int id = 2009000001;
        ExamDao examDao = new ExamDao();
        //判断是否失效
        int flag1 = examDao.updateIsfinish();
        Page<Exam> page = teacherService.getTeaExamPage(pageNum, pageSize, id);
        int total = 0;
        total = teacherService.getTeaExamPageNum(id);
        List<Exam> MyTeaExamList = new ArrayList<>();
        for (Exam exam : page.getList()) {
            MyTeaExamList.add(exam);
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("data", MyTeaExamList);
        map.put("total", total);
        String jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 老师端选择题渲染
     * @param request
     * @param response
     * @throws IOException
     */
    private void doQuesOptionFillPage(HttpServletRequest request, HttpServletResponse response)throws IOException {
//        HttpSession session = request.getSession();
//        User user;
//        user = (User) session.getAttribute("user");
//        int id = user.getId();
//        int exam_id= 2009000001;
        TeacherService teacherService = new TeacherServiceImpl();
        int pageNum = Integer.decode(request.getParameter("pageNum"));
        int pageSize = Integer.decode(request.getParameter("pageSize"));
        int exam_id = Integer.decode(request.getParameter("exam_id"));
        Page<QuesOption> page = teacherService.getTeaOptionPage(pageNum, pageSize, exam_id);
        int total = 0;
        total = teacherService.getTeaOptionPageNum(exam_id);
        List<QuesOption> MyTeaOptionList = new ArrayList<>();
        for (QuesOption quesOption : page.getList()) {
            MyTeaOptionList.add(quesOption);
        }

        StudentService studentService = new StudentServiceImpl();
        Page<QuesFill> page1 = studentService.getStuFillPage(pageNum, pageSize, exam_id);
        int total1 = studentService.getStuFillPageNum(exam_id);
        List<QuesFill> MyTeaFillList = new ArrayList<>();
        for(QuesFill quesFill : page1.getList()){

            MyTeaFillList.add(quesFill);
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("Option", MyTeaOptionList);
        map.put("Fill", MyTeaFillList);
        map.put("optionTotal", total);
        map.put("fillTotal",total1);
        String jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    private void doEssayPage(HttpServletRequest request, HttpServletResponse response)throws IOException {

        TeacherService teacherService = new TeacherServiceImpl();
        int pageNum = Integer.decode(request.getParameter("pageNum"));
        int pageSize = Integer.decode(request.getParameter("pageSize"));
        int exam_id = Integer.decode(request.getParameter("exam_id"));
        Page<QuesEssay> page = teacherService.getTeaEssayPage(pageNum, pageSize, exam_id);
        int total = 0;
        total = teacherService.getTeaEssayPageNum(exam_id);
        List<QuesEssay> MyTeaEssayList = new ArrayList<>();
        for (QuesEssay quesEssay : page.getList()) {
            MyTeaEssayList.add(quesEssay);
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("data", MyTeaEssayList);
        map.put("total", total);
        String jsonString = gson.toJson(map);
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
     * 老师端所有成绩查询根据试卷分页
     * @param request
     * @param response
     * @throws IOException
     */
    private void doViewStuGradePage(HttpServletRequest request, HttpServletResponse response)throws IOException {
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
     * 老师端所有成绩所有
     * @param request
     * @param response
     * @throws IOException
     */

    private void doViewStuGradeAllPage(HttpServletRequest request, HttpServletResponse response)throws IOException {
        TeacherService teacherService = new TeacherServiceImpl();
        int pageNum = Integer.decode(request.getParameter("pageNum"));
        int pageSize = Integer.decode(request.getParameter("pageSize"));
        HttpSession session = request.getSession();
        User user1;
        user1 = (User) session.getAttribute("user");
        int id = user1.getId();
//        int id = 2019000001;
        ExamDao examDao = new ExamDao();
        int flag1 = examDao.updateIsfinish();

        Page<Grade> page = teacherService.doViewStuGradeAllPage(pageNum, pageSize, id);
        int total = 0;
        total = teacherService.doViewStuGradeAllPageNum(id);
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
     * 老师端查看学生的页面
     * @param request
     * @param response
     * @throws IOException
     */
    private void doViewStuByNoCheck(HttpServletRequest request, HttpServletResponse response)throws IOException {
        TeacherService teacherService = new TeacherServiceImpl();
        int pageNum = Integer.decode(request.getParameter("pageNum"));
        int pageSize = Integer.decode(request.getParameter("pageSize"));
        int exam_id = Integer.decode(request.getParameter("exam_id"));
//        HttpSession session = request.getSession();
//        User user1;
//        user1 = (User) session.getAttribute("user");
//        int id = user1.getId();

        ExamDao examDao = new ExamDao();
        //判断是否失效
        int flag1 = examDao.updateIsfinish();
        //获得没有加入自己的学生
        Page<Grade> page = teacherService.getViewStuByNoCheck(pageNum, pageSize, exam_id);
        int total = 0;
        total = teacherService.getViewStuByNoCheckNum(exam_id);
        List<Grade> StuNoCheckList = new ArrayList<>();
        for (Grade student : page.getList()) {
            StuNoCheckList.add(student);
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("data", StuNoCheckList);
        map.put("total", total);
        String jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 老师端查看学生的页面
     * @param request
     * @param response
     * @throws IOException
     */
    private void doViewStuPage(HttpServletRequest request, HttpServletResponse response)throws IOException {
        TeacherService teacherService = new TeacherServiceImpl();
        int pageNum = Integer.decode(request.getParameter("pageNum"));
        int pageSize = Integer.decode(request.getParameter("pageSize"));
        HttpSession session = request.getSession();
        User user1;
        user1 = (User) session.getAttribute("user");
        int id = user1.getId();

        ExamDao examDao = new ExamDao();
        //判断是否失效
        int flag1 = examDao.updateIsfinish();
        //获得没有加入自己的学生
        Page<User> page = teacherService.getViewStuPage(pageNum, pageSize, id);
        int total = 0;
        total = teacherService.getViewStuPageNum(id);
        List<User> StuList = new ArrayList<>();
        for (User student : page.getList()) {
            StuList.add(student);
        }
        Gson gson = new Gson();
        Map map = new HashMap();
        map.put("data", StuList);
        map.put("total", total);
        String jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    /**
     * 选择学生根据年级
     * @param request
     * @param response
     * @throws IOException
     */
    private void doSelectStuByGrade(HttpServletRequest request, HttpServletResponse response)throws IOException {

        String jsonString = JSONUtil.getJson(request);
        Gson gson = new Gson();
        User student = gson.fromJson(jsonString, User.class);
        TeacherService teacherService = new TeacherServiceImpl();

        HttpSession session = request.getSession();
        User user1;
        user1 = (User) session.getAttribute("user");
        int id = user1.getId();
        List<User> StuList;
        String grade = student.getGrade();
        StuList = teacherService.selectStuByGrade(grade,id);
        UserDao userDao  = new UserDao();
        int total = userDao.selectStuByGradeNum(grade,id);
        Map map = new HashMap();
        if(StuList == null){
            map.put("code",0);
            map.put("msg","查询失败，没有想要的信息");
        } else  {
            map.put("code",1);
            map.put("total",total);
            map.put("data",StuList);
            map.put("msg","查询成功");
        }
        jsonString = gson.toJson(map);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();

    }


}
