package com.example.day7;

import java.awt.*;
import java.awt.geom.*;

public class Circle extends Figure {
    double size;
    Ellipse2D f;

    Circle() {
        color = Color.BLACK;
        size = 10.0;
    }

    @Override 
    public void paint(Graphics2D g) {
        f = new Ellipse2D.Double(x - size / 2, y - size / 2, size, size);
        g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setPaint(color);
        g.draw(f); // 塗りつぶしではなく枠線描画に戻しています
    }
}