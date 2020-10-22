package com.summary.service;

import com.summary.entity.Exam;
import com.summary.entity.User;

import java.util.List;

public interface AdminService {


    /**
     * 根据科目名称查试卷
     */

    public List<Exam> selectExamByName(String subject);

    public List<User> selectStuByName(String userName);
}
