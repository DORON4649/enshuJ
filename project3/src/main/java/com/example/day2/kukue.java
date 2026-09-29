package com.example.day2;

public class kukue {
    public static void main(String[] args) {
        int x, y;
//        y = 1;
        while (y < 10) {
            for (x = 1; x < 10; x++) {
                if (x * y < 10) {
                    System.out.println(" " + x * y + " ");
                } else {
                    System.out.println(x * y + " ");
                }
                System.out.println();
                y++;
            }
        }

    }
}


/* This program Error code
Exception in thread "main" java.lang.Error: Unresolved compilation problems: 
        The local variable y may not have been initialized
        The local variable y may not have been initialized
        The local variable y may not have been initialized
        The local variable y may not have been initialized
        The local variable y may not have been initialized

        at com.example.day2.kukue.main(kukue.java:7)



        EOF>*/