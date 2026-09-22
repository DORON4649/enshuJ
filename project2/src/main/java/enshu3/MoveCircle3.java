package enshu3;

import processing.core.PApplet;

public class MoveCircle3 extends PApplet {

    int x, y, vx, vy;

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

        
        if (x >= width - 25) vx = -1;
        if (x <= 25) vx = 1;
        if (y >= height - 25) vy = -1;
        if (y <= 25) vy = 1;

        if (x < 150 && y < 75) {
            fill(255, 0, 0);      
        } else if (x >= 150 && y < 75) {
            fill(0, 255, 0);      
        } else if (x < 150 && y >= 75) {
            fill(0, 0, 255);      
        } else {
            fill(255, 255, 0);    
        }

        
        circle(x, y, 50);
    }

    public static void main(String args[]) {
        PApplet.main(MoveCircle3.class.getName());
    }
}