package enshu11;
import java.util.ArrayDeque;
import java.util.Deque;

public class Ghost {
    int x;
    int y;
    int color;
    record Pos(int x, int y) {}
    Deque<Pos> route = new ArrayDeque<>(); // 正解ルートを覚えるスタック

    public Ghost(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        route.add(new Pos(x, y)); // スタート地点を入れておく
    }

    void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void goBackTo(Pos pos) {
        x = pos.x();
        y = pos.y();
    }
}