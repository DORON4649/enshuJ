package enshu12;

import processing.core.PApplet;

public class Turtle extends PApplet {

    final float deg30 = (float)(Math.PI / 6);
    final float deg45 = (float)(Math.PI / 4);
    final float deg60 = (float)(Math.PI / 3);
    final float deg72 = (float)(Math.PI * 2 / 5);
    final float deg90 = (float)(Math.PI / 2);
    final float deg120 = (float)(Math.PI * 2 / 3);
    final float deg144 = (float)(Math.PI * 4 / 5);


    void reset() {
        resetMatrix();
        translate(width/2, height/2);
    }

    void forward(float n) {
        line(0, 0, 0, -n);
        translate(0, -n);
    }

    void right(float rad) {
        rotate(rad);
    }

    void left(float rad) {
        rotate(-rad);
    }

    /**
     * カメが向いてる方向を基準に、右方向にright, 下方向にdown移動する
     * 線は書かない　カメの向きは変えない
     */

    void move(float right, float down) {
        translate(right, down);
    }

    void square(float length) {
        forward(length);
        right(deg90);
        forward(length);
        right(deg90);
        forward(length);
        right(deg90);
        forward(length);
        right(deg90);
    }

    void triangle(float length) {
        forward(length);
        right(deg120);
        forward(length);
        right(deg120);
        forward(length);
    }

    void pentagon(float length) {
        forward(length);
        right(deg72);
        forward(length);
        right(deg72);
        forward(length);
        right(deg72);
        forward(length);
        right(deg72);
        forward(length);
        right(deg72);
    }

    void star(float length) {
        forward(length);
        right(deg144);
        forward(length);
        right(deg144);
        forward(length);
        right(deg144);
        forward(length);
        right(deg144);
        forward(length);
        right(deg144);
    }
}