package com.example.day6;

public class ArgsTest4 {
    int grade;
    public static void main(String[] args) {
        int i = 60;
        ArgsTest4 kodai = new ArgsTest4();
        kodai.setGrade(i);
        System.out.println("1: grade = " + kodai.grade + " i = " + i);
        kodai.setGrade(70);
        System.out.println("2: grade = " + kodai.grade + " i = " + i);
    }
    public void setGrade(int g) {
        this.grade = g;
        //g++;
    }
}
