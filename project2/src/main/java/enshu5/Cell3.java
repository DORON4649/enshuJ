package enshu5;

import processing.core.PApplet;

public class Cell3 extends PApplet {
    int H = 48;
    int W = 48;
    int cell[][] = new int[H][W];

    int diehard[][] = {
        {0,0,0,0,0,0,1,0},
        {1,1,0,0,0,0,0,0},
        {0,1,0,0,0,1,1,1}
    };

    int acorn[][] = {
        {0,1,0,0,0,0,0},
        {0,0,0,1,0,0,0},
        {1,1,0,0,1,1,1}
    };

    public void settings() {
        size(480, 480);
    }

    public void setup() {
        frameRate(12);

        pattern(diehard, 12, 22);
        pattern(acorn, 28, 22);
    }

    public void draw() {
        background(10, 12, 18);
        
        drawCell();
        change();

        fill(255, 70);
        textSize(14);
        text("Generation: " + frameCount, 15, height - 15);
    }

    void pattern(int p[][], int x, int y) {
        int pHight = p.length;
        int pWidth = p[0].length;
        
        for (int py = 0; py < pHight; py++) {
            for (int px = 0; px < pWidth; px++) {
                int cy = y + py;
                int cx = x + px;
                if (cy >= 0 && cy < H && cx >= 0 && cx < W) {
                    cell[cy][cx] = p[py][px];
                }
            }
        }
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

    void drawCell() {
        noStroke();
        int cellSize = 10; 
        
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (cell[y][x] == 1) {
                    float d = dist(x, y, W/2, H/2);
                    float r = map(d, 0, W/2, 255, 100);
                    float g = map(d, 0, W/2, 50, 200);
                    float b = map(d, 0, W/2, 255, 255);
                    
                    fill(r, g, b);
                    rect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu5.Cell3");
    }
}