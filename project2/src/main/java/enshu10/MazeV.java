package enshu10;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import processing.core.PApplet;

public class MazeV extends PApplet {
    final int SIZE_H = 40;
    final int SIZE_V = 25;
    final int W = 14;

    boolean[][] pillar = new boolean[SIZE_H][SIZE_V]; // 外周の柱も含む
    boolean[][] wallH = new boolean[SIZE_H][SIZE_V];  // 柱の右の壁 右端は常にfalse
    boolean[][] wallV = new boolean[SIZE_H][SIZE_V];  // 柱の下の壁 下端は常にfalse

    public void settings() {
        size(SIZE_H * W, SIZE_V * W);
    }

    public void setup() {
        for (int x = 0; x < SIZE_H; x++) {
            pillar[x][0] = pillar[x][SIZE_V - 1] = true;
            wallH[x][0] = wallH[x][SIZE_V - 1] = (x < SIZE_H - 1);
        }
        for (int y = 0; y < SIZE_V; y++) {
            pillar[0][y] = pillar[SIZE_H - 1][y] = true;
            wallV[0][y] = wallV[SIZE_H - 1][y] = (y < SIZE_V - 1);
        }
        state = new State(); // INIT
        frameRate(10);
    }

    enum Mode { INIT, WAIT_FORWARD, FORWARD, WAIT_INIT, WAIT_BACKWARD, BACKWARD, END };

    class State {
        Mode mode = Mode.INIT;
        int x0, y0; // 伸ばす壁の始点
        int x, y;   // 伸ばす壁の先端
        int count = 0;
        List<Integer> tmp = new ArrayList<>(); // 根元を含む

        void init(int x, int y) {
            this.x0 = this.x = x;
            this.y0 = this.y = y;
            tmp.clear();
            tmp.add(y * SIZE_H + x);
            count = 3;
            mode = Mode.WAIT_FORWARD;
        }

        void waitForward() { 
            if (--count == 0) mode = Mode.FORWARD; 
        }
        
        void waitInit() { 
            if (--count == 0) mode = Mode.INIT; 
        }
        
        void waitBackward() { 
            if (--count == 0) mode = Mode.BACKWARD; 
        }

        boolean contains(int p) { 
            return tmp.contains(p); 
        }
        
        boolean contains(int x, int y) { 
            return tmp.contains(y * SIZE_H + x); 
        }
        
        boolean push(int p) { 
            tmp.add(p); 
            return tmp.size() == 1; 
        }
        
        int pop() { 
            return tmp.remove(tmp.size() - 1); 
        }
    }

    State state;

    public void draw() {
        background(192);
        translate(W / 2, W / 2);

        for (int x = 0; x < SIZE_H; x++) {
            for (int y = 0; y < SIZE_V; y++) {
                if (wallH[x][y]) {
                    if (state.contains(x, y) && state.contains(x + 1, y)) stroke(255, 0, 0); 
                    else stroke(0);
                    line(x * W, y * W, x * W + W, y * W);
                }
                if (wallV[x][y]) {
                    if (state.contains(x, y) && state.contains(x, y + 1)) stroke(255, 0, 0); 
                    else stroke(0);
                    line(x * W, y * W, x * W, y * W + W);
                }
            }
        }

        for (int x = 0; x < SIZE_H; x++) {
            for (int y = 0; y < SIZE_V; y++) {
                if (pillar[x][y]) {
                    if (state.contains(x, y)) stroke(255, 0, 0); 
                    else stroke(0);
                    circle(x * W, y * W, 5);
                }
            }
        }

        switch (state.mode) {
            case INIT: init(); break;
            case FORWARD: forward(); break;
            case WAIT_FORWARD: state.waitForward(); break;
            case WAIT_INIT: state.waitInit(); break;
            case WAIT_BACKWARD: state.waitBackward(); break;
            case BACKWARD: backward(); break;
            default: break;
        }
    }

    void init() {
        for (int y = 1; y < SIZE_V - 1; y++) {
            for (int x = 1; x < SIZE_H - 1; x++) {
                if (!pillar[x][y]) {
                    state.init(x, y);
                    pillar[x][y] = true;
                    return;
                }
            }
        }
        state.mode = Mode.END;
        state.tmp.clear();
        println("END");
    }

    void forward() {
        int[] dp = new int[4];
        int p = state.y * SIZE_H + state.x;
        int dirs = 0;

        if (!state.contains(p + 1)) dp[dirs++] = 1;
        if (!state.contains(p - 1)) dp[dirs++] = -1;
        if (!state.contains(p + SIZE_H)) dp[dirs++] = SIZE_H;
        if (!state.contains(p - SIZE_H)) dp[dirs++] = -SIZE_H;

        if (dirs == 0) {
            state.mode = Mode.WAIT_BACKWARD;
            state.count = 10;
        } else {
            int r = new Random().nextInt(dirs);
            int d = dp[r];
            switch (d) {
                case 1: 
                    wallH[state.x][state.y] = true; state.x++; break;
                case -1: 
                    wallH[state.x - 1][state.y] = true; state.x--; break;
                case SIZE_H: 
                    wallV[state.x][state.y] = true; state.y++; break;
                default: 
                    wallV[state.x][state.y - 1] = true; state.y--; break;
            }
            p += d;
            state.push(p);
            if (pillar[p % SIZE_H][p / SIZE_H]) {
                state.count = 2;
                state.mode = Mode.WAIT_INIT;
            } else {
                pillar[p % SIZE_H][p / SIZE_H] = true;
            }
        }
    }

    void backward() {
        int p = state.pop();
        int q = state.tmp.get(state.tmp.size() - 1);
        pillar[p % SIZE_H][p / SIZE_H] = false;

        if (p == q + 1) {
            wallH[q % SIZE_H][q / SIZE_H] = false;
        } else if (p == q - 1) {
            wallH[p % SIZE_H][p / SIZE_H] = false;
        } else if (p == q + SIZE_H) {
            wallV[q % SIZE_H][q / SIZE_H] = false;
        } else {
            wallV[p % SIZE_H][p / SIZE_H] = false;
        }

        state.x = q % SIZE_H;
        state.y = q / SIZE_H;
        if (state.tmp.size() == 1) {
            state.mode = Mode.FORWARD;
        }
    }

    public static void main(String[] args) {
        PApplet.main("enshu10.MazeV");
    }
}