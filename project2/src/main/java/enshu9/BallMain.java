package enshu9;
import processing.core.PApplet;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class BallMain extends PApplet {
    Ball b1;
    Ball b2;
    ArrayList<Ball> balls;

    public void settings() {
        size(400, 400);
    }

    public void setup() {
        b1 = new Ball(100, 100, 2, 3, 20);
        b2 = new Ball(200, 200, -3, 2, 30);
        balls = new ArrayList<Ball>();
        balls.add(b1);
        balls.add(b2);

        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            float vx = rand.nextFloat() * 6 - 3; 
            float vy = rand.nextFloat() * 6 - 3; 
            Ball b = new Ball(i * 20, i * 20, vx, vy, 5);
            balls.add(b);
        }
    }

    public void draw() {
        background(192);

        for (Ball b : balls) {
            b.update(width, height);
            drawBall(b);
        }
    }

    void drawBall(Ball ball) {
    circle(ball.x, ball.y, ball.r * 2);    
    }


    public static void main(String args[]) {
        PApplet.main("enshu9.BallMain");
    }
}
