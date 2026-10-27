package com.example.day4;

public class CitStudent2 {
    String no;
    String name;
    int year;
    int grade;
    int department;

    // ★ 33(a) 追加が必要なフィールド
    int high;
    int low;
    double ave;
    int total;
    int num;

    // 引数なしコンストラクタ
    CitStudent2() {
        no = "";
        name = "";
        year = 0;
        grade = 0;
        department = 0;
        // ★ 33(a) 集計用変数の初期化
        high = -1;
        low = -1;
        ave = -1.0;
        total = 0;
        num = 0;
    }

    // 引数ありコンストラクタ
    CitStudent2(String p_no, String p_name) {
        no = p_no;
        name = p_name;
        year = 0;
        grade = 0;
        department = 0;
        // ★ 33(a) 集計用変数の初期化
        high = -1;
        low = -1;
        ave = -1.0;
        total = 0;
        num = 0;
    }

    public void setNo(String p_no) {
        no = p_no;
    }
    public void setName(String p_name) {
        name = p_name;
    }
    public void setYear(int p_year) {
        year = p_year;
    }
    public void setGrade(int p_grade) {
        grade = p_grade;
    }
    public void setDepartment(int p_department) {
        department = p_department;
    }

    public String getNo() {
        return no;
    }
    public String getName() {
        return name;
    }
    public int getYear() {
        return year;
    }
    public int getGrade() {
        return grade;
    }
    public int getDepartment() {
        return department;
    }

    // ★ 33(b) 集計用のゲッター
    public int getHigh() {
        return high;
    }
    public int getLow() {
        return low;
    }
    public double getAve() {
        return ave;
    }
    public int getTotal() {
        return total;
    }
    public int getNum() {
        return num;
    }

    // ★ 33(c) 科目数カウントアップ
    public void incNum() {
        num++;
    }

    // ★ 33(d) 成績追加と集計
    public void addScore(int score) {
        // 0〜100 の範囲外は無効
        if (score < 0 || score > 100) {
            return;
        }

        // 初回（まだ点数が登録されていない場合）
        if (num == 0) {
            high = score;
            low = score;
            total = score;
            num = 1;
            ave = (double) score;
        } else {
            // 2回目以降
            if (score > high) {
                high = score;
            }
            if (score < low) {
                low = score;
            }
            total += score;
            num++;
            ave = (double) total / num;
        }
    }
}