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
    public static void sub(int z) {
        int x;
        for(x = 1; x < 10; x++) {
            if(x * z <10){
                System.out.print(" " + x * z + " ");
            } else {
                System.out.print(x * z + " ");
            }
        }
        z = 0;
    }
}
