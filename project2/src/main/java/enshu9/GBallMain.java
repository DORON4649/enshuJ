package enshu9;

import processing.core.PApplet;

public class GBallMain extends PApplet {

    GBall ball = new GBall(100, 100, 0, 0, 5, 1);

    public void settings() {
        size(400, 400);
    }

    public void setup() { }

    public void draw() {
        background(192);

        translate(width / 2, height / 2);
        scale(1, -1);
        
        line(-width / 2, 0, width, 0);
        line(0, -height / 2, 0, height / 2);

        ball.update();
        circle(ball.x, ball.y, ball.r * 2);
    }

    public static void main(String args[]) {
        PApplet.main("enshu9.GBallMain");
    }
}