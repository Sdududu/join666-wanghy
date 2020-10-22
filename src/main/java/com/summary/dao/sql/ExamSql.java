package com.summary.dao.sql;

public class ExamSql {

    //添加
    public static String addExam=" insert into tb_exam (subject,times,deadline,tea_id,publish_date,total_points,isfinish) values (?,?,?,?,now(),?,1)";

//    public static String selectExam=" select * from tb_exam where subject = ? and deadline = ?";
    //删除
//    public static String savePaper="insert into tea_exam(tea_id,exam_id,isfinish) values (?,?,1)";
//    public static String deleteSavePaper = "delete from tea_exam where exam_id = ? and tea_id = ?";
    public static String deleteExam = "delete from tb_exam where exam_id = ?";
    public static String deleteExamInStu = "delete from stu_exam where exam_id = ?";
      // 更新试卷
    public static String updateExam="update tb_exam set subject = ? , times = ? , deadline=? , total_points = ? where exam_id = ?";
    //选择试卷的ID
    public static String selectExamId= "select * from tb_exam order by exam_id desc limit 1";
    //获得学生的的Id从老师
    public static String selectStuIdByTea="select stu_id from tea_stu where tea_id = ?";
    //加入到学生试卷对应的
    public static String addExamStu="insert into stu_exam (exam_id,stu_id,complete) values(?,?,0) ";
    //查看现在的同学
    public static String viewPeopleNow="select count(*) from stu_exam where complete = 1 and exam_id = ?";
    //查看没有完成的学生
    public static String viewUndone="select count(*) from stu_exam where complete = 0 and exam_id = ?";
//      //查看所有还没考试的学生ID
//    public static String viewStuIdFromExam = "select * from stu_exam where complete = 1 and exam_id = ?";
    //查看截止时间
    public static String selectDeadline = "select * from tb_exam";
    //更新是否截止
    public static String updateIsfinish = "update tb_exam set isfinish = 0 where exam_id = ? ";
    //答题情况
    //加入到选择答案上面
    public static String addMyOption = "insert into answer_option (exam_id,number,op_res,stu_id,op_isright) values (?,?,?,?,?)";
    public static String addMyFill = "insert into answer_fill (exam_id,number,fill_res,stu_id,fill_right) values (?,?,?,?,?)";
    public static String addMyEssay = "insert into answer_essay (exam_id,number,essay_res,stu_id,es_resimg,is_check) values (?,?,?,?,?,0)";


    //把单选题成绩加入
    public static String insertGradeOp = "insert into grade(stu_id,exam_id,op_grade,fill_grade)values(?,?,?,?)";
     //学生完成考试
    public static String isCompleteStu = "update stu_exam set complete = 2 and finishTime = now()  where stu_id = ? and exam_id  =?";
    public static String isCompleteNow= "update stu_exam set complete = 1 where stu_id = ? and exam_id  =?";
}
