package enshu6;

import processing.core.PApplet;

public class RGB3 extends PApplet{
    public void settings() {
        size(256, 256);
    }

    public void draw() {
        noLoop();
        for (int i=0; i<256; i++) {
            int level = (i / 16) * 16;
            stroke(level, 0, 0);
            line(i, 0, i, 255);
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu6.RGB3");
    }
}