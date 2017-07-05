package application.MyGraphic.Platforms;

import java.awt.*;

/**
 * Created by ДОМ on 03.07.2016.
 */
public class Fountain extends MyRect {
    private double k;
    private double q = 0.33;
    private double c = 1.05;
    public Fountain(int x, int y, int w, int h, double k) {
        super(x, y, w, h);
        this.k = k;
    }
    public Fountain(int x, int y, int w, int h) {
        super(x, y, w, h);
        k = 0.5;
    }
    @Override
    public void draw(Graphics g, int dx, int dy) {
        int bottomHeight = (int)(height * k);
        g.setColor(Color.cyan);
        g.fillRect(x + dx, yUp(y, (int)(bottomHeight * c)) - dy, width, (int)(bottomHeight * c));
        MyRect bottom = new MyRect(x, y, width, (int)(bottomHeight * c));
        bottom.draw(g, dx, dy);
        g.setColor(Color.gray);
        g.fillOval(x + dx, yUp(y, (height)) - dy, width, (int)(bottomHeight * q));
        g.fillOval(x + dx, yUp(y, (int)(height - (1 - q) * bottomHeight)) - dy, width, (int)(bottomHeight * q));
        g.fillRect(x + dx, yUp(y, (int)(height - bottomHeight * q / 2)) - dy, width, (int)((1 - q) * bottomHeight));
        g.setColor(Color.yellow);
        g.drawOval(x + dx, yUp(y, (height)) - dy, width, (int)(bottomHeight * q));
        g.drawOval(x + dx, yUp(y, (int)(height - (1 - q) * bottomHeight)) - dy, width, (int)(bottomHeight * q));
    }
}
