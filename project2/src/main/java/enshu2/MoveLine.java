package enshu2;

import processing.core.PApplet;

public class MoveLine extends PApplet {

    int x;

    public void settings() {
        size(300, 150);
    }

    public void setup() {
        x = 100;
    }

    public void draw() {
        background(192);
        x += 1;
        line(x, 0, x, 149);
    }

    public static void main(String args[]) {
        PApplet.main(MoveLine.class.getName());
    }
}
