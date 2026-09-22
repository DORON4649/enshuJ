package enshu3;

import processing.core.PApplet;

public class hanabi extends PApplet {

    float y = 400;
    float radius = 0;

    public void settings() {
        size(400, 400);
    }

    public void setup() {
    }

    public void draw() {
        background(0);
        fill(255);

        y = y - 3;

        if (y < 200) {
            radius = radius + 2;

            for (int i = 0; i < 10; i++) {
                float angle = TWO_PI / 10 * i;
                float x = 200 + cos(angle) * radius;
                float fireY = 200 + sin(angle) * radius;

                circle(x, fireY, 10);
            }
        } else {
            circle(200, y, 15);
            radius = 0;
        }

        if (y < -50) {
            y = 400;
        }
    }

    public static void main(String[] args) {
        PApplet.main(hanabi.class.getName());
    }
}