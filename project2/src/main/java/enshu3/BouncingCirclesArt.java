package enshu3;

import processing.core.PApplet;

public class BouncingCirclesArt extends PApplet {

    // 5個の円の情報を配列で用意する（簡単のためにint型）
    int num = 5;
    float[] x = new float[num];  // 横位置
    float[] y = new float[num];  // 縦位置
    float[] vx = new float[num]; // 横の速さ
    float[] vy = new float[num]; // 縦の速さ

    public void settings() {
        size(500, 400);
    }

    public void setup() {
        // 円の最初の場所と速さをバラバラに決める
        for (int i = 0; i < num; i++) {
            x[i] = width / 2;
            y[i] = height / 2;
            vx[i] = random(-5, 5); // -5から5の間のランダムな速さ
            vy[i] = random(-5, 5);
        }
        noStroke(); // 枠線なし
    }

    public void draw() {
        // 1. 半透明の背景で、うっすらと残像を残す（アートっぽく！）
        fill(0, 15); // 黒で透明度15
        rect(0, 0, width, height);

        // 2. 5個の円を順番に動かして描く
        for (int i = 0; i < num; i++) {
            
            // 位置を更新
            x[i] += vx[i];
            y[i] += vy[i];

            // 壁での跳ね返り（以前のコードと同じ仕組み）
            if (x[i] < 0 || x[i] > width)  vx[i] *= -1;
            if (y[i] < 0 || y[i] > height) vy[i] *= -1;

            // --- 3. if 文によるアート要素（高さによって色を変える） ---
            // 画面の上半分なら「黄色」、下半分なら「水色」
            if (y[i] < height / 2) {
                fill(255, 255, 0); // 黄
            } else {
                fill(0, 255, 255); // 水色
            }

            // 円を描画（速さに合わせて少し形を歪ませる応用技）
            circle(x[i], y[i], 20 + abs(vx[i]) * 2);
        }
    }

    public static void main(String[] args) {
        PApplet.main(BouncingCirclesArt.class.getName());
    }
}