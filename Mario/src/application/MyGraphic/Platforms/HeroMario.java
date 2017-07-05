package application.MyGraphic.Platforms;

import application.process.KeyListenerMainWindow;

import java.awt.*;

import static application.Application.WINDOW_H;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by ДОМ on 23.06.2016.
 */
public class HeroMario implements Platform{
    public static final int EPS = 1;
    public static final int TIME_OF_DECELERATION = 700;
    public static final int TIME_OF_DECELERATION_ON_GRASS = 10;
    public static final double g = 0.004;
    public static final int MAX_TIME_JUMP = 400;
    public static final double SPEED_X_RUN = 1;
    public static final double SPEED_X_RUN_AIR = 0.5;
    public static final double SPEED_Y_JUMP = 0.65;

    public double x, y, w, h;
    public boolean[] bort;
    private double speedX;
    private double speedY;
    public double motionX;
    public int jumpTime;
    public void setBord(int i) {
        bort[i] = true;
    }
    public void desetBord(int i) {
        bort[i] = false;
    }
    public HeroMario(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        jumpTime = 0;
        motionX = 0; speedX = 0;
        speedY = 0;
        bort = new boolean[4];
    }
    public double getSpeedX() {return speedX;}
    public double getSpeedY() {return speedY;}
    public int getX() {
        return (int)x;
    }
    public int getY() {
        return (int)y;
    }
    public int getW() { return (int)w; }
    public int getH() { return (int)h; }
    public void goRight() {
        motionX = (bort[3] ? TIME_OF_DECELERATION_ON_GRASS : TIME_OF_DECELERATION);
        speedX = SPEED_X_RUN;
    }
    public void goLeft() {
        motionX = (bort[3] ? TIME_OF_DECELERATION_ON_GRASS : TIME_OF_DECELERATION);
        speedX = -SPEED_X_RUN;
    }
    public void jump(int dt) {
        if (jumpTime < MAX_TIME_JUMP) {
            speedY = SPEED_Y_JUMP;
        }
    }
    public void setFuturePos(int dt) {
        setFutureX(dt);
        setFutureY(dt);
    }
    public void setPastPos(int dt) {
        setPastX(dt);
        setPastY(dt);
    }
    private void setFutureX(int dt) {
        if (bort[0]) {
            speedX = max(speedX, 0);
        }
        if (bort[2]) {
            speedX = min(speedX, 0);
        }
        if (!bort[3]) { // we are in air
            speedX = min(speedX, SPEED_X_RUN_AIR);
            speedX = max(speedX, -SPEED_X_RUN_AIR);
        }
        x = (x + speedX * min(dt, motionX));
    }
    private void setFutureY(int dt) {
        if (bort[1]) {
            speedY = min(speedY, 0);
        }
        if (bort[3]) {
            speedY = max(speedY, 0);
        }
        y =  (y + speedY * dt);
    }
    private void setPastX(int dt) {
        if (bort[0]) {
            speedX = max(speedX, 0);
        }
        if (bort[2]) {
            speedX = min(speedX, 0);
        }
        if (!bort[3]) { // we are in air
            speedX = min(speedX, SPEED_X_RUN_AIR);
            speedX = max(speedX, -SPEED_X_RUN_AIR);
        }
        x =  (x - speedX * min(dt, motionX));
    }
    private void setPastY(int dt) {
        if (bort[1]) {
            speedY = min(speedY, 0);
        }
        if (bort[3]) {
            speedY = max(speedY, 0);
        }
        y =  (y - speedY * dt);
    }
    public void updatePos(int dt) {
        if (bort[0]) {
            speedX = max(speedX, 0);
        }
        if (bort[2]) {
            speedX = min(speedX, 0);
        }
        if (bort[1]) {
            speedY = min(speedY, 0);
        }
        if (bort[3]) {
            speedY = max(speedY, 0);
            jumpTime = 0;
            motionX = min(motionX, TIME_OF_DECELERATION_ON_GRASS);
        } else {
            speedX = min(speedX, SPEED_X_RUN_AIR);
            speedX = max(speedX, -SPEED_X_RUN_AIR);
            jumpTime += dt;
        }

        x += speedX * min(dt, motionX);
        motionX -= min(dt, motionX);
        y += speedY * dt;
        speedY -= dt * g;
        if (y < 0) {
            y = 0;
            jumpTime = 0;
        }
        if (x < 0) {
            x = 0;
        }
    }
    public void slowDown(int k) {
        if (k != 0) {
            speedX /= k;
            speedY /= k;
        }
    }
    public void sdvigRightEps() {
        x += EPS;
    }
    public void sdvigLeftEps() {
        x -= EPS;
    }
    public void sdvigUpEps() {
        y += EPS;
    }
    public void sdvigDownEps() {
        y -= EPS;
    }
    @Override
    public void draw(Graphics g, int dx, int dy) {
        g.setColor(Color.BLUE);
        g.fillRect((int)x + dx, WINDOW_H - (int)y - (int)h - dy, (int)w, (int)h);
        g.setColor(Color.RED);
        g.drawRect((int)x + dx, WINDOW_H - (int)y - (int)h - dy, (int)w, (int)h);
    }
    @Override
    public boolean pointInPlatform(int x, int y) {
        return (this.x <= x && this.y <= y && x <= this.x + w && y <= this.y + h);
    }
    @Override
    public boolean marioSectPlatform(HeroMario mario) {
        return true;
    }
}
