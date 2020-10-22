package com.summary.dao;

import com.summary.dao.sql.AdminSql;
import com.summary.dao.sql.PageSql;
import com.summary.entity.Exam;
import com.summary.entity.User;
import com.summary.util.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    /**
     * 通过名称查找试卷
     * @param subject
     * @return
     */
    public List<Exam> selectExamByName(String subject){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<Exam> myExamList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(AdminSql.selectGradeByName);
            ps.setString(1, subject);
            resu = ps.executeQuery();
            while(resu.next()){
                Exam exam = new Exam();
                exam.setExam_id(resu.getInt("exam_id"));
                exam.setSubject(resu.getString("subject"));
                exam.setDeadline(resu.getString("deadline"));
                exam.setPublish_date(resu.getString("publish_date"));
                exam.setTimes(resu.getString("times"));
                exam.setTotal_points(resu.getDouble("total_points"));
                myExamList.add(exam);
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
        return  myExamList;

    }

    /**
     * 通过名称查找试卷总条数
     * @return
     */
    public int selectExamByNameNum(String subject){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<Exam> myTeaExamList= new ArrayList<>();
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(AdminSql.selectGradeByNameNum);
            ps.setString(1, subject);
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
     * 通过名称查找学生
     * @param
     * @return
     */
    public List<User> selectStuByName(String userName){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<User> userList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(AdminSql.selectStuByName);
            ps.setString(1, userName);
            resu = ps.executeQuery();
            while(resu.next()){
                User user = new User();

                user.setId(resu.getInt("id"));
                user.setUserName(resu.getString("userName"));
                userList.add(user);
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
        return  userList;

    }

    /**
     * 通过名称查找试卷总条数
     * @return
     */
    public int selectStuByNameNum(String subject){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<Exam> myTeaExamList= new ArrayList<>();
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(AdminSql.selectStuByNameNum);
            ps.setString(1, subject);
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


