package enshu12;

import processing.core.PApplet;

public class Test extends Turtle {
    
    public void settings() {
        size(300, 200);
    }

    public void draw() {
        background(255);
        stroke(0);

        reset();
        //square(50);
        //triangle(50);
        //pentagon(50);
        star(50);
    }
    public static void main(String args[]) {
        PApplet.main("enshu12.Test");
    }

    
}