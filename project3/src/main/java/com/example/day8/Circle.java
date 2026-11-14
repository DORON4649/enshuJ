package com.example.day8;
import java.awt.*;
import java.awt.geom.*;

public class Circle extends Figure {
    double size;

    Circle(double size, Color color) {
        this.size = size;
        this.color = color;
    }

    @Override public void paint(Graphics2D g) {
        Ellipse2D f = new Ellipse2D.Double(x - size/2, y - size/2, size, size);
        g.setPaint(color);
        g.fill(f);
        
        // 範囲選択-赤枠表示(Object)
        if (isSelected) {
            g.setPaint(Color.RED);
            g.setStroke(new BasicStroke(2.0f));
            g.draw(f);
        }
    }

    @Override public boolean contains(double px, double py) {
        // 範囲選択の座標チェック
        Ellipse2D f = new Ellipse2D.Double(x - size/2, y - size/2, size, size);
        return f.contains(px, py);
    }
}