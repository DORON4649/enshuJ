package enshu10;

import processing.core.PApplet;

public class Line2 extends PApplet {
    public void settings() {
        size(300, 150);
    }

    public void draw() {
        noLoop();
        background(0);

        neonLine(50, 50, 2500, 50);
        neonLine(100, 60, 100, 120);
        }

        void neonLine(int x0, int y0, int x1, int y1) {
            colorMode(HSB);
            stroke(0, 255, 128);
            strokeWeight(14);
            line(x0, y0, x1, y1);

            stroke(0, 255, 255);
            strokeWeight(8);
            line(x0, y0, x1, y1);

            stroke(0, 10, 255);
            strokeWeight(5);
            line(x0, y0, x1, y1);
        }

    public static void main(String[] args) {
        PApplet.main("enshu10.Line2");
    }

}
