package enshu4;
import processing.core.PApplet;

public class Cell3 extends PApplet {
    int N = 50; 
    int cell[] = new int[N];
    int rule[] = new int[8];
    int cellSize = 10;

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        frameRate(15);
        noStroke();
        background(30);

        int ruleNumber = 90; 
        for (int i = 0; i < 8; i++) {
            rule[i] = (ruleNumber >> i) & 1;
        }
    }

    public void draw() {
        fill(30, 30, 30, 20); 
        rect(0, 0, width, height);

        int mouseIndex = (int)constrain(mouseX / cellSize, 1, N - 2);
        //これでカーソルに追従するらしい...()
        cell[mouseIndex] = 1;

        int next[] = new int[N];
        for (int i = 1; i < N - 1; i++) {
            int d = cell[i - 1] * 4 + cell[i] * 2 + cell[i + 1];
            next[i] = rule[d];
        }

        for (int i = 0; i < N; i++) {
            cell[i] = next[i];
            if (cell[i] == 1) {
                fill(120, 180, 200);
                rect(i * cellSize, mouseY, cellSize - 2, cellSize - 2);
            }
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu4.Cell3");
    }
}