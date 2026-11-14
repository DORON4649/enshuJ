package com.example.day8;
import java.awt.*;

public abstract class Figure extends Coord {
    Color color;
    boolean isSelected = false;
    Figure() { color = Color.BLACK; }
    
    public abstract void paint(Graphics2D g);
    public abstract boolean contains(double px, double py);
}