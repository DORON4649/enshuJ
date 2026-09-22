package enshu10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import processing.core.PApplet;

public class Maze1 extends PApplet {
    final int SIZE_H = 17; // 水平方向(奇数)
    final int SIZE_V = 13; // 垂直方向(奇数)
    final int W = 40;      // マスの大きさ

    int[][] grid = new int[SIZE_H][SIZE_V];
    Random rand = new Random(5); 
    
    Ghost ghost;
    List<Ghost> queue = new ArrayList<>();

    public void settings() {
        size(SIZE_H * W, SIZE_V * W);
    }

    public void setup() {
        for (int x = 0; x < SIZE_H; x++) {
            grid[x][0] = grid[x][SIZE_V - 1] = 1; 
        }
        for (int y = 0; y < SIZE_V; y++) {
            grid[0][y] = grid[SIZE_H - 1][y] = 1; 
        }
        
        extendWall();

        for (int y = 0; y < SIZE_V; y++) {
            for (int x = 0; x < SIZE_H; x++) {
                if (grid[x][y] == 0) {
                    grid[x][y] = 3;
                }
            }
        }

        search();
        Collections.shuffle(queue);

        if (!queue.isEmpty()) {
            ghost = queue.remove(0);
            ghost.color = color(255, 50, 50);
            grid[ghost.x][ghost.y] = 0;      
        }
        
        frameRate(8); //速度
    }

    int countCandies(int x, int y) {
        int count = 0;
        if (grid[x][y - 1] == 3) count++;
        if (grid[x][y + 1] == 3) count++;
        if (grid[x - 1][y] == 3) count++;
        if (grid[x + 1][y] == 3) count++;
        return count;
    }

    void search() {
        for (int x = 1; x < SIZE_H; x += 2) {
            for (int y = 1; y < SIZE_V; y += 2) {
                if (x == 1 && y == 1) continue; 
                if (x == SIZE_H - 2 && y == SIZE_V - 2) continue; 

                int d = 0;
                if (grid[x][y - 1] == 1) d++;
                if (grid[x][y + 1] == 1) d++;
                if (grid[x - 1][y] == 1) d++;
                if (grid[x + 1][y] == 1) d++;

                if (d == 3) {
                    Ghost g = new Ghost(x, y, color(100, 100, 255)); // 待機は青
                    queue.add(g);
                }
            }
        }
    }

    void extendWall() {
        for (int y = 2; y < SIZE_V - 2; y += 2) {
            for (int x = 2; x < SIZE_H - 2; x += 2) {
                if (grid[x][y] == 0) extendFrom(x, y);
            }
        }
    }

    void extendFrom(int x0, int y0) {
        int[] dir = new int[4];
        LOOP:
        while (true) {
            int x = x0, y = y0;
            while (true) {
                int dirs = 0;
                if (grid[x + 2][y] != 2) dir[dirs++] = 1; 
                if (grid[x - 2][y] != 2) dir[dirs++] = 2; 
                if (grid[x][y + 2] != 2) dir[dirs++] = 3; 
                if (grid[x][y - 2] != 2) dir[dirs++] = 4; 

                if (dirs == 0) { 
                    replace(0);  
                    continue LOOP; 
                }

                int r = rand.nextInt(dirs); 
                int d = dir[r];
                if (d == 1) {
                    grid[x][y] = grid[x + 1][y] = 2;
                    x += 2;
                } else if (d == 2) {
                    grid[x][y] = grid[x - 1][y] = 2;
                    x -= 2;
                } else if (d == 3) {
                    grid[x][y] = grid[x][y + 1] = 2;
                    y += 2;
                } else {
                    grid[x][y] = grid[x][y - 1] = 2;
                    y -= 2;
                }

                if (grid[x][y] == 1) break LOOP;
            }
        }
        replace(1); 
    }

    void replace(int r) {
        for (int y = 0; y < SIZE_V; y++) {
            for (int x = 0; x < SIZE_H; x++) {
                if (grid[x][y] == 2) grid[x][y] = r;
            }
        }
    }

    public void draw() {
        noStroke();
        background(15);

        for (int y = 0; y < SIZE_V; y++) {
            for (int x = 0; x < SIZE_H; x++) {
                if (grid[x][y] == 3) {
                    mark(x, y, 4, color(255, 180, 50)); 
                }
            }
        }

        fill(30, 80, 220);
        int m = 8;
        int r = 10;

        for (int y = 0; y < SIZE_V; y += 2) {
            for (int x = 1; x < SIZE_H; x += 2) {
                if (grid[x][y] == 1) {
                    rect(x * W - m, y * W + m, W + m * 2, W - m * 2, r);
                }
            }
        }
        
        for (int y = 1; y < SIZE_V; y += 2) {
            for (int x = 0; x < SIZE_H; x += 2) {
                if (grid[x][y] == 1) {
                    rect(x * W + m, y * W - m, W - m * 2, W + m * 2, r);
                }
            }
        }
        
        for (int y = 0; y < SIZE_V; y += 2) {
            for (int x = 0; x < SIZE_H; x += 2) {
                if (grid[x][y] == 1) {
                    rect(x * W + m, y * W + m, W - m * 2, W - m * 2, r);
                }
            }
        }

        for (Ghost g : queue) {
            mark(g.x, g.y, 10, g.color);
        }

        if (ghost == null) {
            noLoop();
            return;
        }

        mark(ghost.x, ghost.y, 12, ghost.color);

        int candies = countCandies(ghost.x, ghost.y);
        boolean isStartOrGoal = (ghost.x == 1 && ghost.y == 1) || (ghost.x == SIZE_H - 2 && ghost.y == SIZE_V - 2);

        if (candies >= 2 || isStartOrGoal) {
            if (queue.isEmpty()) {
                ghost = null; 
            } else {
                ghost = queue.remove(0); 
                ghost.color = color(255, 50, 50); 
                grid[ghost.x][ghost.y] = 0;
            }
            return; 
        }

        if (!ghost.isRookie()) {
            if (grid[ghost.x][ghost.y - 1] == 3) ghost.y -= 1;
            else if (grid[ghost.x][ghost.y + 1] == 3) ghost.y += 1;
            else if (grid[ghost.x - 1][ghost.y] == 3) ghost.x -= 1;
            else if (grid[ghost.x + 1][ghost.y] == 3) ghost.x += 1;
        }
        ghost.update();

        candies = countCandies(ghost.x, ghost.y);
        isStartOrGoal = (ghost.x == 1 && ghost.y == 1) || (ghost.x == SIZE_H - 2 && ghost.y == SIZE_V - 2);
        
        if (candies < 2 && !isStartOrGoal) {
            grid[ghost.x][ghost.y] = 0;
        }
    }

    void mark(int x, int y, int r, int c) {
        fill(c);
        noStroke();
        circle(x * W + W / 2.0f, y * W + W / 2.0f, r * 2); 
    }

    public static void main(String[] args) {
        PApplet.main("enshu10.Maze1");
    }
}