package enshu3;
import processing.core.PApplet;
public class Circle6 extends PApplet {
    public void settings() {
        size(400, 400);
    }
    public void setup() {}

    public void draw() {
        background(192);
        translate(width/2, height/2);
        line(0, 0, 100, 0);
        line(0, 0, 0, 150);
        float theta = PI / 6;
        float x = cos(theta) * 50;
        float y = sin(theta) * 50;
        circle(x, y, 10);
        line(0, 0, x, y);
    }
    public static void main(String args[]) {
        PApplet.main(Circle6.class.getName());
    }
}
