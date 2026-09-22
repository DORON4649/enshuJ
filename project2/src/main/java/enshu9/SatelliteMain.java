/*
地球に振る隕石
【アートの工夫】
隕石が重力にひかれるようにする。
*/


package enshu9;

import processing.core.PApplet;
import java.util.ArrayList;

public class SatelliteMain extends PApplet {

    Satellite planet = new Satellite(100, 0, 0, 1.3f); 
    
    ArrayList<Satellite> meteors = new ArrayList<>();

    public void settings() {
        size(400, 400);
    }

    public void setup() {
        background(0);
    }

    public void draw() {
        fill(0, 30);
        noStroke();
        rect(0, 0, width, height);

        translate(width / 2, height / 2);
        scale(1, -1);

        fill(50, 100, 255);
        circle(0, 0, 30);

        planet.update();
        fill(200, 200, 200);
        drawSatellite(planet);

        if (random(1) < 0.05f) {
            float startX = random(-width / 2, width / 2);
            float startY = height / 2;
            float startVx = random(-0.5f, 0.5f);
            float startVy = random(-1.5f, -0.5f);
            
            meteors.add(new Satellite(startX, startY, startVx, startVy));
        }

        fill(255, 100, 50);
        
        for (int i = meteors.size() - 1; i >= 0; i--) {
            Satellite m = meteors.get(i);
            m.update();
            circle(m.x, m.y, 5);

            float distSq = m.x * m.x + m.y * m.y;
            if (distSq < 200 || distSq > 400000) { 
                meteors.remove(i);
            }
        }
    }

    void drawSatellite(Satellite s) {
        circle(s.x, s.y, 10);

        float v = (float) Math.sqrt(s.vx * s.vx + s.vy * s.vy);
        float sin = s.vy / v;
        float cos = s.vx / v;
        float xx = 10 * cos;
        float yy = 10 * sin;
        
        stroke(255);
        line(s.x, s.y, s.x + xx, s.y + yy);
        noStroke();
    }

    public static void main(String args[]) {
        PApplet.main("enshu9.SatelliteMain");
    }
}