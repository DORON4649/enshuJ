package enshu10;

import processing.core.PApplet;

public class Line3 extends PApplet {

    public void settings() {
        size(200, 150);
    }

    public void draw() {
        noLoop();
        
        //垂直線
        for(int x=4; x<100; x+=10) line(x, 0, x, 100);
        for(int x=105; x<200; x+=10) line(x, 0, x, 100);

        //水平線
        for(int y=4; y<100; y+=10) line(0, y, 100, y);
        for(int y=5; y<100; y+=10) line(0, y, 200, y);
    }

    public static void main(String[] args) {
        PApplet.main("enshu10.Line3");
    }
}
