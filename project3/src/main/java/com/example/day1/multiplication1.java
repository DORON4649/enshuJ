package com.example.day1;

public class multiplication1 {
    public static void main(String[] args) {
    System.out.println("      ");
    for (int j = 1; j <= 9; j++) {
        System.out.printf("%3d", j);
    }
    System.out.println();

    System.out.println("-------------------------------");
    for (int i = 1; i <= 7; i++) {
        System.out.printf("%2d |", i);
        for (int j = 1; j <= 9; j++) {
            System.out.printf("%3d", i * j);
        }
        System.out.println();
    }

    for (int i = 8; i <= 8; i++) {
        System.out.printf("%2d |", i);
        for (int j = 8; j <= 9; j++) {
            System.out.printf("%3d", i * j);
        }
        System.out.println();
    }

    for (int i = 9; i <= 9; i++) {
        System.out.printf("%2d |", i);
        for (int j = 9; j <= 9; j++) {
            System.out.printf("%3d", i * j);
        }
        System.out.println();
    }
}
}
