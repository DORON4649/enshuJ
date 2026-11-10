package com.example.day8;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class PaintCanvas extends JPanel implements MouseListener, MouseMotionListener {
    ArrayList<Figure> objList;
    int maxObjects;
    boolean isSize50 = false;
    
    int currentTool = 0; 
    Point startPt = null;
    Point currentPt = null;

    JLabel countLabel; // ☆追加: Paint2から受け取るラベル

    // ☆修正: コンストラクタでラベルを受け取る
    PaintCanvas(int maxObjects, JLabel countLabel) {
        this.maxObjects = maxObjects;
        this.countLabel = countLabel;
        objList = new ArrayList<Figure>();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // === ☆追加: 個数表示を更新する共通メソッド ===
    private void updateCountLabel() {
        // ☆1の要件であるコンソール表示も残す[cite: 12]
        System.out.println("現在の描画数: " + objList.size());
        
        // ツールバー右上のGUIのラベルも更新する
        if (countLabel != null) {
            countLabel.setText("図形の数: " + objList.size() + " / " + maxObjects + "  ");
        }
    }

    public void setTool(int tool) {
        this.currentTool = tool;
        startPt = null;
        for(Figure f : objList) f.isSelected = false;
        repaint();
    }

    public void undo() {
        if (!objList.isEmpty()) {
            objList.remove(objList.size() - 1);
            updateCountLabel(); // ☆追加: 減ったので更新
            repaint();
        }
    }

    public void deleteSelected() {
        objList.removeIf(f -> f.isSelected);
        updateCountLabel(); // ☆追加: 減ったので更新
        repaint();
    }

    public void exportImage() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        for(Figure f : objList) f.isSelected = false;
        paint(g2); 
        g2.dispose();
        
        try {
            File outputFile = new File("canvas_export.png");
            ImageIO.write(image, "png", outputFile);
            JOptionPane.showMessageDialog(this, "画像を保存しました！\n" + outputFile.getAbsolutePath());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "保存に失敗しました。");
        }
        repaint();
    }

    private Color getRandomColor() {
        return new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
    }

    private void drawShape(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        double size = isSize50 ? 50.0 : 20.0;
        isSize50 = !isSize50;

        Figure obj = null;
        if (currentTool == 0) obj = new Circle(size, getRandomColor());
        else if (currentTool == 1) obj = new Rect(size, getRandomColor());
        else if (currentTool == 2) obj = new Circle(size, Color.WHITE);

        if (obj != null) {
            obj.moveto(x, y);
            objList.add(obj);
            if (objList.size() > maxObjects) objList.remove(0);
            
            updateCountLabel(); // ☆追加: 増えたので更新
        }
    }

    @Override public void mousePressed(MouseEvent e) {
        if (currentTool == 3) {
            for (int i = objList.size() - 1; i >= 0; i--) {
                Figure f = objList.get(i);
                if (f.contains(e.getX(), e.getY())) {
                    f.color = getRandomColor();
                    break;
                }
            }
        } else if (currentTool == 4) {
            startPt = e.getPoint();
            currentPt = e.getPoint();
            for(Figure f : objList) f.isSelected = false;
        } else {
            drawShape(e);
        }
        repaint();
    }

    @Override public void mouseDragged(MouseEvent e) {
        if (currentTool == 4 && startPt != null) {
            currentPt = e.getPoint();
        } else if (currentTool == 0 || currentTool == 1 || currentTool == 2) {
            drawShape(e);
        }
        repaint();
    }

    @Override public void mouseReleased(MouseEvent e) {
        if (currentTool == 4 && startPt != null) {
            currentPt = e.getPoint();
            int sx = Math.min(startPt.x, currentPt.x);
            int sy = Math.min(startPt.y, currentPt.y);
            int sw = Math.abs(startPt.x - currentPt.x);
            int sh = Math.abs(startPt.y - currentPt.y);
            Rectangle rect = new Rectangle(sx, sy, sw, sh);
            
            for (Figure f : objList) {
                if (rect.contains(f.x, f.y)) f.isSelected = true;
            }
            startPt = null;
        } else if (currentTool == 0 || currentTool == 1 || currentTool == 2) {
            drawShape(e);
        }
        repaint();
    }

    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        for (Figure f : objList) f.paint(g2);

        if (currentTool == 4 && startPt != null && currentPt != null) {
            int sx = Math.min(startPt.x, currentPt.x);
            int sy = Math.min(startPt.y, currentPt.y);
            int sw = Math.abs(startPt.x - currentPt.x);
            int sh = Math.abs(startPt.y - currentPt.y);
            g2.setColor(new Color(0, 0, 255, 100));
            g2.fillRect(sx, sy, sw, sh);
            g2.setColor(Color.BLUE);
            g2.drawRect(sx, sy, sw, sh);
        }
    }
}