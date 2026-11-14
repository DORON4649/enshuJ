package com.example.day8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paint2 extends JFrame {
    public static void main(String[] args) {
        int maxObjects = 9999;
        if (args.length > 0) {
            try { maxObjects = Integer.parseInt(args[0]); } 
            catch (NumberFormatException e) {}
        }
        
        Paint2 p = new Paint2();
        p.setTitle("ペイントツールもどき");

        JLabel countLabel = new JLabel("図形の数: 0 / " + maxObjects + "  ");
        countLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        PaintCanvas canvas = new PaintCanvas(maxObjects, countLabel);

        // === ツールバー ===
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JButton btnCircle = new JButton("円");
        JButton btnRect = new JButton("四角");
        JButton btnEraser = new JButton("消しゴム");
        //削除予定↓
        JButton btnBucket = new JButton("バケツ");
        JButton btnSelect = new JButton("範囲選択");
        JButton btnUndo = new JButton("Undo");
        JButton btnDelete = new JButton("選択削除");
        //削除予定↓
        JButton btnExport = new JButton("保存");

        btnCircle.addActionListener(e -> canvas.setTool(0));
        btnRect.addActionListener(e -> canvas.setTool(1));
        btnEraser.addActionListener(e -> canvas.setTool(2));
        btnBucket.addActionListener(e -> canvas.setTool(3));
        btnSelect.addActionListener(e -> canvas.setTool(4));
        btnUndo.addActionListener(e -> canvas.undo());
        btnDelete.addActionListener(e -> canvas.deleteSelected());
        btnExport.addActionListener(e -> canvas.exportImage());

        toolPanel.add(btnCircle);
        toolPanel.add(btnRect);
        toolPanel.add(btnEraser);
        toolPanel.add(btnBucket);
        toolPanel.add(btnSelect);
        toolPanel.add(btnUndo);
        toolPanel.add(btnDelete);
        toolPanel.add(btnExport);
        
        // 左：ボタン、右：Object Counter
        topPanel.add(toolPanel, BorderLayout.WEST);
        topPanel.add(countLabel, BorderLayout.EAST);
        
        p.getContentPane().add(topPanel, BorderLayout.NORTH);
        // ===================================

        canvas.setBackground(Color.WHITE);
        p.getContentPane().add(canvas, BorderLayout.CENTER);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setSize(900, 600);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
    }
}