package enshu8;

import processing.core.PApplet;
import processing.core.PVector;

public class Circle1 extends PApplet {
    PVector circle;
    public void settings() {
        size(400, 400);
    }

    public void setup() {
        circle = new PVector(0, randomY());
    }

    float randomY() {
        float y = random(0, height-20);
        return y + 10;
    }

    public void draw() {
        background(192);
        circle(circle.x, circle.y, 20);
        circle.x += 4;

        if(circle.x>width) {
        circle.x = 0;
        circle.y = randomY();
    }
    }


    public static void main(String args[]) {
        PApplet.main("enshu8.Circle1");

    }
}
