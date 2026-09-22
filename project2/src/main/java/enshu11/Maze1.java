package enshu11;

import java.util.ArrayDeque;
import java.util.Deque;
import enshu11.Ghost.Pos;
import processing.core.PApplet;

public class Maze1 extends PApplet {
    final int SIZE_H = 17; // 水平方向のマスの数(奇数)
    final int SIZE_V = 13; // 垂直方向のマスの数(奇数)
    final int W = 14;      // マスの大きさ
    
    // ゴールの座標を定義（右下の角）
    final int GX = SIZE_H - 2;
    final int GY = SIZE_V - 2;
    
    /** マスにあるもの 0:道 1:壁 2: 作成中の壁 3:お菓子(通った道) */
    int grid[][] = new MazeMaker(SIZE_H, SIZE_V, 7).make();
    Ghost ghost;
    Deque<Pos> stack = new ArrayDeque<>();
    boolean doPop; 

    public void settings() {
        size(SIZE_H * W + W, SIZE_V * W); 
    }

    public void setup() {
        ghost = new Ghost(1, 1, color(255, 0, 0));
        doPop = true;
        
        // ★修正ポイント★
        // 初回のpop用と、行き止まりから戻ってくる用の2回積む
        stack.push(new Pos(1, 1)); 
        stack.push(new Pos(1, 1)); 
        
        grid[1][1] = 3;            
        frameRate(10); // ★少し速くしておきました（遅ければ数値を上げてください）
    }

    public void draw() {
        noStroke();
        background(192);

        // ゴール到達時は完全に停止する
        if (ghost.x == GX && ghost.y == GY) {
            drawMaze();
            drawStack();
            mark(ghost.x, ghost.y, 6, ghost.color);
            return; // これ以上処理しない
        } 
        
        // バックトラック処理（分岐点に戻る）
        if (doPop) {
            if (!stack.isEmpty()) {
                Pos pos = stack.pop();
                ghost.goBackTo(pos);
                doPop = false; 
            }
        } 
        // 通常の移動および探索処理
        else {
            int x = ghost.x;
            int y = ghost.y;
            
            // 四方の移動判定（お菓子も壁もない場所へ進む）
            if (grid[x][y - 1] == 0) y--;
            else if (grid[x][y + 1] == 0) y++;
            else if (grid[x - 1][y] == 0) x--;
            else if (grid[x + 1][y] == 0) x++;
            
            ghost.moveTo(x, y);
            grid[x][y] = 3; 
            
            int d = countDests(x, y);
            
            if (d == 1) {
                // 行き止まりの場合
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
        
        // 描画処理
        drawMaze();
        drawStack();
        mark(ghost.x, ghost.y, 6, ghost.color);
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
                    fill(255);
                    rect(x * W, y * W, W, W);
                } else if (m == 3) {
                    fill(255, 255, 0);
                    mark(x, y, 2, color(255, 255, 0));
                }
            }
        }
    }

    void mark(int x, int y, int r, int c) {
        fill(c);
        circle(x * W + W / 2, y * W + W / 2, r * 2);
    }

    void drawStack() {
        int n = stack.size() - 1;
        int sx = width - W; 
        
        for (Pos pos : stack) {
            int sy = height - W * (stack.size() - n); 
            fill(color(128, 128, 255));
            rect(sx, sy, W - 1, W - 1);
            fill(255);
            text(n, sx + 2, sy + W - 2);
            n--;
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu11.Maze1");
    }
}