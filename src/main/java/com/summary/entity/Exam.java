package com.summary.entity;



public class Exam {
    private int exam_id;
    private String subject;
    private String times;
    private String publish_date;
    private String deadline;
    private int tea_id;
    private int isfinish;
    private double total_points;
    private int complete;
//    private int people;
//    private int undone;





    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public int getIsfinish() {
        return isfinish;
    }

    public void setIsfinish(int isfinish) {
        this.isfinish = isfinish;
    }

    public double getTotal_points() {
        return total_points;
    }

    public void setTotal_points(double total_points) {
        this.total_points = total_points;
    }

    public int getTea_id() {
        return tea_id;
    }

    public void setTea_id(int tea_id) {
        this.tea_id = tea_id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }


    @Override
    public String toString() {
        return "Exam{" +
                "exam_id=" + exam_id +
                ", subject='" + subject + '\'' +
                ", times='" + times + '\'' +
                ", publish_date='" + publish_date + '\'' +
                ", deadline='" + deadline + '\'' +
                ", tea_id=" + tea_id +
                ", isfinish=" + isfinish +
                ", total_points=" + total_points +
                '}';
    }
}

