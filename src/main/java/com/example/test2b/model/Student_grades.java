package com.example.test2b.model;

public class Student_grades {
    private int grade_id;
    private String student_name;

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        this.Score = score;
    }

    public Student_grades(int grade_id, String student_name, String subject, int score) {
        this.grade_id = grade_id;
        this.student_name = student_name;
        this.subject = subject;
        this.Score = score;
    }

    private String subject;
    private  int Score;

}