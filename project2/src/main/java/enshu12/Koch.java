package enshu12;

import processing.core.PApplet;

public class Koch extends Turtle{
    public void settings() {
        size(500, 200);
    }

    public void draw() {
        background(255);
        stroke(0);
        fill(0);

        int step = 3;
        text(step, 10, 10);

        reset();
        move(-200, 50);
        right(deg90);
        koch(400, step);
    }

    void koch(float length, int step) {
        if (step == 0) {
            forward(length);
        } else {
            koch(length/3, step-1);
            left(deg60);
            koch(length/3, step-1);
            right(deg120);
            koch(length/3, step-1);
            left(deg60);
            koch(length/3, step-1);
        }
    }
    public static void main(String args[]) {
        PApplet.main("enshu12.Koch");
    }

}
