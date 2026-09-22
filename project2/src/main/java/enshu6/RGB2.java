package enshu6;

import processing.core.PApplet;

public class RGB2 extends PApplet{
    public void settings() {
        size(256, 256);
    }

    public void draw() {
        noLoop();
        for (int i=0; i<256; i++) {
            for (int j=0; j<256; j++) {
                int greenValue = (i + j) / 2;
                stroke(0, greenValue, 0);
                point(i, j);
            }
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu6.RGB2");
    }
}