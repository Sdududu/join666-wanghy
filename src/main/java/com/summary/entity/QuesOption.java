package com.summary.entity;

public class QuesOption {
    //选择题的题目内容等
    private int number;
    private String op_content;
    private String op_img;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private String answer;
    private int exam_id;
    private double op_score;
    //试卷答题页面
    private int op_isright;
    private String op_res;

    private int stu_id;



    public int getOp_isright() {
        return op_isright;
    }

    public void setOp_isright(int op_isright) {
        this.op_isright = op_isright;
    }

    public String getOp_res() {
        return op_res;
    }

    public void setOp_res(String op_res) {
        this.op_res = op_res;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public double getOp_score() {
        return op_score;
    }

    public void setOp_score(double op_score) {
        this.op_score = op_score;
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

    public String getOp_content() {
        return op_content;
    }

    public void setOp_content(String op_content) {
        this.op_content = op_content;
    }

    public String getOp_img() {
        return op_img;
    }

    public void setOp_img(String op_img) {
        this.op_img = op_img;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuesOption{" +
                "number=" + number +
                ", op_content='" + op_content + '\'' +
                ", op_img='" + op_img + '\'' +
                ", op1='" + op1 + '\'' +
                ", op2='" + op2 + '\'' +
                ", op3='" + op3 + '\'' +
                ", op4='" + op4 + '\'' +
                ", answer='" + answer + '\'' +
                ", exam_id=" + exam_id +
                ", op_isright=" + op_isright +
                ", op_res='" + op_res + '\'' +
                ", op_score=" + op_score +
                ", stu_id=" + stu_id +
                '}';
    }


}
