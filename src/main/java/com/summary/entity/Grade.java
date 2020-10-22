package com.summary.entity;

public class Grade {
    private int stu_id;
    private int exam_id;
    private double op_grade;
    private double es_grades;
    private double grade;
    private int is_finishCheck;
    private String subject;
    private String userName ;
    private double fill_grade;
    private String finishTime;

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public double getFill_grade() {
        return fill_grade;
    }

    public void setFill_grade(double fill_grade) {
        this.fill_grade = fill_grade;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getIs_finishCheck() {
        return is_finishCheck;
    }

    public void setIs_finishCheck(int is_finishCheck) {
        this.is_finishCheck = is_finishCheck;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public double getOp_grade() {
        return op_grade;
    }

    public void setOp_grade(double op_grade) {
        this.op_grade = op_grade;
    }

    public double getEs_grades() {
        return es_grades;
    }

    public void setEs_grades(double es_grades) {
        this.es_grades = es_grades;
    }


    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;

    }

    @Override
    public String toString() {
        return "Grade{" +
                "stu_id=" + stu_id +
                ", exam_id=" + exam_id +
                ", op_grade=" + op_grade +
                ", es_grades=" + es_grades +
                ", grade=" + grade +
                ", is_finishCheck=" + is_finishCheck +
                ", subject='" + subject + '\'' +
                ", userName='" + userName + '\'' +
                ", fill_grade=" + fill_grade +
                ", finishTime='" + finishTime + '\'' +
                '}';
    }
}
