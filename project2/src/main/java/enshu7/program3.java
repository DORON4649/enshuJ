package enshu7;

import processing.core.PApplet;

public class program3 extends PApplet {

    int stageWidth = 500;
    int stageHeight = 375;

    // 各エリア（左上、右上、右下、左下）の左上座標を定義
    // 画面をきれいに4等分した位置です
    float xLeft = 0;
    float xRight = 250;
    float yTop = 0;
    float yBottom = 187.5f;

    // インベーダー1つあたりのサイズ
    float invaderW = 250;
    float invaderH = 187.5f;

    // アニメーションの制御用変数
    int step = 0; // 0〜3のステップを繰り返す
    int lastTime = 0; // 前回のステップ切り替え時刻
    int interval = 500; // 次のマスに動く間隔（ミリ秒）。500ms = 0.5秒ごとにガタッと動く

    // 各インベーダーの現在の座標
    float pinkX, pinkY;
    float cyanX, cyanY;
    float yellowX, yellowY;

    public static void main(String[] args) {
        PApplet.main("enshu7.program3");
    }

    public void settings() {
        size(stageWidth, stageHeight);
    }

    public void setup() {
        frameRate(60);
        noStroke();
        lastTime = millis(); // タイマーリセット
    }

    public void draw() {
        background(0); // 背景を黒にクリア

        // 一定時間（interval）ごとにステップを進める（コマ送り処理）
        if (millis() - lastTime > interval) {
            step = (step + 1) % 4; // 0, 1, 2, 3 をループ
            lastTime = millis(); // タイマー再設定
        }

        // 動画のコマ送りの動きを完全に再現する条件分岐
        if (step == 0) {
            // 初期状態（動画の冒頭）
            // 左上：ピンク、右上：水色、左下：黄色（右下が空き）
            pinkX = xLeft;
            pinkY = yTop;
            cyanX = xRight;
            cyanY = yTop;
            yellowX = xLeft;
            yellowY = yBottom;
        } else if (step == 1) {
            // 水色が右下に下がる（右上が空き）
            pinkX = xLeft;
            pinkY = yTop;
            cyanX = xRight;
            cyanY = yBottom;
            yellowX = xLeft;
            yellowY = yBottom;
        } else if (step == 2) {
            // ピンクが右上にスライド（左上が空き）
            pinkX = xRight;
            pinkY = yTop;
            cyanX = xRight;
            cyanY = yBottom;
            yellowX = xLeft;
            yellowY = yBottom;
        } else if (step == 3) {
            // 黄色が左上に上がる（左下が空き）
            pinkX = xRight;
            pinkY = yTop;
            cyanX = xRight;
            cyanY = yBottom;
            yellowX = xLeft;
            yellowY = yTop;
        }

        // 計算された座標にそれぞれのインベーダーを描画
        // ピンク
        drawInvader(pinkX, pinkY, invaderW, invaderH, color(255, 0, 255));

        // 水色
        drawInvader(cyanX, cyanY, invaderW, invaderH, color(0, 255, 255));

        // 黄色
        drawInvader(yellowX, yellowY, invaderW, invaderH, color(255, 255, 0));
    }

    /**
     * 単一のインベーダーを描画するメソッド（12x8マス）
     */
    void drawInvader(float ox, float oy, float w, float h, int c) {
        fill(c);

        float cols = 12;
        float rows = 8;
        float dw = w / cols;
        float dh = h / rows;

        int[][] sprite = {
                { 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
                { 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
                { 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0 }
        };

        for (int r = 0; r < rows; r++) {
            for (int col = 0; col < cols; col++) {
                if (sprite[r][col] == 1) {
                    // ドット間の隙間を無くすために少しだけ大きく(0.5f)描画
                    rect(ox + col * dw, oy + r * dh, dw + 0.5f, dh + 0.5f);
                }
            }
        }
    }
}