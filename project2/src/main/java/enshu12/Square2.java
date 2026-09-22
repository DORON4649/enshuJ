package enshu12;

import processing.core.PApplet;

public class Square2 extends PApplet {
    public void settings() {
        size(500, 500);
    }

    float rad = 0;

    public void draw() {
        background(190);

        rectMode(CENTER);
        translate(width/2, height/2);

        square(100, 2);
        rad += 0.01f;
    }

    void square(float length, int step) {
        if (step == 0) {
            ;
        } else {
            push();
            rotate(rad);
            rect(0, 0, length, length);
            pop();

            push();
            translate(0, length);
            square(length/2, step-1);
            pop();

            push();
            translate(length, 0);
            square(length/2, step-1);
            pop();
        }
    }
    public static void main(String args[]) {
        PApplet.main("enshu12.Square2");
    }
}