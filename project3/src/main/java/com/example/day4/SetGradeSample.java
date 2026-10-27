package com.example.day4;

public class SetGradeSample {
    public static void main(String[] args) {
    CitStudent2 kodai = new CitStudent2("25G3999", "工大 太郎");

    kodai.setYear(2);
    kodai.setGrade(80);
    kodai.setDepartment(Integer.parseInt("" + kodai.getNo().charAt(3)));

    showGrade(kodai);
}
    public static void showGrade(CitStudent2 data) {
        String deptName = " ";
        if (data.getDepartment() == 1) {
            deptName = "情報工学科";
        }else if (data.getDepartment() == 2) {
            deptName = "情報ネットワーク学科";
        } else if (data.getDepartment() == 3) {
            deptName ="高度情報工学科";
        }

        String eval = "";
        if (data.getGrade() >= 90) {
            eval = "S";
        } else if (data.getGrade() >=80) {
            eval = "A";
        } else if ( data.getGrade() >=70) {
            eval = "B";
        } else if (data.getGrade() >= 60) {
            eval = "C";
        } else {
            eval = "D";
        }

        System.out.println("No: " + data.getNo() + " " + data.getName() + " " + deptName + " " + data.getGrade() + " " + eval);
    }

    
}