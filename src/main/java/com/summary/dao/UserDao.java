package com.summary.dao;

import com.summary.dao.sql.AdminSql;
import com.summary.dao.sql.ExamSql;
import com.summary.dao.sql.UserSql;
import com.summary.entity.Exam;
import com.summary.entity.User;
import com.summary.util.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {


    /**
     * 登录用户
     *
     * @param user
     * @return
     */
    public User userLogin(User user) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        User succeed = null;
        try {
            conn = C3P0Util.getConnection();
            if (user.getStatus() == 0) {
                ps = conn.prepareStatement(UserSql.stuLogin);
            } else if (user.getStatus() == 1) {
                ps = conn.prepareStatement(UserSql.teaLogin);
            } else {
                ps = conn.prepareStatement(UserSql.adminLogin);
            }

            ps.setInt(1, user.getId());
            ps.setString(2, user.getUserPwd());
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                succeed = new User();
                succeed.setId(resultSet.getInt("id"));
                succeed.setUserPwd(resultSet.getString("userPwd"));
                succeed.setStatus(resultSet.getInt("status"));
                succeed.setUserName(resultSet.getString("userName"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    public int insertUser(User user) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Connection conn = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();

            if (user.getStatus()== 0) {
                ps = conn.prepareStatement(UserSql.stuInsert);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getUserPwd());
                ps.setString(3, user.getSex());

            } else if (user.getStatus() == 1) {
                ps = conn.prepareStatement(UserSql.teaInsert);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getUserPwd());
            } else {
                ps = conn.prepareStatement(UserSql.adminInsert);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getUserPwd());
            }
            succeed = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }

    /**
     * 删除用户
     *
     * @param user
     * @return
     */
    public int deleteUser(User user) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Connection conn = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();

            if (user.getStatus() == 0) {
                ps = conn.prepareStatement(UserSql.stuDelete);
                ps.setString(1, user.getUserName());
                ps.setInt(2, user.getId());

            } else if (user.getStatus() == 1) {
                ps = conn.prepareStatement(UserSql.teaDelete);
                ps.setString(1, user.getUserName());
                ps.setInt(2, user.getId());
            }
            succeed = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }

    /**
     * 注册后获得当前用户的id
     */

    public User getUserId(User user) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        User succeed = null;
        try {
            conn = C3P0Util.getConnection();
            if (user.status == 0) {
                ps = conn.prepareStatement(UserSql.getStuId);
            } else if (user.status == 1) {
                ps = conn.prepareStatement(UserSql.getTeaId);
            } else {
                ps = conn.prepareStatement(UserSql.getAdminId);
            }
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                succeed = new User();
                succeed.setId(resultSet.getInt("id"));
                succeed.setUserPwd(resultSet.getString("userPwd"));
                succeed.setStatus(resultSet.getInt("status"));
                succeed.setUserName(resultSet.getString("userName"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }

    /**
     * 更改信息
     *
     * @param user
     * @return int
     */
    public int updateUser(User user) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            if (user.status == 0) {
                ps = conn.prepareStatement(UserSql.stuUpdate);
                ps.setString(1, user.getUserPwd());
                ps.setString(2, user.getSex());
                ps.setInt(3, user.getId());
            } else if (user.status == 1) {
                ps = conn.prepareStatement(UserSql.teaUpdate);
                ps.setString(1, user.getUserPwd());
                ps.setInt(2, user.getId());

            } else {
                ps = conn.prepareStatement(UserSql.adminUpdate);
                ps.setString(1, user.getUserPwd());
                ps.setInt(2, user.getId());

            }

            succeed = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }
        return succeed;
    }

    /**
     * 通过年级查找学生
     * @param
     * @return
     */
        public List<User> selectStuByGrade(String grade,int tea_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        List<User> stuList= new ArrayList<>();
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(UserSql.selectStuByGrade);
            ps.setString(1, "%"+ grade +"%");
            ps.setInt(2, tea_id);
            resu = ps.executeQuery();
            while(resu.next()){
                User student = new User();
                student.setSex(resu.getString("sex"));
                student.setId(resu.getInt("id"));
                student.setUserName(resu.getString("userName"));
                stuList.add(student);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            C3P0Util.releaseConnection(conn, ps, resu);
        }
        return  stuList;

    }

    /**
     * 通过名称查找试卷总条数
     * @return
     */
    public int selectStuByGradeNum(String grade,int tea_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            ps=conn.prepareStatement(UserSql.selectStuByGradeNum);
            ps.setString(1, "%"+ grade +"%");
            ps.setInt(2, tea_id);
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            C3P0Util.releaseConnection(conn, ps, resu);
        }
        return  num;
    }

    /**
     * 添加学生
     *
     */

    public int addChioceStu(int stu_id,int tea_id){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        int succeed = 0;
        try{
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(UserSql.addChioceStu);
            ps.setInt(1,stu_id);
            ps.setInt(2,tea_id);
            succeed = ps.executeUpdate();


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resu);
        }
         return succeed;

    }



    /**
     * 通过名称查找试卷总条数
     * @return
     */
    public int isUser(User user){
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resu =null;
        int num = 0;
        try {
            conn= C3P0Util.getConnection();
            if (user.status == 0) {
                ps = conn.prepareStatement(UserSql.isStudent);
            } else if (user.status == 1) {
                ps = conn.prepareStatement(UserSql.isTeacher);
            } else {
                ps = conn.prepareStatement(UserSql.isAdmin);

            }

            ps.setInt(1, user.getId());
            ps.setString(2, user.getUserPwd());
            resu = ps.executeQuery();
            resu.first();
            num = resu.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            C3P0Util.releaseConnection(conn, ps, resu);
        }
        return  num;
    }


}