package application.MyGraphic.Platforms;

import java.awt.*;

import static application.Application.WINDOW_H;

/**
 * Created by ДОМ on 22.06.2016.
 */

/**
 * Платформа - это, что умеет рисоваться, определять принадлежит ли ему точка и говорить пересекает ли герой её.
 * Но Может и не только герой, а монстер, но нужно тогда коче-то переделать.
 */
public interface Platform {
    void draw(Graphics g, int dx, int dy);
    boolean pointInPlatform(int x, int y);
    boolean marioSectPlatform(HeroMario mario);
}


