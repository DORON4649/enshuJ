package com.example.day7;

public class Coord {
    double x, y;

    Coord() {
        x = y = 0;
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void moveto(double x, double y) {
        this.x = x;
        this.y = y;
    }
}