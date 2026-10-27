package com.example.day6;

public class ArgsTest5 {
    int grade;
    public static void main(String[] args){
        int i = 60;
        ArgsTest5 kodai = new ArgsTest5();
        setGrade(kodai, i);
        System.out.println("1: grade = " + kodai.grade + " i = " + i);
        setGrade(kodai, 70);
        System.out.println("2: grade = " + kodai.grade + " i = " + i);  
    }
    public static void setGrade(ArgsTest5 student, int grade){
        student.grade = grade;
        student.grade++;
        student = new ArgsTest5();
        student.grade = 80;
    }
}
