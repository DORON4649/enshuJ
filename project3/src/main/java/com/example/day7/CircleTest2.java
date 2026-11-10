package com.example.day7; // 実行環境のパッケージ名に合わせています

import java.util.*; // ArrayList を使うために必要

public class CircleTest2 {
    public static void main(String[] args) {
        ArrayList<Coord> objList = new ArrayList<Coord>(); // さまざまな図形を管理するためのクラス
        Coord c = new Circle(); // Coord クラスの変数にCircle を代入
        
        objList.add(c); // objList の最後にcを挿入。
        
        c.move(100, 100); // cのmove メソッドを呼び出す
        System.out.println("x = " + c.x + " y = " + c.y);
        
        // c.setSize(20); // Coord クラスにないので呼び出せない
    }
}