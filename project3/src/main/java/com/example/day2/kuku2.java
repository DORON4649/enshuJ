package com.example.day2;

public class kuku2 {
    public static void main(String[] args) {
        int y;
        y = 1;
        while(y < 10) {
            sub(y);
            System.out.println();
            y++;
        }
    }
    public static void sub(int y) {
        int x;
        for(x = 1; x < 10; x++) {
            if(x * y <10){
                System.out.println(" " + x * y + " ");
            } else {
                System.out.println(x * y + " ");
            }
        }
    }
}
