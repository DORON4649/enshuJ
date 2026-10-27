package com.example.day6.Kadai;

public class Coord {
    public double x, y;

    public Coord() {
        x = y = 0.0;
        System.out.println("Coord");
    }

    // 相対座標で移動（課題53）
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    // 絶対座標で移動（課題53）
    public void moveto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // 座標を表示するメソッド（課題54）
    public void print() {
        System.out.println("x = " + x + ", y = " + y);
    }
}