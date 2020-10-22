package com.summary.service.Impl;

import com.summary.dao.*;
import com.summary.entity.*;
import com.summary.service.TeacherService;
import com.summary.service.UserService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    //todo 异常处理

    /**
     * 试卷处理
     * @param exam
     * @return
     */
    //添加试卷
    @Override
    public int addExam(Exam exam,int tea_id){
        ExamDao examDao = new ExamDao();
        return examDao.addExam(exam,tea_id);

    }
    //删除试卷
    @Override
    public int  deleteExam(int exam_id){
        ExamDao examDao = new ExamDao();
        return examDao.deleteExam(exam_id);

    }

    //更改试卷
    @Override
    public int updateExam(Exam exam){
        ExamDao examDao = new ExamDao();
        return examDao.updateExam(exam);

    }

    //试卷页面渲染
    @Override
    public Page<Exam> getTeaExamPage(int pageNum , int pageSize, int id) {
        PageDao pagedao = new PageDao();
        int totalSize = getTeaExamPageNum(id);//数据总数
        int starIndex = (pageNum - 1) * pageSize;
        Page<Exam> page = new Page(pageNum, pageSize, totalSize);//范围限定
        page.setList(pagedao.getTeaExamPage(starIndex, pageSize,id));//获得结果
        return page;
    }


    @Override
    public int getTeaExamPageNum(int id) {
        PageDao pageDao = new PageDao();
        int total = pageDao.getTeaExamPageNum(id);
        return total;
    }

    /**
     * 选择题题目的处理
     * @param quesOption
     * @return
     */
    //添加选择题目
    public int addQuesOption(QuesOption quesOption){
        QuestionDao questionDao = new QuestionDao();
        return questionDao.addQuesOption(quesOption);

    }
    //删除选择题目
    public int deleteQuesOption(QuesOption quesOption){
        QuestionDao questionDao = new QuestionDao();
        return questionDao.deleteQuesOption(quesOption);
    }
    //更新选择题目
    public int updateQuesOption(QuesOption quesOption){
        QuestionDao questionDao = new QuestionDao();
        return questionDao.updateQuesOption(quesOption);
    }
    //比较是否题目重复
    public int isRepeatNumber(int exam_id,int number){
        QuestionDao questionDao = new QuestionDao();
        return questionDao.isRepeatNumber(exam_id,number);
    }
    //选择题渲染
    @Override
    public Page<QuesOption> getTeaOptionPage(int pageNum,int pageSize,int exam_id) {
        PageDao pagedao = new PageDao();
        int totalSize = getTeaOptionPageNum(exam_id);//数据总数
        int starIndex = (pageNum - 1) * pageSize;
        Page<QuesOption> page = new Page(pageNum, pageSize, totalSize);//范围限定
        page.setList(pagedao.getTeaOptionPage(starIndex, pageSize,exam_id));//获得结果
        return page;
    }

    @Override
    public int getTeaOptionPageNum(int exam_id) {
        PageDao pageDao = new PageDao();
        int total = pageDao.getTeaOptionPageNum(exam_id);
        return total;
    }

    /**
     * 填空题题目的处理
     * @param quesFill
     * @return
     */
    //添加选择题目
    public int addQuesFill(QuesFill quesFill){
        QuestionDao questionDao = new QuestionDao();
        return questionDao.addQuesFill(quesFill);

    }
    //删除选择题目
    public int deleteQuesFill(QuesFill quesFill){
        QuestionDao questionDao = new QuestionDao();
        return questionDao.deleteQuesFill(quesFill);
    }
    //更新选择题目
    public int updateQuesFill(QuesFill quesFill){
        QuestionDao questionDao = new QuestionDao();
        return questionDao.updateQuesFill(quesFill);
    }
    //比较是否题目重复
    public int isRepeatNumberFill(int exam_id,int number){
        QuestionDao questionDao = new QuestionDao();
        return questionDao.isRepeatNumberFill(exam_id,number);
    }
//    //填空题渲染
//    @Override
//    public Page<QuesOption> getTeaOptionPage(int pageNum,int pageSize,int exam_id) {
//        PageDao pagedao = new PageDao();
//        int totalSize = getTeaOptionPageNum(exam_id);//数据总数
//        int starIndex = (pageNum - 1) * pageSize;
//        Page<QuesOption> page = new Page(pageNum, pageSize, totalSize);//范围限定
//        page.setList(pagedao.getTeaOptionPage(starIndex, pageSize,exam_id));//获得结果
//        return page;
//    }
//
//    @Override
//    public int getTeaOptionPageNum(int exam_id) {
//        PageDao pageDao = new PageDao();
//        int total = pageDao.getTeaOptionPageNum(exam_id);
//        return total;
//    }
    /**
     * 问答题题目的处理
     * @param quesEssay
     * @return
     */
    //添加问答题目
    public int addQuesEssay(QuesEssay quesEssay){
        QuestionDao questionDao = new QuestionDao();
        return questionDao.addQuesEssay(quesEssay);

    }
    //删除问答题目
    public int deleteQuesEssay(QuesEssay quesEssay){
        QuestionDao questionDao = new QuestionDao();
        return questionDao.deleteQuesEssay(quesEssay);
    }
    //更新问答题目
    public int updateQuesEssay(QuesEssay quesEssay){
        QuestionDao questionDao = new QuestionDao();
        return questionDao.updateQuesEssay(quesEssay);
    }
    //比较是否问答题目重复(
    public int isRepeatNumberEss(int exam_id,int number){
        QuestionDao questionDao = new QuestionDao();
        return questionDao.isRepeatNumberEss(exam_id,number);
    }

    //问答题渲染
    @Override
    public Page<QuesEssay> getTeaEssayPage(int pageNum,int pageSize,int exam_id) {
        PageDao pagedao = new PageDao();
        int totalSize = getTeaEssayPageNum(exam_id);//数据总数
        int starIndex = (pageNum - 1) * pageSize;
        Page<QuesEssay> page = new Page(pageNum, pageSize, totalSize);//范围限定
        page.setList(pagedao.getTeaEssayPage(starIndex, pageSize,exam_id));//获得结果
        return page;
    }

    @Override
    public int getTeaEssayPageNum(int exam_id) {
        PageDao pageDao = new PageDao();
        int total = pageDao.getTeaEssayPageNum(exam_id);
        return total;
    }
     //批改一道一道问答题
    @Override
    public int markEssay(QuesEssay quesEssay){
        QuestionDao questionDao = new QuestionDao();
        return questionDao.markEssay(quesEssay);
    }
    //批改所有
    @Override
    public int markEssayFinish(QuesEssay quesEssay,double grade){
        QuestionDao questionDao = new QuestionDao();
        return questionDao.markEssayFinish(quesEssay,grade);
    }


    /**
     * 成绩
     * @param pageNum
     * @param pageSize
     * @param exam_id
     * @return
     */
    //成绩页面渲染
    @Override
    public Page<Grade> doViewStuGradePage(int pageNum , int pageSize, int exam_id) {
        PageDao pageDao = new PageDao();
        int totalSize = doViewStuGradePageNum(exam_id);//数据总数
        int starIndex = (pageNum - 1) * pageSize;
        Page<Grade> page = new Page(pageNum, pageSize, totalSize);//范围限定
        page.setList(pageDao.doViewStuGradePage(starIndex, pageSize,exam_id));//获得结果
        return page;
    }

    @Override
    public int doViewStuGradePageNum(int exam_id) {
        PageDao pagedao = new PageDao();
        int total = pagedao.doViewStuGradePageNum(exam_id);
        return total;
    }

    @Override
    public Page<Grade> doViewStuGradeAllPage(int pageNum , int pageSize, int id) {
        PageDao pageDao = new PageDao();
        int totalSize = doViewStuGradeAllPageNum(id);//数据总数
        int starIndex = (pageNum - 1) * pageSize;
        Page<Grade> page = new Page(pageNum, pageSize, totalSize);//范围限定
        page.setList(pageDao.doViewStuGradeAllPage(starIndex, pageSize,id));//获得结果
        return page;
    }

    @Override
    public int doViewStuGradeAllPageNum(int id) {
        PageDao pagedao = new PageDao();
        int total = pagedao.doViewStuGradeAllPageNum(id);
        return total;
    }


    //获得没有考试的学生
    public Page<User> getViewUndonePage(int pageNum , int pageSize, int exam_id){
        PageDao pagedao = new PageDao();
        ExamDao  examDao = new ExamDao();
        int totalSize = examDao.viewUndone(exam_id);//数据总数
        int starIndex = (pageNum - 1) * pageSize;
        Page<User> page = new Page(pageNum, pageSize, totalSize);//范围限定
        page.setList(pagedao.getViewUndonePage(starIndex, pageSize,exam_id));//获得结果
        return page;

    }


    //所有学生页面
    @Override
    public Page<User> getViewStuPage(int pageNum , int pageSize, int id) {
        PageDao pagedao = new PageDao();
        int totalSize = getViewStuPageNum(id);//数据总数
        int starIndex = (pageNum - 1) * pageSize;
        Page<User> page = new Page(pageNum, pageSize, totalSize);//范围限定
        page.setList(pagedao.getViewStuPage(starIndex, pageSize,id));//获得结果
        return page;
    }


    @Override
    public int getViewStuPageNum(int id) {
        PageDao pageDao = new PageDao();
        int total = pageDao.getViewStuPageNum(id);
        return total;
    }

    //选择学生根据年级
    @Override

    public List<User> selectStuByGrade(String grade,int tea_id) {
        UserDao userDao = new UserDao();
        List<User> StuList;
        StuList = userDao.selectStuByGrade(grade,tea_id);
        int flag = userDao.selectStuByGradeNum(grade,tea_id);
        if(flag == 0){
            return null;
        } else {
            return StuList;
        }
    }



    //所有学生的页面
    @Override
    public Page<Grade> getViewStuByNoCheck(int pageNum , int pageSize, int exam_id) {
        PageDao pageDao = new PageDao();
        int totalSize = getViewStuByNoCheckNum(exam_id);//数据总数
        int starIndex = (pageNum - 1) * pageSize;
        Page<Grade> page = new Page(pageNum, pageSize, totalSize);//范围限定
        page.setList(pageDao.getViewStuByNoCheck(starIndex, pageSize,exam_id));//获得结果
        return page;
    }

    @Override
    public int getViewStuByNoCheckNum(int exam_id) {
        PageDao pagedao = new PageDao();
        int total = pagedao.getViewStuByNoCheckNum(exam_id);
        return total;
    }



}
