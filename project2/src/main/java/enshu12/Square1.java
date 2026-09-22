package enshu12;

import processing.core.PApplet;

public class Square1 extends PApplet {
    public void settings() {
        size(500, 500);
    }

    float red = 0;

    public void draw() {
        background(190);

        rectMode(CENTER);
        translate(width/2, height/2);

        square(100);
        red += 0.01f;
    }

    void square(float length) {

        rotate(red);
        rect(0, 0, length, length);

        line(0, 0, 0, 100);
    }

    public static void main(String args[]) {
        PApplet.main("enshu12.Square1");
    }
}