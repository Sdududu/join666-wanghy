package com.summary.entity;

public class QuesEssay {
    //问答题题目
    private int number;
    private String es_content;
    private String es_img;
    private int exam_id;
    private double es_score;
    //问答题答题
    private String essay_res;
    private int stu_id;
    private String es_resimg;
    private int is_check;
    private double es_grade;



    public String getEs_resimg() {
        return es_resimg;
    }

    public void setEs_resimg(String es_resimg) {
        this.es_resimg = es_resimg;
    }

    public String getEssay_res() {
        return essay_res;
    }

    public void setEssay_res(String essay_res) {
        this.essay_res = essay_res;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getIs_check() {
        return is_check;
    }

    public void setIs_check(int is_check) {
        this.is_check = is_check;
    }

    public double getEs_grade() {
        return es_grade;
    }

    public void setEs_grade(double es_grade) {
        this.es_grade = es_grade;
    }

    public double getEs_score() {
        return es_score;
    }

    public void setEs_score(double es_score) {
        this.es_score = es_score;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEs_content() {
        return es_content;
    }

    public void setEs_content(String es_content) {
        this.es_content = es_content;
    }

    public String getEs_img() {
        return es_img;
    }

    public void setEs_img(String es_img) {
        this.es_img = es_img;
    }



    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }


    @Override
    public String toString() {
        return "QuesEssay{" +
                "number=" + number +
                ", es_content='" + es_content + '\'' +
                ", es_img='" + es_img + '\'' +
                ", exam_id=" + exam_id +
                ", es_score=" + es_score +
                ", essay_res='" + essay_res + '\'' +
                ", stu_id=" + stu_id +
                ", es_resimg='" + es_resimg + '\'' +
                ", is_check=" + is_check +
                ", es_grade=" + es_grade +
                '}';
    }
}
