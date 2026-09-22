package enshu4;
import processing.core.PApplet;

public class Cell2 extends PApplet {
    int N = 40;
    int cell[] = new int[N];
    int rule[] = { 0, 0, 1, 1, 1, 1, 1, 1 };

    public void settings() {
        size(400, 200);
    }

    public void draw() {
        noLoop(); 
        noStroke();
        background(192);

        cell[20] = 1; 
        drawCell(0); 

        for (int gen = 1; gen < 10; gen++) {
            int next[] = new int[N]; 

            for (int i = 1; i < N - 1; i++) {
                int d = cell[i-1]*4 + cell[i]*2 + cell[i+1];
                next[i] = rule[d];
            }

            for (int i = 0; i < N; i++) {
                cell[i] = next[i];
            }
            drawCell(gen);
        }
    }

    void drawCell(int gen) {
        int y = gen * 10;
        fill(0);
        rect(0, y, 8, 8);
        rect((N - 1) * 10, y, 8, 8);

        for (int i = 1; i < N - 1; i++) {
            if (cell[i] == 0) {
                fill(0);
            } else {
                fill(255);
            }
            rect(i * 10, y, 8, 8);
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu4.Cell2");
    }
}