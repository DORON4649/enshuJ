package com.example.day1;

public class fibnacci {
    public static void main(String[] ars) {
        for(int i = 0; i <10000; i++) {
            int a = 0;
            int b = 1;
            int c = a + b;
            while(c < i) {
                a = b;
                b = c;
                c = a + b;
            }
            if(c == i) {
                System.out.println(i);
            }
        }
    }
}
