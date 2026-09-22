package com.example.day1;

public class multiplication {
    public static void main(String[] args) {
    System.out.println("      ");
    for (int j = 1; j <= 9; j++) {
        System.out.printf("%3d", j);
    }
    System.out.println();

    System.out.println("-------------------------------");
    for (int i = 1; i <= 9; i++) {
        System.out.printf("%2d |", i);
        for (int j = 1; j <= 9; j++) {
            System.out.printf("%3d", i * j);
        }
        System.out.println();
    }
}
}
