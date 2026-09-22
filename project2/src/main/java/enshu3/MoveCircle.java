package enshu3;

import processing.core.PApplet;

public class MoveCircle extends PApplet {

    int x;
    int y;
    int vx;
    int vy;

    public void settings() {
        size(300, 150);
    }

    public void setup() {
        x = width / 2;
        y = height / 2;
        vx = 1;
        vy = 1;
    }

    public void draw() {
        background(192);

        x += vx;
        y += vy;

        if (x >= width - 25) {
            vx = -1;
        }
        if (x <= 25) {
            vx = 1;
        }

        if (y >= height - 25) {
            vy = -1;
        }
        if (y <= 25) {
            vy = 1;
        }

        circle(x, y, 50);
        fill(10, 39, 234);

        if (x >= 150) {
            fill(255, 0, 0);
        } else {
            fill(90, 90, 0);
        }
    }

    public static void main(String args[]) {
        PApplet.main(MoveCircle.class.getName());
    }
}