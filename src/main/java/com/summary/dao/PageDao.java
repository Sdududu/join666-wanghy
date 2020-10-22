package com.summary.dao;

import com.summary.dao.sql.PageSql;
import com.summary.entity.*;
import com.summary.util.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PageDao {

    /**
     * 老师端页面
     * @param starIndex
     * @param pageSize
     * @return
     */
    public List<Exam> getTeaExamPage(int starIndex, int pageSize ,int id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<Exam> myTeaExamList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getTeaExamPage);
            ps.setInt(1, id);
            ps.setInt(2, starIndex);
            ps.setInt(3, pageSize);
            resu = ps.executeQuery();
            while(resu.next()){
                Exam exam = new Exam();
                exam.setExam_id(resu.getInt("exam_id"));
                exam.setIsfinish(resu.getInt("isfinish"));
                exam.setSubject(resu.getString("subject"));
                exam.setDeadline(resu.getString("deadline"));
                exam.setPublish_date(resu.getString("publish_date"));
                exam.setTimes(resu.getString("times"));
                exam.setTotal_points(resu.getDouble("total_points"));

                myTeaExamList.add(exam);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  myTeaExamList;

    }


    /**
     * 获得老师端试卷数据总条数
     * @return
     */
    public int getTeaExamPageNum(int id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getTeaExamPageNum);
            ps.setInt(1,id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }

    /**
     *获得所有没有完成的学生ID
     *
     */
    public List<User> getViewUndonePage(int starIndex, int pageSize, int exam_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<User> MyStuIdList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getViewUndonePage);
            ps.setInt(1, exam_id);
            ps.setInt(2, starIndex);
            ps.setInt(3, pageSize);
            resu = ps.executeQuery();
            while(resu.next()){
                User user = new User();
                user.setId(resu.getInt("stu_id"));
                MyStuIdList.add(user);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  MyStuIdList;

    }


    /**
     * 老师端选择题渲染
     * @param starIndex
     * @param pageSize
     * @return
     */
    public List<QuesOption> getTeaOptionPage(int starIndex, int pageSize , int exam_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet =null;
        List<QuesOption> MyTeaOptionList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getTeaOptionPage);
            ps.setInt(1, exam_id);
            ps.setInt(2, starIndex);
            ps.setInt(3, pageSize);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                QuesOption succeed = new QuesOption();
                succeed.setExam_id(resultSet.getInt("exam_id"));
                succeed.setNumber(resultSet.getInt("number"));
                succeed.setOp_content(resultSet.getString("op_content"));
                succeed.setOp_img(resultSet.getString("op_img"));
                succeed.setOp1(resultSet.getString("op1"));
                succeed.setOp2(resultSet.getString("op2"));
                succeed.setOp3(resultSet.getString("op3"));
                succeed.setOp4(resultSet.getString("op4"));
                succeed.setAnswer(resultSet.getString("answer"));
                succeed.setOp_score(resultSet.getDouble("op_score"));

                MyTeaOptionList.add(succeed);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resultSet.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  MyTeaOptionList;

    }


    /**
     * 获得老师端选择题数据总条数
     * @return
     */
    public int getTeaOptionPageNum(int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<QuesOption> MyTeaOptionList= new ArrayList<>();
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getTeaOptionPageNum);
            ps.setInt(1,exam_id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }

    /**
     * 老师端问答题渲染
     * @param starIndex
     * @param pageSize
     * @return
     */
    public List<QuesEssay> getTeaEssayPage(int starIndex, int pageSize , int exam_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet =null;
        List<QuesEssay> MyTeaEssayList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getTeaEssayPage);
            ps.setInt(1, exam_id);
            ps.setInt(2, starIndex);
            ps.setInt(3, pageSize);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                QuesEssay succeed = new QuesEssay();
                succeed.setExam_id(resultSet.getInt("exam_id"));
                succeed.setNumber(resultSet.getInt("number"));
                succeed.setEs_content(resultSet.getString("es_content"));
                succeed.setEs_img(resultSet.getString("es_img"));
                succeed.setEs_score(resultSet.getDouble("es_score"));

                MyTeaEssayList.add(succeed);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resultSet.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  MyTeaEssayList;

    }


    /**
     * 获得老师端问答题数据总条数
     * @return
     */
    public int getTeaEssayPageNum(int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<QuesEssay> MyTeaEssayList= new ArrayList<>();
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getTeaEssayPageNum);
            ps.setInt(1,exam_id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }

    /**
     * 学生端页面
     * @param starIndex
     * @param pageSize
     * @return
     */
    public List<Exam> getStuExamPage(int starIndex, int pageSize ,int id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<Exam> myStuExamList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getStuExamPage);  //已经完成,完成的给成绩就行
            ps.setInt(1, id);  //学生id
            ps.setInt(2, starIndex);
            ps.setInt(3, pageSize);
            resu = ps.executeQuery();
            while(resu.next()){
                Exam exam = new Exam();
                exam.setExam_id(resu.getInt("tb_exam.exam_id"));
                exam.setSubject(resu.getString("tb_exam.subject"));
                exam.setDeadline(resu.getString("tb_exam.deadline"));
                exam.setPublish_date(resu.getString("tb_exam.publish_date"));
                exam.setTimes(resu.getString("tb_exam.times"));
                exam.setTotal_points(resu.getDouble("total_points"));
                exam.setComplete(resu.getInt("stu_exam.complete")) ;

                myStuExamList.add(exam);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  myStuExamList;

    }


    /**
     * 获得学生端试卷数据总条数
     * @return
     */
    public int getStuExamPageNum(int id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<Exam> myTeaExamList= new ArrayList<>();
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getStuExamPageNum);
            ps.setInt(1,id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }


    /**
     * 学生端选择题渲染
     * @param starIndex
     * @param pageSize
     * @return
     */
    public List<QuesOption> getStuOptionPage(int starIndex, int pageSize , int exam_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet =null;
        List<QuesOption> MyStuOptionList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getStuOptionPage);
            ps.setInt(1, exam_id);
            ps.setInt(2, starIndex);
            ps.setInt(3, pageSize);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                QuesOption succeed = new QuesOption();
                succeed.setExam_id(resultSet.getInt("exam_id"));
                succeed.setNumber(resultSet.getInt("number"));
                succeed.setOp_content(resultSet.getString("op_content"));
                succeed.setOp_img(resultSet.getString("op_img"));
                succeed.setOp1(resultSet.getString("op1"));
                succeed.setOp2(resultSet.getString("op2"));
                succeed.setOp3(resultSet.getString("op3"));
                succeed.setOp4(resultSet.getString("op4"));
                succeed.setOp_score(resultSet.getDouble("op_score"));

                MyStuOptionList.add(succeed);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resultSet.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  MyStuOptionList;

    }


    /**
     * 获得学生端选择题数据总条数
     * @return
     */
    public int getStuOptionPageNum(int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getStuOptionPageNum);
            ps.setInt(1,exam_id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }

    /**
     * 学生端填空题渲染
     * @param starIndex
     * @param pageSize
     * @return
     */
    public List<QuesFill> getStuFillPage(int starIndex, int pageSize , int exam_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet =null;
        List<QuesFill> MyStuFillList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getStuFillPage);
            ps.setInt(1, exam_id);
            ps.setInt(2, starIndex);
            ps.setInt(3, pageSize);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                QuesFill succeed = new QuesFill();
                succeed.setExam_id(resultSet.getInt("exam_id"));
                succeed.setNumber(resultSet.getInt("number"));
                succeed.setFill_content(resultSet.getString("fill_content"));
                succeed.setFill_img(resultSet.getString("fill_img"));
                succeed.setFill_score(resultSet.getDouble("fill_score"));
                succeed.setFill_answer(resultSet.getString("fill_answer"));

                MyStuFillList.add(succeed);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resultSet.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  MyStuFillList;

    }


    /**
     * 获得学生端填空题数据总条数
     * @return
     */
    public int getStuFillPageNum(int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getStuFillPageNum);
            ps.setInt(1,exam_id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }

    /**
     * 学生端问答题渲染
     * @param starIndex
     * @param pageSize
     * @return
     */
    public List<QuesEssay> getStuEssayPage(int starIndex, int pageSize , int exam_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet =null;
        List<QuesEssay> MyStuEssayList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getStuEssayPage);
            ps.setInt(1, exam_id);
            ps.setInt(2, starIndex);
            ps.setInt(3, pageSize);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                QuesEssay succeed = new QuesEssay();
                succeed.setExam_id(resultSet.getInt("exam_id"));
                succeed.setNumber(resultSet.getInt("number"));
                succeed.setEs_content(resultSet.getString("es_content"));
                succeed.setEs_img(resultSet.getString("es_img"));
                succeed.setEs_score(resultSet.getDouble("es_score"));
                MyStuEssayList.add(succeed);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resultSet.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  MyStuEssayList;

    }


    /**
     * 获得学生端问答题数据总条数
     * @return
     */
    public int getStuEssayPageNum(int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<QuesEssay> MyStuEssayList= new ArrayList<>();
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getStuEssayPageNum);
            ps.setInt(1,exam_id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }

    /**
     * 学生端选择题答题渲染
     * @param
     * @param pageSize
     * @return
     */
    public List<QuesOption> getCheckMyOptionAnswerPage(int starIndex, int pageSize, int stu_id,int exam_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet =null;
        List<QuesOption> MyCheckOptionList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getCheckOptionPage);
            ps.setInt(1, stu_id);
            ps.setInt(2, exam_id);
            ps.setInt(3, starIndex);
            ps.setInt(4, pageSize);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                QuesOption succeed = new QuesOption();
                succeed.setExam_id(resultSet.getInt("exam_id"));
                succeed.setNumber(resultSet.getInt("number"));
                succeed.setStu_id(resultSet.getInt("stu_id"));

                succeed.setOp_content(resultSet.getString("op_content"));
                succeed.setOp_img(resultSet.getString("op_img"));
                succeed.setOp1(resultSet.getString("op1"));
                succeed.setOp2(resultSet.getString("op2"));
                succeed.setOp3(resultSet.getString("op3"));
                succeed.setOp4(resultSet.getString("op4"));
                succeed.setAnswer(resultSet.getString("answer"));
                succeed.setOp_isright(resultSet.getInt("op_isright"));
                succeed.setOp_score(resultSet.getDouble("op_score"));
                succeed.setOp_res(resultSet.getString("op_res"));



                MyCheckOptionList.add(succeed);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resultSet.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  MyCheckOptionList;

    }


    /**
     * 获得学生端选择题答题数据总条数
     * @return
     */
    public int getCheckMyOptionAnswerPageNum(int stu_id ,int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getCheckMyOptionAnswerPageNum);
            ps.setInt(1,exam_id);
            ps.setInt(2,stu_id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }

    /**
     * 查看学生端填空题渲染
     * @param starIndex
     * @param pageSize
     * @return
     */
    public List<QuesFill> getCheckMyFillAnswerPage(int starIndex, int pageSize, int stu_id,int exam_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet =null;
        List<QuesFill> MyCheckFillList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getCheckFillPage);
            ps.setInt(1, stu_id);
            ps.setInt(2, exam_id);
            ps.setInt(3, starIndex);
            ps.setInt(4, pageSize);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                QuesFill succeed = new QuesFill();
                succeed.setExam_id(resultSet.getInt("question_fill.exam_id"));
                succeed.setNumber(resultSet.getInt("question_fill.number"));
                succeed.setFill_content(resultSet.getString("question_fill.fill_content"));
                succeed.setFill_img(resultSet.getString("question_fill.fill_img"));
                succeed.setFill_score(resultSet.getDouble("question_fill.fill_score"));
                succeed.setFill_answer(resultSet.getString("question_fill.fill_answer"));
                succeed.setFill_res(resultSet.getString("answer_fill.fill_res"));
                succeed.setFill_right(resultSet.getInt("answer_fill.fill_right"));

                MyCheckFillList.add(succeed);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resultSet.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  MyCheckFillList;

    }


    /**
     * 查看学生端填空题数据总条数
     * @return
     */
    public int getCheckMyFillAnswerPageNum(int stu_id ,int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getCheckFillPageNum);
            ps.setInt(1,exam_id);
            ps.setInt(2, stu_id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }
    /**
     * 学生端问答题渲染
     * @param starIndex
     * @param pageSize
     * @return
     */
    public List<QuesEssay> getCheckMyEssayAnswerPage(int starIndex, int pageSize , int stu_id, int exam_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet =null;
        List<QuesEssay> MyCheckEssayList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getCheckMyEssayAnswerPage);
            ps.setInt(1, exam_id);
            ps.setInt(2, stu_id);
            ps.setInt(3, starIndex);
            ps.setInt(4, pageSize);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                QuesEssay succeed = new QuesEssay();
                succeed.setExam_id(resultSet.getInt("answer_essay.exam_id"));
                succeed.setNumber(resultSet.getInt("answer_essay.number"));
                succeed.setEs_content(resultSet.getString("es_content"));
                succeed.setEs_img(resultSet.getString("es_img"));
                succeed.setEs_score(resultSet.getDouble("es_score"));

                succeed.setEs_resimg(resultSet.getString("es_resimg"));
                succeed.setEssay_res(resultSet.getString("essay_res"));

                succeed.setEs_grade(resultSet.getDouble("es_grade"));
                MyCheckEssayList.add(succeed);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resultSet.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  MyCheckEssayList;
//        return null;

    }


    /**
     * 获得学生端问答题数据总条数
     * @return
     */
    public int getCheckMyEssayAnswerPageNum(int stu_id,int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getCheckMyEssayAnswerPageNum);
            ps.setInt(1,exam_id);
            ps.setInt(2,stu_id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }


    /**
     * 学生端成绩页面
     * @param starIndex
     * @param pageSize
     * @return
     */
    public List<Grade> getStuGradePage(int starIndex, int pageSize , int id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<Grade> myStuGradeList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getStuGradePage);  //已经完成,完成的给成绩就行
            ps.setInt(1, id);  //学生id
            ps.setInt(2, starIndex);
            ps.setInt(3, pageSize);
            resu = ps.executeQuery();
            while(resu.next()){
                Grade grade = new Grade();
                grade.setExam_id(resu.getInt("tb_exam.exam_id"));
                grade.setSubject(resu.getString("tb_exam.subject"));
                grade.setOp_grade(resu.getDouble("op_grade"));
                grade.setEs_grades(resu.getDouble("es_grades"));
                grade.setFill_grade(resu.getDouble("fill_grade"));
                grade.setGrade(resu.getDouble("grade"));
                grade.setFinishTime(getFinishTime(resu.getInt("grade.exam_id"),resu.getInt("grade.stu_id")));

//                exam.setTea_id(resu.getInt("tb_exam.tea_id"));

                myStuGradeList.add(grade);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  myStuGradeList;

    }


    /**
     * 获得学生端试卷成绩总条数
     * @return
     */
    public int getStuGradePageNum(int id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getStuGradePageNum);
            ps.setInt(1,id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }

    /**
     * 老师查看学生试卷成绩页面
     * @param starIndex
     * @param pageSize
     * @return
     */
    public List<Grade> doViewStuGradePage(int starIndex, int pageSize , int exam_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<Grade> myStuGradeList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.doViewStuGradePage);  //已经完成,完成的给成绩就行
            ps.setInt(1, exam_id);
            ps.setInt(2, starIndex);
            ps.setInt(3, pageSize);
            resu = ps.executeQuery();
            while(resu.next()){
                Grade grade = new Grade();
                grade.setExam_id(resu.getInt("grade.exam_id"));
                grade.setUserName(resu.getString("tb_student.userName"));
                grade.setOp_grade(resu.getDouble("op_grade"));
                grade.setFill_grade(resu.getDouble("fill_grade"));
                grade.setEs_grades(resu.getDouble("es_grades"));
                grade.setGrade(resu.getDouble("grade"));
                grade.setStu_id(resu.getInt("grade.stu_id"));

                grade.setFinishTime(getFinishTime(resu.getInt("grade.exam_id"),resu.getInt("grade.stu_id")));
                myStuGradeList.add(grade);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  myStuGradeList;

    }

    /**
     * 获得交卷时间
     */
    public String getFinishTime(int exam_id,int stu_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        String finishtime = null;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getFinishTime);
            ps.setInt(1,exam_id);
            ps.setInt(2,stu_id);

            resu = ps.executeQuery();
            while(resu.next()) {

                finishtime=resu.getString("finishTime");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  finishtime;
    }


    /**
     * 老师查看学生试卷成绩总条数
     * @return
     */
    public int doViewStuGradePageNum(int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.doViewStuGradePageNum);
            ps.setInt(1,exam_id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }


    /**
     * 老师查看所有学生试卷成绩页面
     * @param starIndex
     * @param pageSize
     * @return
     */
    public List<Grade> doViewStuGradeAllPage(int starIndex, int pageSize , int id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<Grade> myStuGradeList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.doViewStuGradeAllPage);  //已经完成,完成的给成绩就行
            ps.setInt(1, id);
            ps.setInt(2, starIndex);
            ps.setInt(3, pageSize);
            resu = ps.executeQuery();
            while(resu.next()){
                Grade grade = new Grade();
                grade.setExam_id(resu.getInt("grade.exam_id"));
                grade.setUserName(resu.getString("tb_student.userName"));
                grade.setOp_grade(resu.getDouble("op_grade"));
                grade.setFill_grade(resu.getDouble("fill_grade"));
                grade.setEs_grades(resu.getDouble("es_grades"));
                grade.setGrade(resu.getDouble("grade"));
                grade.setStu_id(resu.getInt("grade.stu_id"));

                grade.setFinishTime(getFinishTime(resu.getInt("grade.exam_id"),resu.getInt("grade.stu_id")));
                myStuGradeList.add(grade);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  myStuGradeList;

    }

    /**
     * 老师查看学生试卷成绩总条数
     * @return
     */
    public int doViewStuGradeAllPageNum(int id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.doViewStuGradeAllPageNum);
            ps.setInt(1,id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }

    /**
     * 所有学生
     * @param starIndex
     * @param pageSize
     * @return
     */
    public List<User> getViewStuPage(int starIndex, int pageSize ,int id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<User> StuList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getViewStuPage);
            ps.setInt(1, id);
            ps.setInt(2, starIndex);
            ps.setInt(3, pageSize);
            resu = ps.executeQuery();
            while(resu.next()){
                User student = new User();
                student.setId(resu.getInt("id"));
                student.setUserName(resu.getString("userName"));
                student.setSex(resu.getString("sex"));
                StuList.add(student);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  StuList;

    }

    /**
     * 所有学生总条数
     * @return
     */
    public int getViewStuPageNum(int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getViewStuPageNum);
            ps.setInt(1,exam_id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }

    /**
     * 没有批改的学生
     * @param starIndex
     * @param pageSize
     * @return
     */
    public List<Grade> getViewStuByNoCheck(int starIndex, int pageSize , int exam_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<Grade> myStuGradeList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getViewStuByNoCheck);  //已经完成,完成的给成绩就行
            ps.setInt(1, exam_id);
            ps.setInt(2, starIndex);
            ps.setInt(3, pageSize);
            resu = ps.executeQuery();
            while(resu.next()){
                Grade grade = new Grade();
                grade.setExam_id(resu.getInt("grade.exam_id"));
                grade.setUserName(resu.getString("tb_student.userName"));
                grade.setStu_id(resu.getInt("grade.stu_id"));

                myStuGradeList.add(grade);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  myStuGradeList;

    }


    /**
     * 没有批改的学生总数量
     * @return
     */
    public int getViewStuByNoCheckNum(int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(PageSql.getViewStuByNoCheckNum);
            ps.setInt(1,exam_id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                resu.close();
                ps.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  num;
    }

}
