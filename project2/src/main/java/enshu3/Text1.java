package enshu3;

import processing.core.PApplet;

public class Text1 extends PApplet {
    public void settings() {
        size(400, 400);
    }

    public void setup() {
    }

    public void draw() {
        background(192);
        translate(0, 30);
        fill(0);
        textSize(20);
        for (int x = 0; x < 10; x++) {
            text(x, x * 40, 0);
        }
        for (int y = 0; y < 10; y++) {
            text(y, 0, y * 40);
        }
    }

public static void main(String[] args) {
    PApplet.main(Text1.class.getName());
}
}