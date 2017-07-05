package application.MyGraphic.Platforms;

import application.Geometry.Sequence;

import java.awt.*;

import static application.Application.*;

/**
 * Created by ДОМ on 22.06.2016.
 */
public class MyRect implements Platform {
    public static final int cx[] = {0, 1, 1, 0, 0};
    public static final int cy[] = {0, 0, 1, 1, 0};
    int x, y, width, height;
    public MyRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    @Override
    public void draw(Graphics g, int dx, int dy) {
        g.setColor(Color.black);
        g.drawRect(x + dx, yUp(y, height) - dy, width, height);
    }
    @Override
    public boolean pointInPlatform(int x, int y) {
        //System.out.println(this.x + " " + this.y + " " + width + " " + height + " " + x + " " + y);
        return (this.x <= x && this.y <= y && x <= this.x + width && y <= this.y + height);
    }
    public static int yUp(int y, int h) {
        return WINDOW_H - y - h;
    }
    @Override
    public boolean marioSectPlatform(HeroMario mario) {
        for (int i = 0; i < 4; i++) {
            Point a = new Point(mario.getX() + cx[i] * mario.getW(), mario.getY() + cy[i] * mario.getH());
            Point b = new Point(mario.getX() + cx[i + 1] * mario.getW(), mario.getY() + cy[i + 1] * mario.getH());
            Sequence s = new Sequence(a.getX(), a.getY(), b.getX(), b.getY());
            for (int j = 0; j < 4; j++) {
                Point c = new Point(x + cx[j] * width, y + cy[j] * height);
                Point d = new Point(x + cx[j + 1] * width, y + cy[j + 1] * height);
                Sequence q = new Sequence(c.getX(), c.getY(), d.getX(), d.getY());
                if (Sequence.perSeq(s, q)) {
                    return true;
                }
            }
        }
        return false;
    }
}
