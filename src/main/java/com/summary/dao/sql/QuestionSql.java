package com.summary.dao.sql;

public class QuestionSql {
    /**
     * 选择题
     */
    public static String addQuesOption= "insert into question_option (exam_id,number,op_content,op_img,op1,op2,op3,op4,answer,op_score)values(?,?,?,?,?,?,?,?,?,?)";

    public static String deleteQuesOption="delete from question_option where exam_id = ? and number = ?";

    public static String updateQuesOption= "update  question_option set op_content =?,op_img =?,op1 = ?,op2 = ?,op3 = ?,op4 = ? ,answer = ?,op_score = ? where exam_id = ? and number = ?";

    public static String isRepeatNumber="select count(*) from question_option where exam_id = ? and number = ?";

    public static String selectQuesOption="select * from question_option where exam_id = ? and number = ?";

    /**
     * 填空题
     */
    public static String addQuesFill= "insert into question_fill (exam_id,number,fill_content,fill_img,fill_answer,fill_score)values(?,?,?,?,?,?)";

    public static String deleteQuesFill="delete from question_fill where exam_id = ? and number = ?";

    public static String updateQuesFill= "update  question_fill set fill_content =?,fill_img =? ,fill_answer = ?,fill_score = ? where exam_id = ? and number = ?";

    public static String isRepeatNumberFill="select count(*) from question_fill where exam_id = ? and number = ?";

    public static String selectQuesFill="select * from question_fill where exam_id = ? and number = ?";


    /**
     * 问答题
     *
     */
    public static String addQuesEssay= "insert into question_essay (exam_id,number,es_content,es_img,es_score)values(?,?,?,?,?)";

    public static String deleteQuesEssay="delete from question_essay where exam_id = ? and number = ?";

    public static String updateQuesEssay= "update  question_essay set es_content =?,es_img =? ,es_score = ? where exam_id = ? and number = ?";

    public static String isRepeatNumberEss="select count(*) from question_essay where exam_id = ? and number = ?";

    public static String selectQuesEssay="select * from answer_essay where exam_id = ? and stu_id=?";
    public static String selectQuesEssayNumber="select * from question_essay where exam_id = ? and number=?";

    public static String markEssay="update answer_essay set es_grade=?,is_check=1 where exam_id=? and number=? and stu_id=?";

    public static String markEssayFinish ="update grade set is_finishCheck = 1 , grade=? where exam_id = ? and stu_id = ?";

    public static String addEsGrades = "update grade set es_grades=? where exam_id = ? and stu_id = ?";


    public static String getFillGrade="select fill_grade from grade where exam_id = ? and stu_id=?";
    public static String getOptionGrade="select op_grade from grade where exam_id = ? and stu_id=?";

}
