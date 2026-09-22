package enshu9;

class Satellite {
    float x, y;
    float vx, vy;

    Satellite(float x, float y, float vx, float vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    void update() {
        double d2 = x * x + y * y;
        double d = Math.sqrt(d2);
        double f = 200 / d2;

        double sin = y / d;
        double cos = x / d;

        float ax = (float)(-f * cos);
        float ay = (float)(-f * sin);

        vx += ax;
        vy += ay;

        x += vx;
        y += vy;
    }
}
