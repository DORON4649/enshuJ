package enshu6;
import processing.core.PApplet;

public class Mandelbrot extends PApplet {
    
    /*
     * 工夫した点・みどころ:
     * 1.漸化式の虚部計算に絶対値 (|x*y|) を加えた。
     * 2.HSBモードで色相(Hue)を繰り返し回数(count)の対数で変化させ、発散境界の細部を美しくグラデーション化した点。
     * 3.座標設定(x0, y0, w, h)を変更することで、簡単にズームができる。
     */

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        colorMode(HSB, 360, 100, 100);
    }

    public void draw() {
        noLoop();
        double x0 = -2.5, y0 = 2.5, w = 5.0, h = 5.0;
        int max = 150;
        
        for (int py = 0; py < height; py++) {
            for (int px = 0; px < width; px++) {
                int count = mcount(px, py, x0, y0, w, h, max);

                if (count == max) {
                    set(px, py, color(0, 0, 0));
                } else {
                    float hue = (count * 4) % 360;
                    set(px, py, color(hue, 80, 90));
                }
            }
        }
    }

    int mcount(int px, int py, double x0, double y0, double w, double h, int max) {
        double a = x0 + w * px / width;
        double b = y0 - h * py / height;
        int count = 0;
        double x = 0, y = 0;
        
        while (count < max && x * x + y * y < 4) {
            count++;
            double xx = x * x - y * y + a;
            double yy = 2 * Math.abs(x * y) + b;
            x = xx;
            y = yy;
        }
        return count;
    }

    public static void main(String args[]) {
        PApplet.main("enshu6.Mandelbrot");
    }
}