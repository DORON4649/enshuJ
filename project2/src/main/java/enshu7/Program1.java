package enshu7;

import processing.core.PApplet;

public class Program1 extends PApplet {

    // --- パラメータ設定 ---
    int numArms = 6; // 渦巻きの腕の数
    int dotsPerArm = 19; // 1つの腕あたりの点の数
    float a = 0.5f; // 渦巻きの開始位置
    float b = 0.9f; // 渦巻きの広がり具合
    float speed = 0.01f; // 回転スピード
    float scaleFactor = 25; // 描画サイズの拡大率（画面に合わせるため）
    float rotationAngle = 10; // 現在の回転角度

    public void settings() {
        size(400, 400);
    }

    public void setup() {
        background(0);
        noStroke();
    }

    public void draw() {
        background(0);

        translate(width / 2f, height / 2f);

        rotationAngle += speed;

        for (int k = 0; k < numArms; k++) {
            float offset = TWO_PI * k / (float) numArms;

            for (int i = 0; i < dotsPerArm; i++) {
                float t = i * 0.4f;

                float r = a + b * t;

                float theta = t + offset - rotationAngle;

                float x = r * cos(theta) * scaleFactor;
                float y = r * sin(theta) * scaleFactor;

                float ratio = (float) i / (dotsPerArm - 1);

                fill(ratio * 255, 255, ratio * 255);

                circle(x, y, 10);
            }
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu7.Program1");
    }
}
