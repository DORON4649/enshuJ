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
        System.out.println("[old]");
        int w = v / 16;
        if (w != 0) {
            printHex(w);
        } else {
            System.out.print("0");
        }
        printHex(v % 16);
        System.out.print(" ");
    }

    public static void printHex2(int v, int n) {
        System.out.println("[new]");
        int i, x = 1, w;
        for(i = 1; i < n; i++) x = x*16;
        for( i = 0; i < n; i++) {
            //System.out.println("[X = " + x + "]");
            w = v / x;
            v = v % x;
            printHex(w);
            x = x / 16;
        }
    }

    public static void main(String[] args) {
        printHex2(15);
        printHex2(15, 4);
    }
    /** 
    public static void main(String[] args) {
        int x, y;

        System.out.print("     ");
        for (x = 1; x < 16; x++) {
            printHex3(x, 2);
            System.out.print(" ");
        }
        System.out.println();

        for (x = 0; x < 5 + 3 * 15; x++) {
            System.out.print("-");
        }
        System.out.println();

        for (y = 1; y < 16; y++) {
            printHex3(y, 2);
            System.out.print(" | ");

            for ( x = 1; x < 16; x++) {
                printHex3(x * y, 2);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
        **/
}