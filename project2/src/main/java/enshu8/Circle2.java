package enshu8;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class Circle2 extends PApplet {
    List<PVector> list = new ArrayList<>();

    public void settings() {
        size(400, 400);
    }

    public void setup() {
    }

    float randomY() {
        float y = random(0, height-20);
        return y + 10;
    }

    public void draw() {
        background(192);
        text(list.size(), 10, 20);

         if (frameCount % 60 == 0) {
            PVector circle = new PVector(0, randomY());
            list.add(circle);
        }

        for (PVector circle : list) {
            circle(circle.x, circle.y, 20);
            circle.x += circle.y * 0.02f;

            if (circle.x > width) {
                list.remove(circle);
                circle.x = 0;
                circle.y = randomY();
            }

           /*  if (circle.x > width) {
                circle.x = 0;
                circle.y = randomY();
            }*/
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu8.Circle2");
    }
}
