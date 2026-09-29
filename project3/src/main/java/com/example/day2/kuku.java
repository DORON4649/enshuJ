package com.example.day2;

public class kuku {
    public static void main(String[] args) {
        System.out.print("   ");
        for (int x = 1; x <= 9; x++) {
            System.out.printf("%3d", x);
        }
        System.out.println();

        for (int y = 1; y <= 9; y++) {
            System.out.printf("%2d |", y);
            for (int x = 1; x <= 9; x++) {
                if (x >= y) {
                    System.out.printf("%3d", x * y);
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }
}