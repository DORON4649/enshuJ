package enshu6;
import processing.core.PApplet;

public class Picture extends PApplet {
    /** 利用する色の配列 (カラーパレット) */
    int colors[] = new int[5];

    public void settings() {
        size(500, 500); // ウィンドウサイズ
    }

    public void setup() {
        // colors []の初期化
        int begin = color(0, 0, 255); // 青
        int end = color(255, 255, 255); // 白
        int lerpMax = colors.length - 1; // 5ではなく4にする

        for (int i = 0; i < colors.length; i++) {
            // lerpColor()を使用してグラデーションを作成
            colors[i] = lerpColor(begin, end, (float)i / lerpMax);
        }
    }

    public void draw() {
        noLoop(); // draw()を1回だけ実行する
        double x0 = -2.5, y0 = 2.5, w = 5, h = 5;
        for (int py = 0; py < height; py++) {
            for (int px = 0; px < width; px++) {
                int c = picture(px, py, x0, y0, w, h); // 座標(px,py)の色を決める
                set(px, py, c); // 座標(px,py)に色で点を描く
            }
        }
    }

    /** 座標(px,py)にセットすべき色を決めて返す */
    int picture(int px, int py, double x0, double y0, double w, double h) {
        double a = x0 + w * px / width;
        double b = y0 - h * py / height;
        double abs = Math.sqrt(a * a + b * b); // 原点からの距離 (複素数a+biの絶対値)
        int d = (int)(abs * 2.5); // 2.5倍してから切捨てる

        if (d < colors.length) {
            return colors[d];
        } else {
            return color(0, 0, 0); // 黒
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu6.Picture");
    }
}