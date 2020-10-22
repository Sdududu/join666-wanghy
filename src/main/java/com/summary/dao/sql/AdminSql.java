package com.summary.dao.sql;

public class AdminSql {

    /////
    //名字查找试卷
    public static String  selectGradeByName= "select * from tb_exam where locate (?,subject)>0";
    public static String selectGradeByNameNum="select count(*) from tb_exam where locate (?,subject)>0";

    //名字查找学生
    public static String  selectStuByName= "select * from tb_student where locate (?,userName)>0";
    public static String selectStuByNameNum="select count(*) from tb_student where locate (?,userName)>0";

}
