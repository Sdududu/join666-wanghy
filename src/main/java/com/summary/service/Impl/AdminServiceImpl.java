package com.summary.service.Impl;

import com.summary.dao.AdminDao;
import com.summary.entity.Exam;
import com.summary.entity.User;
import com.summary.service.AdminService;
import com.summary.service.StudentService;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {


    //todo 异常处理
    //查找试卷bysubject

    @Override

    public List<Exam> selectExamByName(String subject) {
        AdminDao adminDao = new AdminDao();
        List<Exam> examList;
        examList = adminDao.selectExamByName(subject);
        int flag = adminDao.selectExamByNameNum(subject);
        if(flag == 0){
            return null;
        } else {
            return examList;
        }
    }

    //查找学生姓名byName

    @Override

    public List<User> selectStuByName(String userName) {
        AdminDao adminDao = new AdminDao();
        List<User> userList;
        userList = adminDao.selectStuByName(userName);
        int flag = adminDao.selectStuByNameNum(userName);
        if(flag == 0){
            return null;
        } else {
            return userList;
        }
    }


}
