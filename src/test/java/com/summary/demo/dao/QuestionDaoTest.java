package com.summary.demo.dao;

import com.summary.dao.ExamDao;
import com.summary.dao.QuestionDao;
import com.summary.entity.Exam;
import com.summary.entity.QuesOption;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class QuestionDaoTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("这是before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("这是after");
    }



    @Test
    public void getRepeatNumber() {
        QuesOption q = new QuesOption();
        int exam_id = 000001;
        int number = 2;
        QuestionDao questionDao = new QuestionDao();
        int flag =questionDao.isRepeatNumber(exam_id,number);
        System.out.println(flag);

    }
}
