package com.summary.service;

import com.summary.dao.PageDao;
import com.summary.entity.*;

import java.util.List;

public interface StudentService {


    //试卷的页面渲染
    Page<Exam> getStuExamPage(int pageNum , int pageSize, int id);
    public int getStuExamPageNum(int id);

    //选择题页面的渲染
    Page<QuesOption> getStuOptionPage(int pageNum, int pageSize, int exam_id);
    public int getStuOptionPageNum(int exam_id);

    //填空题页面的渲染
    Page<QuesFill> getStuFillPage(int pageNum, int pageSize, int exam_id);
    public int getStuFillPageNum(int exam_id);

    //问答题
    Page<QuesEssay> getStuEssayPage(int pageNum, int pageSize, int exam_id);
    public int getStuEssayPageNum(int exam_id);

    //我的选择题页面的渲染
    Page<QuesOption> getCheckMyOptionAnswerPage(int pageNum, int pageSize, int stu_id,int exam_id);
    public int getCheckMyOptionAnswerPageNum(int stu_id ,int exam_id);

    //我的填空题
    Page<QuesFill> getCheckMyFillAnswerPage(int pageNum, int pageSize, int stu_id,int exam_id);
    public int getCheckMyFillPageNum(int stu_id,int exam_id);
    //我的问答题
    Page<QuesEssay> getCheckMyEssayAnswerPage(int pageNum, int pageSize, int stu_id,int exam_id);
    public int getCheckMyEssayAnswerPageNum(int stu_id , int exam_id);

    //学生成绩渲染
    Page<Grade> getStuGradePage(int pageNum , int pageSize, int id);
    public int getStuGradePageNum(int id);

    //提交选择题
    public int addMyOption(QuesOption optionAnswerList,int stu_id,int isright);
//    public int isRightOption(QuesOption optionList);

    //提交选择题
    public int addMyFill(QuesFill fillList,int stu_id,int fill_right);
//    public int isRightOption(QuesOption optionList);

}
