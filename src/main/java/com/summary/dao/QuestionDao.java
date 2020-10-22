package com.summary.dao;

import com.summary.dao.sql.QuestionSql;
import com.summary.dao.sql.UserSql;
import com.summary.entity.QuesEssay;
import com.summary.entity.QuesFill;
import com.summary.entity.QuesOption;
import com.summary.entity.User;
import com.summary.util.C3P0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuestionDao {







    /**
     * 添加选择题
     * @param quesOption
     * @return
     */

    public int addQuesOption(QuesOption quesOption) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {

            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.addQuesOption);
            ps.setInt(1, quesOption.getExam_id());
            ps.setInt(2, quesOption.getNumber());
            ps.setString(3, quesOption.getOp_content());
            ps.setString(4, quesOption.getOp_img());
            ps.setString(5, quesOption.getOp1());
            ps.setString(6, quesOption.getOp2());
            ps.setString(7, quesOption.getOp3());
            ps.setString(8, quesOption.getOp4());
            ps.setString(9, quesOption.getAnswer());
            ps.setDouble(10, quesOption.getOp_score());

            succeed = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }

    /**
     * 获得添加的选择题目内容
     * @param quesOption
     * @return
     */
    public QuesOption selectQuesOption(QuesOption quesOption){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        QuesOption succeed = null;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.selectQuesOption);
            ps.setInt(1, quesOption.getExam_id());
            ps.setInt(2, quesOption.getNumber());
            resultSet = ps.executeQuery();
        while (resultSet.next()) {
            succeed = new QuesOption();
            succeed.setExam_id(resultSet.getInt("exam_id"));
            succeed.setNumber(resultSet.getInt("number"));
            succeed.setOp_content(resultSet.getString("op_content"));
            succeed.setOp_img(resultSet.getString("op_img"));
            succeed.setOp1(resultSet.getString("op1"));
            succeed.setOp2(resultSet.getString("op2"));
            succeed.setOp3(resultSet.getString("op3"));
            succeed.setOp4(resultSet.getString("op4"));
            succeed.setAnswer(resultSet.getString("answer"));
            succeed.setOp_score(resultSet.getDouble("op_score"));
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;


    }



    /**
     * 删除选择题目
     * @param quesOption
     * @return
     */
    public int deleteQuesOption(QuesOption quesOption) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.deleteQuesOption);
            ps.setInt(1, quesOption.getExam_id());
            ps.setInt(2, quesOption.getNumber());
            succeed=ps.executeUpdate();;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }

    /**
     * 更新选择题目
     * @param quesOption
     * @return
     */
    public int updateQuesOption(QuesOption quesOption) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
        conn = C3P0Util.getConnection();
        ps = conn.prepareStatement(QuestionSql.updateQuesOption);
        ps.setInt(9, quesOption.getExam_id());
        ps.setInt(10, quesOption.getNumber());
        ps.setString(1, quesOption.getOp_content());
        ps.setString(2, quesOption.getOp_img());
        ps.setString(3, quesOption.getOp1());
        ps.setString(4, quesOption.getOp2());
        ps.setString(5, quesOption.getOp3());
        ps.setString(6, quesOption.getOp4());
        ps.setString(7, quesOption.getAnswer());
         ps.setDouble(8, quesOption.getOp_score());

            succeed = ps.executeUpdate();
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        C3P0Util.releaseConnection(conn, ps, resultSet);
    }

        return succeed;

    }

    /**
     * 比较重复的选择
     * @param exam_id
     * @param number
     * @return
     */
    public int isRepeatNumber(int exam_id,int number){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {

            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.isRepeatNumber);
            ps.setInt(1, exam_id);
            ps.setInt(2, number);
            resultSet = ps.executeQuery();
            resultSet.first();
            succeed =resultSet.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }


















    /**
     * 添加填空题
     * @param quesFill
     * @return
     */

    public int addQuesFill(QuesFill quesFill) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {

            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.addQuesFill);
            ps.setInt(1, quesFill.getExam_id());
            ps.setInt(2, quesFill.getNumber());
            ps.setString(3, quesFill.getFill_content());
            ps.setString(4, quesFill.getFill_img());
            ps.setString(5, quesFill.getFill_answer());
            ps.setDouble(6, quesFill.getFill_score());

            succeed = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }

    /**
     * 获得添加的填空题目内容
     * @param quesFill
     * @return
     */
    public QuesFill selectQuesFill(QuesFill quesFill){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        QuesFill succeed = null;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.selectQuesFill);
            ps.setInt(1, quesFill.getExam_id());
            ps.setInt(2, quesFill.getNumber());
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                succeed = new QuesFill();
                succeed.setExam_id(resultSet.getInt("exam_id"));
                succeed.setNumber(resultSet.getInt("number"));
                succeed.setFill_content(resultSet.getString("fill_content"));
                succeed.setFill_img(resultSet.getString("fill_img"));
                succeed.setFill_answer(resultSet.getString("fill_answer"));
                succeed.setFill_score(resultSet.getDouble("fill_score"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;


    }

    /**
     * 删除选择题目
     * @param quesFill
     * @return
     */
    public int deleteQuesFill(QuesFill quesFill) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.deleteQuesFill);
            ps.setInt(1, quesFill.getExam_id());
            ps.setInt(2, quesFill.getNumber());
            succeed=ps.executeUpdate();;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }

    /**
     * 更新选择题目
     * @param quesFill
     * @return
     */
    public int updateQuesFill(QuesFill quesFill) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.updateQuesFill);
            ps.setInt(5, quesFill.getExam_id());
            ps.setInt(6, quesFill.getNumber());
            ps.setString(1, quesFill.getFill_content());
            ps.setString(2, quesFill.getFill_img());
            ps.setString(3, quesFill.getFill_answer());
            ps.setDouble(4, quesFill.getFill_score());

            succeed = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }

    /**
     * 比较重复的选择
     * @param exam_id
     * @param number
     * @return
     */
    public int isRepeatNumberFill(int exam_id,int number){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {

            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.isRepeatNumberFill);
            ps.setInt(1, exam_id);
            ps.setInt(2, number);
            resultSet = ps.executeQuery();
            resultSet.first();
            succeed =resultSet.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }





























    /**
     * 添加问答
     * @param quesEssay
     * @return
     */

    public int addQuesEssay(QuesEssay quesEssay) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {

            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.addQuesEssay);
            ps.setInt(1, quesEssay.getExam_id());
            ps.setInt(2, quesEssay.getNumber());
            ps.setString(3, quesEssay.getEs_content());
            ps.setString(4, quesEssay.getEs_img());
            ps.setDouble(5, quesEssay.getEs_score());
            succeed = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }

    /**
     * 获得添加的问答题目内容
     * @param quesEssay
     * @return
     */
    public QuesEssay selectQuesEssay(QuesEssay quesEssay){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        QuesEssay succeed = null;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.selectQuesEssayNumber);
            ps.setInt(1, quesEssay.getExam_id());
            ps.setInt(2, quesEssay.getNumber());
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                succeed = new QuesEssay();
                succeed.setExam_id(resultSet.getInt("exam_id"));
                succeed.setNumber(resultSet.getInt("number"));
                succeed.setEs_content(resultSet.getString("es_content"));
                succeed.setEs_img(resultSet.getString("es_img"));
                succeed.setEs_score(resultSet.getDouble("es_score"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;


    }

    /**
     * 删除问答题目
     * @param quesEssay
     * @return
     */
    public int deleteQuesEssay(QuesEssay quesEssay) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.deleteQuesEssay);
            ps.setInt(1, quesEssay.getExam_id());
            ps.setInt(2, quesEssay.getNumber());
            succeed=ps.executeUpdate();;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }

    /**
     * 更新问答题目
     * @param quesEssay
     * @return
     */
    public int updateQuesEssay(QuesEssay quesEssay) {

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.updateQuesEssay);
            ps.setInt(4, quesEssay.getExam_id());
            ps.setInt(5, quesEssay.getNumber());
            ps.setString(1, quesEssay.getEs_content());
            ps.setString(2, quesEssay.getEs_img());
            ps.setDouble(3, quesEssay.getEs_score());

            succeed = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;

    }























    /**
     *  批改试卷
     * @param  quesEssay
     * @return
     */
    public int markEssay(QuesEssay quesEssay){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {

            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.markEssay);
            ps.setDouble(1,quesEssay.getEs_grade());
            ps.setInt(2, quesEssay.getExam_id());
            ps.setInt(3,  quesEssay.getNumber());
            ps.setInt(4,  quesEssay.getStu_id());

            succeed = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }





    /**
     *批改完成，显示试卷成绩
     * @param  quesEssay
     * @return
     */
    public int markEssayFinish(QuesEssay quesEssay,Double grade){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {

            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.markEssayFinish);
            ps.setDouble(1, grade);
            ps.setInt(2, quesEssay.getExam_id());
            ps.setInt(3,  quesEssay.getStu_id());
            succeed = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }

    /**
     * 获得填空题的分数
     * @param exam_id
     * @param stu_id
     * @return
     */
    public double getFillGrade(int exam_id,int stu_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        double succeed = 0;
        try {

            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.getFillGrade);
            ps.setInt(1, exam_id);
            ps.setInt(2, stu_id);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                succeed =resultSet.getDouble("fill_grade_grade");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }

    /**
     * 获得选择题的成绩
     * @param exam_id
     * @param stu_id
     * @return
     */
    public double getOptionGrade(int exam_id,int stu_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        double succeed = 0;
        try {

            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.getOptionGrade);
            ps.setInt(1, exam_id);
            ps.setInt(2, stu_id);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                succeed =resultSet.getDouble("op_grade");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }
    /**
     * 比较重复的选择
     * @param exam_id
     * @param number
     * @return
     */
    public int isRepeatNumberEss(int exam_id,int number){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {

            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.isRepeatNumberEss);
            ps.setInt(1, exam_id);
            ps.setInt(2, number);
            resultSet = ps.executeQuery();
            resultSet.first();
            succeed =resultSet.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }


    /**
     * 获得问答题总成绩
     * @param
     * @return
     */
    public double getEssGrades(QuesEssay quesEssay){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        QuesEssay succeed = null;
        double es_grades = 0;
        try {
            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.selectQuesEssay);
            ps.setInt(1, quesEssay.getExam_id());
            ps.setInt(2, quesEssay.getStu_id());
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                succeed = new QuesEssay();
                es_grades = es_grades + resultSet.getDouble("es_grade");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return es_grades;


    }


    /**
     *把问答题成绩计入成绩中
     * @param
     * @return
     */
    public int addEsGrades(double  es_grades,int exam_id,int stu_id){

        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet resultSet = null;
        int succeed = 0;
        try {

            conn = C3P0Util.getConnection();
            ps = conn.prepareStatement(QuestionSql.addEsGrades);
            ps.setDouble(1, es_grades);
            ps.setInt(2,  exam_id);
            ps.setInt(3,  stu_id);
            succeed = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            C3P0Util.releaseConnection(conn, ps, resultSet);
        }

        return succeed;
    }


}
