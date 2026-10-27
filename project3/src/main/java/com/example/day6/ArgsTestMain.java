package com.example.day6;

import com.example.day5.ArgsTest;

public class ArgsTestMain {
    public static void main(String[] args) {
        int i =60;
        ArgsTest6 kodai = new ArgsTest6();
        ArgsTest6.setGrade(kodai, i);
        System.out.println("1: grade = " + kodai.grade + " i = " + i);
        ArgsTest6.setGrade(kodai, 70);
        System.out.println("2: grade = " + kodai.grade + " i = " + i);
        
        new ArgsTest6();
        //ArgsTest6.count;
        System.out.println("3: count = " + ArgsTest6.count);
    }
    
}
