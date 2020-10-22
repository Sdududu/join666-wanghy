package com.summary.dao.sql;

public class PageSql {
    //老师试卷
    public static String  getTeaExamPage= "select * from tb_exam where tea_id =? order by publish_date desc limit ?,?";
    public static String getTeaExamPageNum="select count(*) from tb_exam where tea_id=?";
    //老师选择题
    public static String  getTeaOptionPage= "select * from question_option where exam_id =? order by number limit ?,?";
    public static String getTeaOptionPageNum="select count(*) from question_option where exam_id=?";


    //老师问答题
    public static String  getTeaEssayPage= "select * from question_essay where exam_id =? order by number limit ?,?";
    public static String getTeaEssayPageNum="select count(*) from question_essay where exam_id=?";
    //查看没有考试的学生
    public static String getViewUndonePage= "select stu_id from stu_exam where exam_id =? and complete = 0 order by stu_id desc limit ?,?";

    //学生试卷
    public static String getStuExamPage="select * from stu_exam,tb_exam where (stu_exam.exam_id = tb_exam.exam_id) and stu_exam.stu_id = ? and tb_exam.isfinish = 1  order by tb_exam.publish_date limit ?,?";
    public static String getStuExamPageNum="select count(*) from stu_exam where stu_id=?";
    //学生选择题
    public static String getStuOptionPage="select * from question_option where exam_id = ? order by number limit ?,?";
    public static String getStuOptionPageNum="select count(1) from question_option where exam_id=?";
    //学生填空题
    public static String getStuFillPage="select * from question_fill where exam_id = ? order by number limit ?,?";
    public static String getStuFillPageNum="select count(1) from question_fill where exam_id=?";
    //学生问答题
    public static String getStuEssayPage="select * from question_essay where exam_id = ? order by number limit ?,?";
    public static String getStuEssayPageNum="select count(*) from question_essay where exam_id=?";
    //查看学生的答题
    public static String getCheckOptionPage="select * from question_option,answer_option where (question_option.number=answer_option.number) and (question_option.exam_id=answer_option.exam_id) and answer_option.stu_id = ? and answer_option.exam_id = ? limit ?,?";
    public static String getCheckMyOptionAnswerPageNum="select count(*) from answer_option where exam_id=? and stu_id = ? ";

    public static String getCheckFillPage="select * from question_fill,answer_fill where (question_fill.number=answer_fill.number) and (question_fill.exam_id=answer_fill.exam_id) and answer_fill.stu_id = ? and answer_fill.exam_id = ? limit ?,?";
    public static String getCheckFillPageNum="select count(*) from answer_fill where exam_id=? and stu_id = ? ";

    public static String getCheckMyEssayAnswerPage="select * from question_essay,answer_essay where (question_essay.number=answer_essay.number) and (question_essay.exam_id=answer_essay.exam_id) and answer_essay.exam_id  = ? and  answer_essay.stu_id= ? limit ?,?";
    public static String getCheckMyEssayAnswerPageNum="select count(*) from answer_essay where exam_id=? and stu_id = ? ";


    //学生端成绩查看
    public static String getStuGradePage="select * from grade,tb_exam where (grade.exam_id = tb_exam.exam_id) and grade.stu_id = ? and grade.is_finishCheck = 1  order by tb_exam.exam_id limit ?,?";
    public static String getStuGradePageNum="select count(*) from grade where stu_id=? and  grade.is_finishCheck = 1";
    //老师查看成绩
    public static String doViewStuGradePage="select * from grade,tb_student where (grade.stu_id = tb_student.id) and grade.exam_id = ? and grade.is_finishCheck = 1  order by grade.stu_id limit ?,?";
    public static String doViewStuGradePageNum="select count(*) from grade where exam_id=? and  grade.is_finishCheck = 1";
    public static String doViewStuGradeAllPage="select * from grade,tb_student ,tb_exam where (grade.stu_id = tb_student.id) and grade.is_finishCheck = 1  and grade.exam_id=tb_exam.exam_id and tb_exam.tea_id=? order by grade.stu_id limit ?,?";
    public static String doViewStuGradeAllPageNum="select count(*) from grade,tb_student ,tb_exam where (grade.stu_id = tb_student.id) and grade.is_finishCheck = 1  and grade.exam_id=tb_exam.exam_id and tb_exam.tea_id=? ";
    ///
    //所有学生,不是自己的学生
    public static String getViewStuPage= "select distinct tb_student.id,userName,sex from tb_student ,tea_stu where id not in (select id from tb_student ,tea_stu  where tea_stu.stu_id =tb_student.id    and  tea_stu.tea_id = ? ) limit ?,?";
    public static String getViewStuPageNum = "select count(distinct tb_student.id,userName,sex )from tb_student ,tea_stu where id not in (select id from tb_student ,tea_stu  where tea_stu.stu_id =tb_student.id    and  tea_stu.tea_id = ? )";

    //所有学生没有批改的
    public static String getViewStuByNoCheck="select * from stu_exam,tb_student,grade where (stu_exam.stu_id=grade.stu_id)and  (grade.stu_id=tb_student.id)and (stu_exam.exam_id = grade.exam_id )  and\n" +
            "stu_exam.exam_id = ? and grade.is_finishCheck = 0  order by grade.stu_id limit ?,?";
    public static String getViewStuByNoCheckNum="select count(*) from stu_exam,tb_student,grade where (stu_exam.stu_id=grade.stu_id)and  (grade.stu_id=tb_student.id)and (stu_exam.exam_id = grade.exam_id )  and\\n\" +\n" +
            "            \"stu_exam.exam_id = ? and grade.is_finishCheck = 0  order by grade.stu_id ";

    //获得交卷时间
    public static String getFinishTime="select * from stu_exam where exam_id = ? and stu_id=?";

}
