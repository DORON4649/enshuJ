package com.example.day7;

import javax.swing.*;
import java.awt.*;

public class Paint2 extends JFrame {
    public static void main(String[] args) {
        Paint2 p = new Paint2();
        PaintCanvas canvas;
        p.setTitle("Paint2 - 課題W");
        canvas = new PaintCanvas();
        canvas.setBackground(Color.WHITE);
        p.getContentPane().add(canvas, BorderLayout.CENTER);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setSize(900, 600);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
    }
}