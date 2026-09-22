package enshu10;

public class Ghost {
    int x;
    int y;
    int color;
    int age;

    public Ghost(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        age = 0;
    }

    void update() {
        age++;
    }

    boolean isRookie() {
        if (age == 0) {
            return true;
        } else {
            return false;
        }
    }
}
