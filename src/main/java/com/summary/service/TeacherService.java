package com.summary.service;

import com.summary.entity.*;

import java.util.List;

public interface TeacherService {

    /**
     * 试卷处理
     */
    //添加
    public int addExam(Exam exam , int tea_id);
    //删除试卷
    public int deleteExam(int exam_id);
    //更改
    public int updateExam(Exam exam);
    //试卷信息渲染
    Page<Exam> getTeaExamPage(int pageNum , int pageSize, int id);
    public int getTeaExamPageNum(int id);
    //没有考试的学生
    Page<User> getViewUndonePage(int pageNum , int pageSize, int exam_id);


    /**
     * 选择题
     * @param quesOption
     * @return
     */
    //添加问题
    public int addQuesOption(QuesOption quesOption);
    //删除问题
    public int deleteQuesOption(QuesOption quesOption);
    //更改
    public int updateQuesOption(QuesOption quesOption);
    //是否题目重复
    public int isRepeatNumber(int exam_id,int number);
    //信息渲染
    Page<QuesOption> getTeaOptionPage(int pageNum,int pageSize,int exam_id);
    public int getTeaOptionPageNum(int exam_id);

    /**
     * 填空题
     * @param quesFill
     * @return
     */
    //添加问题
    public int addQuesFill(QuesFill quesFill);
    //删除问题
    public int deleteQuesFill(QuesFill quesFill);
    //更改
    public int updateQuesFill(QuesFill quesFill);
    //是否题目重复
    public int isRepeatNumberFill(int exam_id,int number);
    //信息渲染
//    Page<QuesOption> getTeaOptionPage(int pageNum,int pageSize,int exam_id);
//    public int getTeaOptionPageNum(int exam_id);

    /**
     * 问答题
     * @param quesEssay
     * @return
     */
    //添加问题
    public int addQuesEssay(QuesEssay quesEssay);
    //删除问题
    public int deleteQuesEssay(QuesEssay quesEssay);
    //更改
    public int updateQuesEssay(QuesEssay quesEssay);
    //是否题目重复
    public int isRepeatNumberEss(int exam_id,int number);
    Page<QuesEssay> getTeaEssayPage(int pageNum,int pageSize,int exam_id);
    public int getTeaEssayPageNum(int exam_id);
    //批改问答题(插入)
    public int markEssay(QuesEssay quesEssay);
    public int markEssayFinish(QuesEssay quesEssay,double grade);

    /**
     * 成绩
     * @param pageNum
     * @param pageSize
     * @param exam_id
     * @return
     */
    //学生成绩渲染
    Page<Grade> doViewStuGradePage(int pageNum , int pageSize, int exam_id);
    public int doViewStuGradePageNum(int exam_id);

    Page<Grade> doViewStuGradeAllPage(int pageNum , int pageSize, int id);
    public int doViewStuGradeAllPageNum(int id);
    /**
     * 学生信息选择页面
     */
    //试卷信息渲染
    Page<User> getViewStuPage(int pageNum , int pageSize, int id);
    public int getViewStuPageNum(int id);
    //根据年级选择学生
    public List<User> selectStuByGrade(String grade,int tea_id);

     //查看所有学生没有批改的学生
    Page<Grade> getViewStuByNoCheck(int pageNum , int pageSize, int exam_id);
    public int getViewStuByNoCheckNum(int exam_id);
}
