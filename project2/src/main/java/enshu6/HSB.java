package enshu6;
import processing.core.PApplet;
public class HSB extends PApplet{

    public void settings() {
        size(256, 200);
    }

    public void draw() {
        noLoop();
        colorMode(HSB);

        for (int i=0; i<256; i++) {
            stroke(i, 255, 255);
            line(i, 0, i, 45);
        }

        for (int i=0; i<256; i++) {
            stroke(255, i, 255);
            line(i, 50, i, 95);
        }

        for (int i=0; i<256; i++) {
            stroke(255, i, 127);
            line(i, 100, i, 145);
        }

        for (int i=0; i<256; i++) {
            stroke(255, 255, i);
            line(i, 150, i, 195);
        }

        int c = color(255, 255, 255);
        System.out.printf("%08x\n", c);
    }

    public static void main(String args[]) {
        PApplet.main("enshu6.HSB");
    }
}
