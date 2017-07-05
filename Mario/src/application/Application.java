package application;

/**
 * Created by ДОМ on 22.06.2016.
 */
import application.Geometry.Sequence;
import application.Levels.Level;
import application.MyGraphic.MyWindow;
import application.MyGraphic.Platforms.HeroMario;
import application.Screen.ScreenType;
import application.process.KeyListenerMainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.File;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Application {
    private MyWindow screen;
    private String name;
    private Level curLevel;
    public HeroMario hero;
    private long lastDrawingTime;
    private long lastUpdateTime;
    private KeyListenerMainWindow keyListener;
    private ScreenType screenType;

    public static final int WINDOW_W = 1000;
    public static final int WINDOW_H = 700;
    public static final int DRAW_FRESH_TIME = 5;
    public static final int EPS = 1;
    public Application(String name) {
        this.name = name;
    }
    public void start() {
        screen = new MyWindow(name, WINDOW_W, WINDOW_H);
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.setVisible(true);

        screenType = ScreenType.GAME;
        System.out.println(screenType.name());
        openScreen();

        lastUpdateTime = System.currentTimeMillis();
        draw();

        timer.start();
    }

    private Timer timer = new Timer(3, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            long curTime = System.currentTimeMillis();
            update((int)(curTime - lastUpdateTime));
            lastUpdateTime = curTime;
            if (curTime - lastDrawingTime >= DRAW_FRESH_TIME) {
                draw();
                lastDrawingTime = curTime;
            }
        }
    });
    int count = 0;
    public void draw() {
        count++;
        Graphics g;
        screen.createBufferStrategy(2);
        BufferStrategy bufferStrategy = screen.getBufferStrategy();
        g = bufferStrategy.getDrawGraphics();

        int curDrawX = min(0, WINDOW_W / 2 - hero.getX());
        int curDrawY = min(0, WINDOW_H / 2 - hero.getY());
        curDrawX = max(curDrawX, -curLevel.endLevelX + WINDOW_W);
        curDrawY = max(curDrawY, -curLevel.endLevelY + WINDOW_H);
        for (int i = 0; i < curLevel.plato.length; i++) {
            curLevel.plato[i].draw(g, curDrawX, curDrawY);
        }
        hero.draw(g, curDrawX, curDrawY);

        bufferStrategy.show();
    }
    private void openScreen() {
        if (screenType == ScreenType.MAIN_MENU) {
            openScreenMainMenu();
        } else if (screenType == ScreenType.GAME) {
            openScreenGame(0);
        }
    }

    private void openScreenMainMenu() {

    }

    private void openScreenGame(int numLevel) {
        if (numLevel == 0) {
            try {
                curLevel = Level.createLevel(new File("Mario/src/application/Levels/levelTmp.txt"));
            } catch (java.io.FileNotFoundException e) {
                System.out.println("File not Found!!!");
                return;
            }
            keyListener = new KeyListenerMainWindow();
            screen.addKeyListener(keyListener);
            hero = new HeroMario(50, 646, 25, 50);
        }
    }

    public void update(int dt) {
        updateMarioMotion(keyListener, dt);
        updateMarioBorders();
        updateMarioPos(dt);
    }

    public void updateMarioMotion(KeyListenerMainWindow keyListener, int dt) {
        if (keyListener.leftPressed && !keyListener.rightPressed) {
            hero.goLeft();
        }
        if (keyListener.rightPressed && !keyListener.leftPressed) {
            hero.goRight();
        }
        if (keyListener.upPressed && !keyListener.downPressed) {
            hero.jump(dt);
        }
    }

    public void updateMarioPos(int dt) {
        for (int i = dt; i >= 0; i--) { //Пытаемся передвинуть Марио на максимальное время, при котором он не врежется
            hero.setFuturePos(i);
            if (curLevel.marioSectPlatform(hero)) {
                hero.setPastPos(i);
                continue;
            } else {
                if (i == 0) { // Если нельзя передвинуться даже на 1 мс, то сбавляем скорость.
                    hero.slowDown(dt);
                }
                hero.setPastPos(i);
                hero.updatePos(i);
                break;
            }
        }
    }

    public void updateMarioBorders() {
        updateLeftBorder();
        updateUpBorder();
        updateRightBorder();
        updateDownBorder();
    }

    public void updateLeftBorder() {
        hero.sdvigLeftEps();

        if (curLevel.marioSectPlatform(hero)) {
            hero.setBord(0);
        } else {
            hero.desetBord(0);
        }
        hero.sdvigRightEps();
    }

    public void updateRightBorder() {
        hero.sdvigRightEps();
        if (curLevel.marioSectPlatform(hero)) {
            hero.setBord(2);
        } else {
            hero.desetBord(2);
        }
        hero.sdvigLeftEps();
    }

    public void updateUpBorder() {
        hero.sdvigUpEps();
        if (curLevel.marioSectPlatform(hero)) {
            hero.setBord(1);
        } else {
            hero.desetBord(1);
        }
        hero.sdvigDownEps();
    }

    public void updateDownBorder() {
        hero.sdvigDownEps();
        if (curLevel.marioSectPlatform(hero)) {
            hero.setBord(3);
        } else {
            hero.desetBord(3);
        }
        hero.sdvigUpEps();
    }

}
