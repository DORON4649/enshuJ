package enshu12;

import processing.core.PApplet;

public class TurtleSample extends Turtle {

    public void settings() {
        size(400, 400);
    }

    public void draw() {
        background(255);
        stroke(0);

        reset();
        forward(50);
        right(deg45);
        forward(50);
        right(deg45);
        forward(100);

        reset();
        left(deg90);
        forward(150);

        reset();
        move(50, 100);
        forward(100);

    }
    public static void main(String args[]) {
        PApplet.main("enshu12.TurtleSample");
    }
}