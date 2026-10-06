package com.example.day3;

public class kuku {
    public static void printHex(int v) {
        if (v < 10) {
            System.out.print(v);
        } else {
            System.out.print((char)('a' + v - 10));
        }
    }

    public static void printHex2(int v) {
        int w = v / 16;
        if (w != 0) {
            printHex(w);
        } else {
            System.out.print("0");
        }
        printHex(v % 16);
        System.out.print(" ");
    }

    public static void main(String[] args) {
        int x, y;

        System.out.print("     ");
        for (x = 1; x < 16; x++) {
            printHex2(x);
        }
        System.out.println();

        for (x = 0; x < 5 + 3 * 15; x++) {
            System.out.print("-");
        }
        System.out.println();

        for (y = 1; y < 16; y++) {
            printHex2(y);
            System.out.print("| ");

            for (x = 1; x < 16; x++) {
                printHex2(x * y);
            }
            System.out.println();
        }
    }
}