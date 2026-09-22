package enshu3;
import processing.core.PApplet;
public class Circle5 extends PApplet{

    public void settings() {
        size(400,400);
    }

    public void setup() {
    }

    public void draw() {
        background(192);
        fill(255);

        for (int i = 30; i >= 0; i--) {
            float x = i * 15;
            float y = i * 18;
            float d = i * 25;
            circle(x,y,d);
        }
    }
    public static void main(String[] args) {
        PApplet.main(Circle5.class.getName());
    }
}
