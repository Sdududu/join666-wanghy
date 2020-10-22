package com.summary.dao;

import com.summary.dao.sql.ExamSql;
import com.summary.dao.sql.UserSql;
import com.summary.entity.*;
import com.summary.util.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamDao {

    /**
     * 添加试卷
     * @param exam
     * @return
     */
    public int addExam(Exam exam,int tea_id){


        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.addExam);
            ps.setString(1, exam.getSubject());
            ps.setString(2, exam.getTimes());
            ps.setString(3, exam.getDeadline());
            ps.setInt(4,tea_id);
            ps.setDouble(5,exam.getTotal_points());
            succeed = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }

    /**
     * 删除试卷
     * @param exam_id
     * @return
     */
    public static int deleteExam(int exam_id) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.deleteExam);
            ps.setInt(1, exam_id);
            succeed = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }

    /**
     * 删除学生端的试卷
     * @param exam_id
     * @return
     */
    public static int deleteExamInStu(int exam_id) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.deleteExamInStu);
            ps.setInt(1, exam_id);
            succeed = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }


    /**
     * 更改试卷
     * @param exam
     * @return
     */
    public int updateExam(Exam exam){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.updateExam);
            ps.setString(1, exam.getSubject());
            ps.setString(2, exam.getTimes());
            ps.setString(3, exam.getDeadline());
            ps.setDouble(4,exam.getTotal_points());
            ps.setInt(5, exam.getExam_id());

            succeed = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }


    /**
     * 获得试卷的id
     * @param exam
     * @return
     */
    public Exam selectExamId(Exam exam){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        Exam succeed = null;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(ExamSql.selectExamId);  //选择出所有的学生id并加入
            resultSet = ps.executeQuery();
            while(resultSet.next()){
                succeed = new Exam();
                succeed.setSubject(resultSet.getString("subject"));
                succeed.setExam_id(resultSet.getInt("exam_id"));
                succeed.setTimes(resultSet.getString("times"));
                succeed.setPublish_date(resultSet.getString("publish_date"));
                succeed.setDeadline(resultSet.getString("deadline"));
                succeed.setTotal_points(resultSet.getDouble("total_points"));

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }

    /**
     * 获得试卷id给学生
     * @param exam
     * @return
     */
    public int addExamIdInStu(Exam exam,int id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
//        Student succeed = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.selectStuIdByTea);
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            while(resultSet.next()){
//                succeed = new Student();
               int stu_id = resultSet.getInt("stu_id");
               int flag = addExamStu(stu_id,exam.getExam_id());
               if(flag !=0){ succeed = 1;

               }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }
    /**
     * 加入到数据库中
     * @param exam_id
     * @return
     */
    public int addExamStu(int stu_id,int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.addExamStu);
            ps.setInt(1, exam_id);
            ps.setInt(2, stu_id);
            succeed = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }

    /**
     * 查看现在考试的学生数
     * @param exam_id
     * @return
     */
    public int viewPeopleNow(int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.viewPeopleNow);
            ps.setInt(1, exam_id);
            resultSet = ps.executeQuery();
            resultSet.first();
            succeed =resultSet.getInt(1);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }

    /**
     * 查看还没考试的学生
     * @param exam_id
     * @return
     */
    public int viewUndone(int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;

        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.viewUndone);
            ps.setInt(1, exam_id);
            resultSet = ps.executeQuery();
            resultSet.first();
            succeed =resultSet.getInt(1);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }

    /**
     *  判断是否失效
     */


    public int updateIsfinish(){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.selectDeadline);
            resultSet = ps.executeQuery();
            while(resultSet.next()){
                int exam_id = resultSet.getInt("exam_id");
                String Deadline = resultSet.getString("deadline");
                Date date = new Date();

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = format.format(date);
                int big = Deadline.compareTo(dateString);
                if(big <=0 ){
                    succeed = updateIsFinish(exam_id);
                }

                }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }

    /**
     * 更改是否超时
     * @param exam_id
     * @return
     */
    public int updateIsFinish(int exam_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.updateIsfinish);
            ps.setInt(1, exam_id);
            succeed = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }

    /**
     * 将选择题答案加入
     * @param optionList
     * @param isright
     * @return
     */
    public int addMyOption(QuesOption optionList,int stu_id,int isright){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.addMyOption);
            ps.setInt(1, optionList.getExam_id());
            ps.setInt(2, optionList.getNumber());
            ps.setString(3,optionList.getOp_res());
            ps.setInt(4,stu_id);
            ps.setInt(5,isright);
            succeed = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }

    /**
     * 将填空题答案加入
     * @param fillList stu_id
     * @param fill_right
     * @return
     */
    public int addMyFill(QuesFill fillList,int stu_id,int fill_right){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.addMyFill);
            ps.setInt(1, fillList.getExam_id());
            ps.setInt(2, fillList.getNumber());
            ps.setString(3,fillList.getFill_res());
            ps.setInt(4,stu_id);
            ps.setInt(5,fill_right);
            succeed = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }

    /**
     * 将主观题答案加入
     * @param essayList
     * @param
     * @return
     */
    public int addMyEssay(QuesEssay essayList, int stu_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.addMyEssay);
            ps.setInt(1, essayList.getExam_id());
            ps.setInt(2, essayList.getNumber());
            ps.setString(3,essayList.getEssay_res());
            ps.setInt(4,stu_id);
            ps.setString(5,essayList.getEs_resimg());
            succeed = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }


    /**
     * 加入学生成绩
     * @param stu_id
     * @param exam_id
     * @param op_grade
     * @return
     */
    public int insertGradeOp(int stu_id , int exam_id , double op_grade,double fill_grade){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.insertGradeOp);
            ps.setInt(1, stu_id);
            ps.setInt(2, exam_id);
            ps.setDouble(3,op_grade);
            ps.setDouble(4,fill_grade);
            succeed = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }


    /**
     * 开始该张试卷
     * @param exam_id
     * @param
     * @return
     */
    public int isCompleteNow( int exam_id ,int stu_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.isCompleteNow);
            ps.setInt(1, stu_id);
            ps.setInt(2, exam_id);
            succeed = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }
    /**
     * 完成该张试卷
     * @param exam_id
     * @param
     * @return
     */
    public int isCompleteStu( int exam_id ,int stu_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(ExamSql.isCompleteStu);
            ps.setInt(1, stu_id);
            ps.setInt(2, exam_id);
            succeed = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }



//    public int isRightOption(QuesOption optionList){
//        PreparedStatement ps = null;
//        Connection conn = null;
//        ResultSet resultSet = null;
//        int succeed = 0;
//        try {
//            conn = C3P0Util.getConnection();
//            if(optionList.getAnswer().equals(optionList.getOp_res()));{
//
//            }
//            ps = conn.prepareStatement(ExamSql.isRightOption);
//            ps.setInt(1, optionList.getExam_id());
//            ps.setInt(2, optionList.getStu_id());
//            ps.setInt(3, optionList.getNumber());
//            ps.setString(4,optionList.getOp_res());
//            ps.setInt(5,optionList.getOp_isright());
//            succeed = ps.executeUpdate();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            C3P0Util.releaseConnection(conn, ps, resultSet);
//        }
//
//        return succeed;
//
//    }


}






