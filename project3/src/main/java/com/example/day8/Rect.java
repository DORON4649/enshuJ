package com.example.day8;
import java.awt.*;
import java.awt.geom.*;

public class Rect extends Figure {
    double size;

    Rect(double size, Color color) {
        this.size = size;
        this.color = color;
    }

    @Override public void paint(Graphics2D g) {
        Rectangle2D f = new Rectangle2D.Double(x - size/2, y - size/2, size, size);
        g.setPaint(color);
        g.fill(f);

        if (isSelected) {
            g.setPaint(Color.RED);
            g.setStroke(new BasicStroke(2.0f));
            g.draw(f);
        }
    }

    @Override public boolean contains(double px, double py) {
        Rectangle2D f = new Rectangle2D.Double(x - size/2, y - size/2, size, size);
        return f.contains(px, py);
    }
}