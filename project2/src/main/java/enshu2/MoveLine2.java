package enshu2;

import processing.core.PApplet;

public class MoveLine2 extends PApplet {

    int x;
    int vx;

    public void settings() {
        size(300, 150);
    }

    public void setup() {
        x = 10;
        vx = 1;
    }

    public void draw() {
        background(192);
        x += vx;
        if (x == width-1) {
            vx = -1;
        }
        if (x == 0) {
            vx = 1;
        }
        line(x, 0, x, 149);
    }

    public static void main(String args[]) {
        PApplet.main(MoveLine2.class.getName());
    }
}
