package enshu5;

import processing.core.PApplet;

public class Cell2 extends PApplet {
    int H = 8;
    int W = 10;
    int cell[][] = new int[H][W];

    public void settings() {
        size(200, 200);
    }

    public void draw() {
        noLoop();

        glider(2, 2);

        //blinkerH(2, 2);
        //blinkerV(7, 2);

        background(192);
        drawCell();
        drawCount();

        change();
        change();

        translate(0, 100);
        drawCell();
        drawCount();
    }

    void change() {
        int next[][] = new int[H][W];
        
        for (int y = 1; y < H - 1; y++) {
            for (int x = 1; x < W - 1; x++) {
                next[y][x] = nextState(x, y);
            }
        }
        
        for (int y = 1; y < H - 1; y++) {
            for (int x = 1; x < W - 1; x++) {
                cell[y][x] = next[y][x];
            }
        }
    }

    int nextState(int x, int y) {
    int count = 0;
    if (cell[y - 1][x - 1] == 1) { count++; }
    if (cell[y - 1][x]     == 1) { count++; }
    if (cell[y - 1][x + 1] == 1) { count++; }
    if (cell[y][x - 1]     == 1) { count++; }
    if (cell[y][x + 1]     == 1) { count++; }
    if (cell[y + 1][x - 1] == 1) { count++; }
    if (cell[y + 1][x]     == 1) { count++; }
    if (cell[y + 1][x + 1] == 1) { count++; }

    int next = 0;

    if (cell[y][x] == 0) {
        if (count == 3) {
            next = 1;
        }
    } else {
        if (count == 2 || count == 3) {
            next = 1;
        }
    }

    return next;
}   

    void blinkerH(int x, int y) {
        cell[y][x - 1] = cell[y][x] = cell[y][x + 1] = 1;
    }

    void blinkerV(int x, int y) {
        cell[y - 1][x] = cell[y][x] = cell[y + 1][x] = 1;
    }

    void glider(int x, int y) {
        cell[y - 1][x]     = 1;
        cell[y][x + 1]     = 1;
        cell[y + 1][x - 1] = 1;
        cell[y + 1][x]     = 1;
        cell[y + 1][x + 1] = 1;
    }

    void drawCell() {
        noStroke();
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (cell[y][x] == 0) {
                    fill(0);
                } else {
                    fill(255);
                }
                rect(x * 10, y * 10, 8, 8);
            }
        }
    }

    void drawCount() {
        int count = 0;
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (cell[y][x] == 1) {
                    count++;
                }
            }
        }
        fill(0);
        text(count, 100, 50);
    }

    public static void main(String args[]) {
        PApplet.main("enshu5.Cell2");
    }
}