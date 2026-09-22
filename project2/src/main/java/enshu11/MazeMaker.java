package enshu11;

import java.util.Random;

public class MazeMaker {
    final int SIZE_H; // 水平方向のマスの数 (奇数)
    final int SIZE_V; // 垂直方向のマスの数 (奇数)
    
    /** マスにあるもの 0:道 1:壁 2: 作成中の壁 */
    int grid[][];
    Random rand; // ランダムシード=7

    public MazeMaker(int size_h, int size_v, int seed) {
        SIZE_H = size_h;
        SIZE_V = size_v;
        rand = new Random(seed);
    }

    /** 迷路を作成し2次元配列で返す */
    public int[][] make() {
        grid = new int[SIZE_H][SIZE_V];
        for (int x = 0; x < SIZE_H; x++) {
            grid[x][0] = grid[x][SIZE_V - 1] = 1; // 上端と下端のマスを壁にする
        }
        for (int y = 0; y < SIZE_V; y++) {
            grid[0][y] = grid[SIZE_H - 1][y] = 1; // 左端と右端のマスを壁にする
        }
        
        for (int y = 2; y < SIZE_V - 2; y += 2) {
            for (int x = 2; x < SIZE_H - 2; x += 2) {
                if (grid[x][y] == 0) extendFrom(x, y); // 柱を選び壁を伸ばす
            }
        }
        return grid;
    }

    /** (x, y)にある柱から壁を伸ばす */
    void extendFrom(int x0, int y0) {
        int dir[] = new int[4];
        LOOP:
        while (true) {
            int x = x0, y = y0;
            while (true) {
                int dirs = 0;
                if (grid[x + 2][y] != 2) dir[dirs++] = 1; // 右
                if (grid[x - 2][y] != 2) dir[dirs++] = 2; // 左
                if (grid[x][y + 2] != 2) dir[dirs++] = 3; // 下
                if (grid[x][y - 2] != 2) dir[dirs++] = 4; // 上
                
                if (dirs == 0) { // 伸ばせる方向がない
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
        replace(1); // 作成中の壁を確定する
    }

    /** 作成中の壁の値を変更する */
    void replace(int r) {
        for (int y = 0; y < SIZE_V; y++) {
            for (int x = 0; x < SIZE_H; x++) {
                if (grid[x][y] == 2) grid[x][y] = r;
            }
        }
    }
}