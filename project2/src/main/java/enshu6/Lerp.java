package enshu6;
import processing.core.PApplet;

public class Lerp extends PApplet {
    public void settings() {
        size(500, 200); // ウィンドウサイズ [cite: 171]
    }

    public void draw() {
        noLoop(); // draw()を1回だけ実行する [cite: 174]
        
        int begin = color(255, 0, 0); // 開始色 [cite: 175, 176]
        int end = color(0, 255, 255); // 終了色 [cite: 177]

        // 1段目: RGBモードで赤→水色 [cite: 217]
        for (int i = 0; i < width; i++) {
            int c = lerpColor(begin, end, (float)i / width); // 色の補間 [cite: 180]
            stroke(c);
            line(i, 0, i, 45);
        }

        // 2段目: HSBモードで赤→水色 [cite: 184, 219]
        colorMode(HSB);
        for (int i = 0; i < width; i++) {
            int c = lerpColor(begin, end, (float)i / width);
            stroke(c);
            line(i, 50, i, 95);
        }

        // 3段目: RGBモードで白→緑 [cite: 190, 221]
        colorMode(RGB);
        int start = color(255, 255, 255);
        int stop = color(0, 255, 0);
        for (int i = 0; i < width; i++) {
            int c = lerpColor(start, stop, (float)i / width);
            stroke(c);
            line(i, 100, i, 145);
        }

        // 4段目: HSBモードで白→緑 [cite: 200, 222]
        colorMode(HSB);
        for (int i = 0; i < width; i++) {
            int c = lerpColor(start, stop, (float)i / width);
            stroke(c);
            line(i, 150, i, 195);
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu6.Lerp");
    }
}