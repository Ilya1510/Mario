package application.MyGraphic.Platforms;

import application.Geometry.Sequence;

import java.awt.*;
import java.awt.geom.Point2D;

import static application.Application.WINDOW_H;

/**
 * Created by ДОМ on 22.06.2016.
 */

public class PlatformRect extends MyRect {
    public PlatformRect(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    @Override
    public void draw(Graphics g, int dx, int dy) {
        g.setColor(Color.green);
        g.fillRect(x + dx, yUp(y, height) - dy, width, height);
        super.draw(g, dx, dy);
    }
}
