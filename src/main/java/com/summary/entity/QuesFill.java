package com.summary.entity;

public class QuesFill {

    private int exam_id;
    private int number;
    private String fill_img;
    private String fill_content;
    private String fill_res;
    private int stu_id;
    private int fill_right;
    private double fill_grade;
    private double fill_score;
    private String fill_answer;

    public String getFill_answer() {
        return fill_answer;
    }

    public void setFill_answer(String fill_answer) {
        this.fill_answer = fill_answer;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFill_img() {
        return fill_img;
    }

    public void setFill_img(String fill_img) {
        this.fill_img = fill_img;
    }

    public String getFill_content() {
        return fill_content;
    }

    public void setFill_content(String fill_content) {
        this.fill_content = fill_content;
    }

    public String getFill_res() {
        return fill_res;
    }

    public void setFill_res(String fill_res) {
        this.fill_res = fill_res;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getFill_right() {
        return fill_right;
    }

    public void setFill_right(int fill_right) {
        this.fill_right = fill_right;
    }

    public double getFill_grade() {
        return fill_grade;
    }

    public void setFill_grade(double fill_grade) {
        this.fill_grade = fill_grade;
    }

    public double getFill_score() {
        return fill_score;
    }

    public void setFill_score(double fill_score) {
        this.fill_score = fill_score;
    }

    @Override
    public String toString() {
        return "QuesFill{" +
                "exam_id=" + exam_id +
                ", number=" + number +
                ", fill_img='" + fill_img + '\'' +
                ", fill_content='" + fill_content + '\'' +
                ", fill_res='" + fill_res + '\'' +
                ", stu_id=" + stu_id +
                ", fill_right=" + fill_right +
                ", fill_grade=" + fill_grade +
                ", fill_score=" + fill_score +
                '}';
    }
}
