package enshu11;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList;
import java.util.List;
import enshu11.Ghost.Pos;
import processing.core.PApplet;

public class Maze2 extends PApplet {
    final int SIZE_H = 17;
    final int SIZE_V = 13;
    final int W = 14;
    

    final int GX = SIZE_H - 2;
    final int GY = SIZE_V - 2;

    int grid[][] = new MazeMaker(SIZE_H, SIZE_V, 7).make();
    Ghost ghost;
    

    List<Pos> deadEnds = new ArrayList<>();
    int currentDeadEndIndex = 0;

    public void settings() {
        size(SIZE_H * W, SIZE_V * W);
    }

    public void setup() {
        findDeadEnds();
        
        if (!deadEnds.isEmpty()) {
            Pos start = deadEnds.get(currentDeadEndIndex);
            ghost = ghostFrom(start.x(), start.y());
        }
        
        frameRate(10);
    }

    public void draw() {
        noStroke();
        background(91);
        
        if (ghost != null) {
            if (!ghost.route.isEmpty()) {
                Pos nextPos = ghost.route.pollFirst();
                ghost.moveTo(nextPos.x(), nextPos.y());
                grid[ghost.x][ghost.y] = 3;
            } else {
                currentDeadEndIndex++;
                if (currentDeadEndIndex < deadEnds.size()) {
                    Pos start = deadEnds.get(currentDeadEndIndex);
                    ghost = ghostFrom(start.x(), start.y());
                } else {
                    ghost = null;
                }
            }
        }
        
        drawMaze();
        
        if (ghost != null) {
            mark(ghost.x, ghost.y, 6, ghost.color);
        }
    }

    void findDeadEnds() {
        for (int y = 0; y < SIZE_V; y++) {
            for (int x = 0; x < SIZE_H; x++) {
                if (grid[x][y] == 0) {
                    if (x == GX && y == GY) continue;
                    
                    int d = countDests(x, y);
                    if (d == 1) {
                        deadEnds.add(new Pos(x, y));
                    }
                }
            }
        }
    }

    void clean() {
        for (int y = 0; y < SIZE_V; y++) {
            for (int x = 0; x < SIZE_H; x++) {
                if (grid[x][y] == 3) {
                    grid[x][y] = 0;
                }
            }
        }
    }

    Ghost ghostFrom(int startX, int startY) {
        Deque<Pos> stack = new ArrayDeque<>();
        Ghost g = new Ghost(startX, startY, color(255, 0, 0));
        
        stack.push(new Pos(startX, startY));
        stack.push(new Pos(startX, startY));
        
        boolean doPop = false;
        clean();
        grid[startX][startY] = 3;

        while (!(g.x == GX && g.y == GY)) {
            if (doPop) {
                if (!stack.isEmpty()) {
                    Pos pos = stack.pop();
                    g.goBackTo(pos);
                    
                    while (!g.route.isEmpty()) {
                        Pos p = g.route.pollLast(); 
                        if (p.x() == pos.x() && p.y() == pos.y()) {
                            g.route.addLast(p); 
                            break;
                        }
                    }
                    doPop = false;
                }
            } else {
                int x = g.x;
                int y = g.y;
                
                if (grid[x][y - 1] == 0) y--;
                else if (grid[x][y + 1] == 0) y++;
                else if (grid[x - 1][y] == 0) x--;
                else if (grid[x + 1][y] == 0) x++;
                
                g.moveTo(x, y);
                grid[x][y] = 3;
                
                g.route.add(new Pos(x, y));
                
                int d = countDests(x, y);
                if (d == 1) {
                    if (!stack.isEmpty()) {
                        doPop = true;
                    }
                } else if (d == 3) {
                    stack.push(new Pos(x, y));
                } else if (d == 4) {
                    stack.push(new Pos(x, y));
                    stack.push(new Pos(x, y));
                }
            }
        }

        g.x = startX;
        g.y = startY;
        clean();
        grid[startX][startY] = 3; 
        
        return g;
    }

    int countDests(int x, int y) {
        int d = 0;
        if (grid[x][y - 1] != 1) d++;
        if (grid[x][y + 1] != 1) d++;
        if (grid[x - 1][y] != 1) d++;
        if (grid[x + 1][y] != 1) d++;
        return d;
    }

    void drawMaze() {
        for (int y = 0; y < SIZE_V; y++) {
            for (int x = 0; x < SIZE_H; x++) {
                int m = grid[x][y];
                if (m == 1) {
                    noStroke();
                    fill(255);
                    rect(x * W, y * W, W, W);
                } else if (m == 3) {
                    noStroke();
                    fill(255, 255, 0);
                    mark(x, y, 2, color(255, 255, 0));
                }
            }
        }
    }

    void mark(int x, int y, int r, int c) {
        noStroke();
        fill(c);
        circle(x * W + W / 2, y * W + W / 2, r * 2);
    }

    public static void main(String args[]) {
        PApplet.main("enshu11.Maze2");
    }
}