package enshu9;

class GBall {
    float x, y;
    float vx, vy;
    int r;
    float cor;

    GBall(float x, float y, float vx, float vy, int r, float cor) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.r = r;
        this.cor = cor;
    }

    void update() {
        vy += -0.1;

        x += vx;
        y += vy;

        if (y <= 0) {
            vy = -vy * cor;
            y += vy;
        }
    }
}