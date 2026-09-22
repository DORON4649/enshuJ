package enshu2;

import processing.core.PApplet;

public class ArtLine extends PApplet {
    int x;
    int y;

    public void settings() {
        size(300, 300);
    }

    public void setup() {
        x = 10;
        y = 2;
    }

    public void draw() {


        x += y;
        if (x >= width || x <= 0) {
            y *= -1;
        }

        line(x, 0, x, height);
        line(0, x, width, x);
        circle(width/2, height/2, x % 100 + 50); 
    }

    public static void main(String args[]) {
        PApplet.main(ArtLine.class.getName());
    }
}