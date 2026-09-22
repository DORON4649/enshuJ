package enshu3;

import processing.core.PApplet;

public class Circle2 extends PApplet {
    public void settings() {
        size(200, 200);
    }

    public void setup() {
    }

    public void draw() {
        background(192);
        for (int x = 20; x <= width; x += 20) {
            for (int y = 20; y <= height; y += 20) {

                fill(255);
                circle(x, y, 20);

            }
        }
    }

    public static void main(String args[]) {
        PApplet.main(Circle2.class.getName());
    }
}
