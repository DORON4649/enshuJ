package com.example.day4;

public class SetGradeSample {
    public static void main(String[] args) {
        CitStudent kodai = new CitStudent();
        kodai.no = "25G3999";
        kodai.name = "工大 太郎";
        kodai.year = 2;
        kodai.grade = 80;

        kodai.department = Integer.parseInt("" + kodai.no.charAt(3));
        showGrade(kodai);
    }

    public static void showGrade(CitStudent data) {
        String deptName = " ";
        if (data.department == 1) {
            deptName = "情報工学科";
        }else if (data.department == 2) {
            deptName = "情報ネットワーク学科";
        } else if (data.department == 3) {
            deptName ="高度情報工学科";
        }

        String eval = "";
        if (data.grade >= 90) {
            eval = "S";
        } else if (data.grade >=80) {
            eval = "A";
        } else if ( data.grade >=70) {
            eval = "B";
        } else if (data.grade >= 60) {
            eval = "C";
        } else {
            eval = "D";
        }

        System.out.println("No: " + data.no + " " + data.name + " " + data.grade + " " + deptName);
    }

    
}