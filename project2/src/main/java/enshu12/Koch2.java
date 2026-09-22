package enshu12;

import processing.core.PApplet;

public class Koch2 extends Turtle {
    public void settings() {
        size(500, 500); 
    }

    public void draw() {
        background(255);
        stroke(0);
        fill(0);

        int step = 2; 
        text(step, 10, 20);

        reset();
        
        move(-150, -100);
        right(deg90);

        for (int i = 0; i < 3; i++) {
            koch(300, step); 
            right(deg120);
        }
    }

    void koch(float length, int step) {
        if (step == 0) {
            forward(length);
        } else {
            koch(length / 3, step - 1);
            left(deg60);
            koch(length / 3, step - 1);
            right(deg120);
            koch(length / 3, step - 1);
            left(deg60);
            koch(length / 3, step - 1);
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu12.Koch2");
    }
}