package com.summary.dao.sql;

public class UserSql {
    //登录
    public static String stuLogin="select * from tb_student where id = ? and userPwd= ?";

    public static String teaLogin="select * from tb_teacher where id = ? and userPwd= ?";

    public static String adminLogin="select * from tb_admin where id = ? and userPwd= ?";

    //注册
    public static String stuInsert="insert into tb_student (userName,userPwd,status,sex)Values(?,?,0,?)";

    public static String teaInsert="insert into tb_teacher (userName,userPwd,status)Values(?,?,1)";

    public static String adminInsert="insert into tb_admin (userName,userPwd,status)Values(?,?,2)";

    public static String getStuId="select * from tb_student order by id desc limit 1";
    public static String getTeaId="select * from tb_teacher order by id desc limit 1";
    public static String getAdminId="select * from tb_admin order by id desc limit 1";

    //删除用户
    public static String stuDelete = "delete from tb_student where userName = ? and id = ?";
    public static String teaDelete = "delete from tb_teacher where userName = ? and id = ?";

    //更改用户信息

    public static String stuUpdate="update tb_student set userPwd = ? , sex = ? where  id = ?";

    public static String teaUpdate="update tb_teacher set userPwd = ? where  id = ?";

    public static String adminUpdate="update tb_admin set userPwd = ? where  id = ?";

    //根据年级查学生
//    public static String selectStuByGrade = "select * from tb_student ,tea_stu where (id like  '?%' ) and (tb_student.id != tea_stu.stu_id) and tea_stu=tea.id = ?";
//        public static String selectStuByGrade = "select * from tb_student ,tea_stu where like (id like  '?%' ) and (tb_student.id != tea_stu.stu_id) and tea_stu=tea.id and tea.id = ?";
    public static String selectStuByGrade = "select distinct tb_student.id,userName,sex from tb_student ,tea_stu where (id like ?) and id not in (select id from tb_student ,tea_stu  where tea_stu.stu_id =tb_student.id    and  tea_stu.tea_id = ? )";
    public static String selectStuByGradeNum = "select count(distinct tb_student.id,userName,sex )from tb_student ,tea_stu where (id like ? ) and id not in (select id from tb_student ,tea_stu  where tea_stu.stu_id =tb_student.id    and  tea_stu.tea_id = ? )";
                ////

    //    public static String selectStuByGradeNum = "select * from tb_student ,tea_stu where like '?%' and (tb_student.id != tea_stu.stu_id) and tea_stu=tea.id and tea.id = ?";
    //增加学生
    public static String addChioceStu = "insert into tea_stu (stu_id,tea_id) values (?,?)";


    //判断用户是否存在
    public static String isStudent = "select count(*) from tb_student where id=? and userPwd=?";
    public static String isTeacher = "select count(*) from tb_teacher where id=? and userPwd=?";
    public static String isAdmin= "select count(*) from tb_admin where id=? and userPwd=?";



}
