package enshu2;

import processing.core.PApplet;

public class Line extends PApplet {

    public void settings() {
        size(200, 150);
    }

    public void setup() {
    }

    public void draw0() {
        int x = 100;
        int y = 50;
        line(0, 0, x, y);
    }

    public void draw() {
        line(99, 0, 0, 74);
        line(0, 74, 99, 149);
        line(99, 149, 199, 74);
        line(199, 74, 99, 0);
    }

    public static void main(String argss[]) {
        PApplet.main(Line.class.getName());
    }
}
