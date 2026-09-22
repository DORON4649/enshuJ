package enshu6;

import processing.core.PApplet;

public class RGB extends PApplet{
    public void settings() {
        size(256, 256);
    }

    public void draw() {
        noLoop();
        for (int i=0; i<256; i++) {
            int c = color(i, 0, 0);
            System.out.printf("%08x\n", c);
        stroke(c);
        line(i, 0, i, 255);
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu6.RGB");
    }
}