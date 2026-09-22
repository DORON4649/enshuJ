package enshu5;

import processing.core.PApplet;

public class Cell1 extends PApplet {
    // セルの数 5行10列 (高さ: Height, 幅: Width の頭文字)
    int H = 5;
    int W = 10; 
    
    // H行W列の2次元配列の宣言
    int cell[][] = new int[H][W]; 

    public void settings() {
        size(300, 150); // ウィンドウサイズ
    }

    public void draw() {
        noLoop(); // draw()を1回だけ実行する(静止画になる)
        
        cell[0][0] = 1;     // 左上端のセルを1にする
        cell[H-1][0] = 1;   // 左下端のセルを1にする
        cell[0][W-1] = 1;   // 右上端のセルを1にする
        cell[H-1][W-1] = 1; // 右下端のセルを1にする

        for (int y = 0; y < H; y++) {
                cell[y][3] = 1;
            }

        for (int x = 0; x<W; x++) {
                cell[2][x] = 1;
            }
        background(192);
        drawCell();
        drawCount();
    }

    /** 配列cell[][]を描く マスの大きさは幅も高さも8 */
    void drawCell() {
        noStroke(); // 輪郭線を描かない
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (cell[y][x] == 0) {
                    fill(0); // セルが0なら黒
                } else {
                    fill(255); // そうでなければ白
                }
                rect(x * 10, y * 10, 8, 8); // cell[y][x]のセルを描く
            }
        }
    }

    /** 白マスの個数を数え表示する(ようにする) */
    void drawCount() {
        int count = 0;
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (cell[y][x] == 1) {
                    count++; // セルが1ならcountを1増やす
                }
            }
        }
        fill(0); // 黒い文字で
        text(count, 100, 50); // 座標(100,50)にcountを表示する
    }

    public static void main(String args[]) {
        PApplet.main("enshu5.Cell1");
    }
}