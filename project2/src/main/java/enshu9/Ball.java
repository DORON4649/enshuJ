package enshu9;

class Ball {
    float x, y;
    float vx, vy;
    int r;

    Ball(float x, float y, float vx, float vy, int r) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.r = r;
    }

    void update(int width, int height) {
        x += vx;
        y += vy;

    if (x<=0 || x>=width) {
        vx = -vx;
    }
    if (y<=0 || y>=height) {
        vy = -vy;
}
}
}
