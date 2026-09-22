package enshu7;

import processing.core.PApplet;

public class Program2 extends PApplet {

    int stageWidth  = 250;
    int stageHeight = 200;
    int cellSize    = 5;

    int[][] cell = {
        { 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0 },
        { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0 },
        { 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
        { 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
        { 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0 }
    };

    int spriteRows = 8;
    int spriteCols = 12;

    float spriteW;
    float spriteH;
    float groupW;
    float groupH;

    float x = 0;
    float y = 0;

    float speed = 5;

    int direction = 0;

    public void settings() {
        size(stageWidth, stageHeight);
    }

    public void setup() {
        frameRate(5);
        noStroke();
        spriteW = spriteCols * cellSize;
        spriteH = spriteRows * cellSize;
        groupW  = spriteW * 2;
        groupH  = spriteH * 2;
    }

    public void draw() {
        background(0);

        if (direction == 0) {
            x += speed;
            if (x >= stageWidth - groupW) {
                x = stageWidth - groupW;
                direction = 1;
            }
        } else if (direction == 1) {
            y += speed;
            if (y >= stageHeight - groupH) {
                y = stageHeight - groupH;
                direction = 2;
            }
        } else if (direction == 2) {
            x -= speed;
            if (x <= 0) {
                x = 0;
                direction = 3;
            }
        } else if (direction == 3) {
            y -= speed;
            if (y <= 0) {
                y = 0;
                direction = 0;
            }
        }

        pattern(x,         y,         color(255,   0, 255));
        pattern(x + spriteW, y,         color(  0, 255, 255));
        pattern(x,         y + spriteH, color(255, 255,   0));
        pattern(x + spriteW, y + spriteH, color(255, 255, 255));
    }

    void pattern(float ox, float oy, int c) {
        fill(c);
        for (int r = 0; r < spriteRows; r++) {
            for (int col = 0; col < spriteCols; col++) {
                if (cell[r][col] == 1) {
                    rect(ox + col * cellSize, oy + r * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu7.Program2");
    }
}
