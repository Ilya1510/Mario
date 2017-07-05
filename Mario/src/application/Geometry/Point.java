package application.Geometry;

/**
 * Created by ДОМ on 30.06.2016.
 */
public class Point {
    private double x, y;
    public Point() {
        x = y = 0;
    }
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
