package enshu3;

import processing.core.PApplet;

public class Circle7 extends PApplet {

    public void settings() {
        size(400, 400);
    }

    public void draw() {
        background(192);
        fill(255);
        stroke(0);

        float centerX = width / 2;
        float centerY = height / 2;
        float radius = 100;

        for (int i = 0; i < 10; i++) {
            float angle = TWO_PI / 10 * i;
            float x = centerX + cos(angle) * radius;
            float y = centerY + sin(angle) * radius;
            circle(x, y, 30);
        }
        
    }

    public static void main(String[] args) {
        PApplet.main(Circle7.class.getName());
    }
}