package enshu12;

import processing.core.PApplet;

public class Test2 extends Turtle{

    public void settings() {
        size(800, 200);
    }

    public void draw() {
        background(255);
        stroke(0);

        reset();

        for (int i = 0; i < 5; i++) {
            square(50);
            move(60, 0);
        }

    }

    public static void main(String args[]) {
        PApplet.main("enshu12.Test2");
    }

}
