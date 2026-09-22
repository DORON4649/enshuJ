package enshu9;

import processing.core.PApplet;

public class CoordSys extends PApplet {
    
    Ball ball1 = new Ball(100, 100, 0, 0, 5);
    Ball ball2 = new Ball(-100, 100, 0, 0, 10);
    Ball ball3 = new Ball(-100, -100, 0, 0, 15);
    Ball ball4 = new Ball(100, -100, 0, 0, 20);

    
    public void settings() {
        size(400, 400);
    }

    public void setup() {

    }

    public void draw() {
        background(192);
        translate(width / 2, height / 2);
        scale(1, -1);

        line(-width / 2, 0, width, 2);
        line(0, -height / 2, 0, height / 2);

        int interval = 25;
        int tick = 5;

        for (int x = -width / 2; x <= width / 2; x += interval) {
            line(x, -tick, x, tick);
        }
        for (int y = -height / 2; y <= height / 2; y += interval) {
            line(-tick, y, tick, y);
        }

        Ball[] balls = {ball1, ball2, ball3, ball4};
        for (Ball b : balls) {
            circle(b.x, b.y, b.r * 2);
            b.x += 0.1f;
            b.y += 0.1f;
        }
    }

    void drawBall(Ball ball) {
        ball.x += 0.1f;
        ball.y += 0.1f;
        }

    public static void main(String args[]) {
        PApplet.main("enshu9.CoordSys");
    }
}