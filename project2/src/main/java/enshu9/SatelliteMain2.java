package enshu9;

import processing.core.PApplet;
import java.util.ArrayList;

public class SatelliteMain2 extends PApplet {

    Satellite planet = new Satellite(100, 0, 0, 1.3f); 
    ArrayList<Satellite> meteors = new ArrayList<>();

    // 確変システム用の変数
    int hitCount = 0;
    boolean isFever = false;
    int feverTimer = 0;
    int maxFeverTime = 300; // 確変持続時間（フレーム数、約5秒）

    // 演出用の変数
    int flashTimer = 0; // 画面フラッシュ用のタイマー

    public void settings() {
        size(400, 400);
    }

    public void setup() {
        background(0);
        // 文字の平滑化（滑らかにする）
        textFont(createFont("SansSerif", 40f));
    }

    public void draw() {
        // --- 背景の軌跡エフェクト（確変中は超高速虹色フェード！） ---
        noStroke();
        if (isFever) {
            colorMode(HSB, 360, 100, 100); // 虹色の計算がしやすいHSBモードに一時変更
            // TWO_PI * 5.0f で回転速度を高速化
            fill((frameCount * 5.0f) % 360, 100, 100, 40); 
            rect(0, 0, width, height);
            colorMode(RGB, 255); // RGBモードに戻す
        } else {
            fill(0, 30); // 通常時は黒
            rect(0, 0, width, height);
        }

        // --- ド派手演出1：画面全体の揺れ（スクリーンシェイク） ---
        float shakeX = 0;
        float shakeY = 0;
        if (isFever) {
            // 残り時間が少なくなると揺れが収まっていく
            float shakeIntensity = map(feverTimer, 0, maxFeverTime, 1.0f, 15.0f);
            shakeX = random(-shakeIntensity, shakeIntensity);
            shakeY = random(-shakeIntensity, shakeIntensity);
        }

        // ==========================================
        // 物理シミュレーション用の描画エリア開始
        // ==========================================
        pushMatrix(); // 元の座標系を保存
        
        // 画面揺れと画面中心への移動を統合
        translate(width / 2 + shakeX, height / 2 + shakeY);
        scale(1, -1); // 数学座標系へ

        // --- ド派手演出2：中心の星（地球）のパルス（鼓動）描画 ---
        if (isFever) {
            // sin関数でサイズが1.0〜2.0倍に滑らかに変化
            float pulseScale = sin(frameCount * 0.2f) * 0.5f + 1.5f;
            fill(random(100, 255), random(150, 255), 255); // 虹色パルス
            circle(0, 0, 30 * pulseScale);
            // さらにその周りに白い光のリング
            stroke(255, 150);
            strokeWeight(3);
            noFill();
            circle(0, 0, 30 * pulseScale * 1.3f);
            noStroke();
        } else {
            fill(50, 100, 255); // 通常時
            circle(0, 0, 30);
        }

        // 惑星の更新と描画
        planet.update();
        fill(200, 200, 200);
        drawSatellite(planet);

        // --- 確変状態のタイマー処理 ---
        if (isFever) {
            feverTimer--; 
            if (feverTimer <= 0) {
                isFever = false; 
                hitCount = 0;    
            }
        }

        // --- 隕石の生成（確変中は雨のように降り注ぐ！） ---
        float spawnRate = isFever ? 0.6f : 0.05f; // 確変中の確率をさらにアップ

        if (random(1) < spawnRate) {
            float startX = random(-width / 2, width / 2);
            float startY = height / 2;
            // 確変中は隕石の初速度も少しバラつかせる
            float startVx = random(-1.5f, 1.5f);
            float startVy = random(-2.5f, -0.5f);
            meteors.add(new Satellite(startX, startY, startVx, startVy));
        }

        // --- 隕石の更新と衝突判定（軌跡の描画も含む） ---
        for (int i = meteors.size() - 1; i >= 0; i--) {
            Satellite m = meteors.get(i);
            m.update();

            // --- ド派手演出3：隕石の軌跡（トレイル）の強化 ---
            // Satellite.javaの速度ベクトルを使って、隕石の後ろに線を引く
            float v = (float) Math.sqrt(m.vx * m.vx + m.vy * m.vy);
            if (v > 0.1f) {
                if (isFever) {
                    // 確変中はトレイルを長く、虹色に
                    stroke(random(360), 255, 255, random(100, 200)); 
                    strokeWeight(random(2, 6)); // 太さもバラつかせる
                    // 速度の10倍の長さの尾を引く
                    line(m.x, m.y, m.x - m.vx * 10, m.y - m.vy * 10);
                } else {
                    stroke(255, 100, 50, 100); // 通常はオレンジの透明トレイル
                    strokeWeight(1);
                    line(m.x, m.y, m.x - m.vx * 5, m.y - m.vy * 5);
                }
                noStroke(); // 描画後はストロークをリセット
            }

            // 隕石本体の描画
            if (isFever) {
                // 確変中はより鮮やかにチカチカ
                fill(random(360), 255, 255);
            } else {
                fill(255, 100, 50); 
            }
            circle(m.x, m.y, 5);

            float distSq = m.x * m.x + m.y * m.y;
            if (distSq < 200) { // 地球に衝突
                if (!isFever) {
                    hitCount++; 
                    if (hitCount >= 20) {
                        isFever = true;
                        feverTimer = maxFeverTime; 
                        // --- ド派手演出4：確変突入時の画面フラッシュタイマーセット ---
                        flashTimer = 10; 
                    }
                }
                meteors.remove(i);
            } else if (distSq > 400000) { // 画面外
                meteors.remove(i);
            }
        }
        
        popMatrix(); // 座標系を元に戻す
        // ==========================================
        // UI・テキスト描画エリア開始
        // ==========================================

        // 左上のヒットカウント表示
        fill(255);
        textSize(16);
        text("Hit: " + hitCount + " / 20", 10, 25);

        // --- ド派手演出5：確変突入時の白フラッシュ描画 ---
        if (flashTimer > 0) {
            flashTimer--;
            fill(255); // 白で塗りつぶし
            rect(0, 0, width, height);
        }

        // --- ド派手演出6：「あけおめ！！！」の文字演出強化（巨大化パルス） ---
        if (isFever) {
            fill(255, random(150, 255), 0); // 黄色〜白でチカチカ
            
            // sin関数でtextSizeを40〜80までパルス（巨大化・縮小）
            float textPulse = sin(frameCount * 0.3f) * 20 + 60;
            textSize(textPulse);
            
            textAlign(CENTER, CENTER); 
            text("単位くれ", width / 2, height / 2);
            textAlign(LEFT, BASELINE); // 基準を元に戻す
        }
    }

    void drawSatellite(Satellite s) {
        circle(s.x, s.y, 10);
        float v = (float) Math.sqrt(s.vx * s.vx + s.vy * s.vy);
        if (v > 0.01f) {
            float sin = s.vy / v;
            float cos = s.vx / v;
            float xx = 10 * cos;
            float yy = 10 * sin;
            stroke(255, 200); // 通常時の惑星の進行方向線は少し透明に
            line(s.x, s.y, s.x + xx, s.y + yy);
            noStroke();
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu9.SatelliteMain2");
    }
}