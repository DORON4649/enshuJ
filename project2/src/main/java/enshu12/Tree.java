package enshu12;

import processing.core.PApplet;

public class Tree extends Turtle {

    public void settings() {
        size(250, 400);
    }

    public void draw() {
        background(255);
        stroke(0);
        fill(0);

        int step = 0;
        text(step, 10, 10);

        reset();
        move(0, 150);
        tree(150, step);
    }

    void tree(float length, int step) {
        if (step == 0) {
            forward(length);
        } else {
            forward(length);

            push();
            right(deg30);
            tree(length * 0.5f, step - 1);
            pop();

            push();
            left(deg30);
            tree(length * 0.5f, step - 1);
            pop();
        }
    }

    public static void main(String args[]) {
        PApplet.main(Tree.class.getName());
    }
}