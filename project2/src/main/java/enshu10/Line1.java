package enshu10;
import processing.core.PApplet;
public class Line1 extends PApplet {
    public void settings() {
        size(300, 150);
    }

    public void draw() {
        noLoop();
        translate(10,10);

        for (int i=0; i<10; i++) {
            if (i==0) {
                noStroke();
            } else{
                stroke(0);
                strokeWeight(i);
            }
            circle(i*30, 20, 20);
            rect(i*30, 60, 10, 20);
            line(i*30, 100, i*30, 120);
        }
    }

    public static void main(String[] args) {
        PApplet.main("enshu10.Line1");
    }
}
