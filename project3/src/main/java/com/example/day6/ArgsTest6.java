package com.example.day6;

public class ArgsTest6 {
    int grade;
    static int count = 0;
    public static void setGrade(ArgsTest6 student, int g) {
        student.grade = g;
        student.grade++;
        student = new ArgsTest6();
        student.grade = 70;
    }
    ArgsTest6() {
        count++;
    }
}
