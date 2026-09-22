/*
確変雪だるま
*/
package enshu8;

import processing.core.PApplet;
import processing.core.PVector;

public class Snow extends PApplet {

    final int N = 30;
    final int W = 15;
    final int R = 5;
    
    int maxParticles = 35; 
    float snowTop[] = new float[N];
    Particle[] particles = new Particle[300]; 

    PVector gravity;
    PVector wind;

    int snowmanStage = 0;
    float hueOffset = 0; 

    public void settings() {
        size(N * W, 400);
    }

    public void setup() {
        gravity = new PVector(0, 0.07f);
        wind = new PVector(0.03f, 0);
        
        for (int i = 0; i < particles.length; i++) {
            particles[i] = new Particle();
        }
        
        for (int i = 0; i < snowTop.length; i++) {
            snowTop[i] = height;
        }
    }

    public void draw() {
        if (snowmanStage >= 2) {
            colorMode(HSB, 360, 100, 100);
            for (int y = 0; y < height; y++) {
                float hue = (float)y / height * 180 + hueOffset;
                stroke(hue % 360, 80, 90);
                line(0, y, width, y);
            }
            colorMode(RGB, 255, 255, 255);
            hueOffset += 4; 
        } else {
            background(192);
        }

        float nextTop[] = new float[N];
        for (int i = 0; i < N; i++) {
            float left = (i > 0) ? snowTop[i - 1] : height;
            float right = (i < N - 1) ? snowTop[i + 1] : height;
            float smoothed = snowTop[i] * 0.96f + (left + right) / 2.0f * 0.04f;
            nextTop[i] = min(smoothed, height);
        }
        snowTop = nextTop;

        boolean triggerReset = false;
        for (int i = 0; i < N; i++) {
            if (snowTop[i] < 150) {
                triggerReset = true;
                break;
            }
        }

        if (triggerReset) {
            for (int i = 0; i < N; i++) {
                snowTop[i] = height;
            }
            if (snowmanStage < 2) {
                snowmanStage++;
                if (snowmanStage == 2) {
                    maxParticles = 120; 
                    gravity.y = 0.15f;  
                    wind.x = 0.08f;     
                }
            }
        }

        stroke(255);
        fill(255);
        beginShape();
        vertex(0, height); 
        for (int i = 0; i < snowTop.length; i++) {
            vertex(W * i + W / 2, snowTop[i]); 
        }
        vertex(width, height); 
        endShape(CLOSE);

        if (snowmanStage >= 1) {
            stroke(255);
            fill(255);
            circle(width * 0.75f, height - 50, 100);
        }
        if (snowmanStage >= 2) {
            stroke(255);
            fill(255);
            circle(width * 0.75f, height - 120, 70);
            
            fill(255, 0, 0);
            textSize(32);
            textAlign(CENTER);
            text("merryChristmas!!!", width / 2, 80);
        }

        for (int i = 0; i < maxParticles; i++) {
            Particle p = particles[i];
            
            p.velocity.add(gravity);
            p.velocity.add(wind);
            p.pos.add(p.velocity);

            stroke(255);
            fill(255);
            circle(p.pos.x, p.pos.y, R * 2);

            int index = (int)(p.pos.x / W);

            if (p.pos.x < -300 || p.pos.x >= width + 50 || p.pos.y >= height) {
                p.reset();
            } else if (index >= 0 && index < N) {
                if (p.pos.y >= snowTop[index] - R) {
                    
                    while (index < N - 1 && snowTop[index + 1] > snowTop[index] + 5) {
                        index++;
                    }

                    if (random(1) < 0.04f) {
                        snowTop[index] -= 25; 
                    } else {
                        snowTop[index] -= 8;  
                    }
                    
                    p.reset();
                }
            }
        }
    }

    class Particle {
        PVector pos;
        PVector velocity;

        Particle() {
            pos = new PVector();
            velocity = new PVector();
            reset();
            pos.x = random(-300, width);
            pos.y = random(-300, height); 
        }

        void reset() {
            pos.x = random(-350, width * 0.7f);
            pos.y = random(-150, -10);
            velocity.set(random(-0.5f, 0.5f), random(0, 1.0f));
        }
    }

    public static void main(String args[]) {
        PApplet.main("enshu8.Snow");
    }
}