package com.summary.demo.dao;

import com.summary.dao.ExamDao;
import com.summary.entity.Exam;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExamDaoTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("这是before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("这是after");
    }

    @Test
    public void getGoodsInventoryByThing_id() {
        Exam exam = new Exam();
        exam.setSubject("近代史");
        exam.setDeadline("2020-10-20 21:59:06");
        exam.setTimes("一个半小时");
        int id = 2009000001;
        ExamDao examDao = new ExamDao();
        int flag =examDao.addExam(exam,id);
        System.out.println(flag);

    }

    @Test
    public void updateExamTest() {
        Exam exam = new Exam();
        exam.setSubject("近代史");
        exam.setDeadline("2020-10-20 21:59:06");
        exam.setTimes("一个半小时");
        exam.setExam_id(000003);
        ExamDao examDao = new ExamDao();
        int flag =examDao.updateExam(exam);
        System.out.println(flag);

    }

    @Test
    public void updateIsfinish() {
        Exam exam = new Exam();
//        exam.setSubject("近代史");
        exam.setDeadline("2020-10-21");
//        exam.setTimes("一个半小时");
//        exam.setExam_id(000003);
        ExamDao examDao = new ExamDao();
        int flag =examDao.updateIsfinish() ;
        System.out.println(flag);

    }

}
