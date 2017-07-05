package application.Geometry;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by ДОМ on 30.06.2016.
 */
public class Sequence {
    private Point p, q;
    public Sequence(double x1, double y1, double x2, double y2){
        p = new Point(x1, y1);
        q = new Point(x2, y2);
    }
    public static boolean perSeq(Sequence a, Sequence b) {
        Point[] sect = perLine(a, b);
        if (sect.length == 0) {
            return false;
        } else if (sect.length == 1) {
            boolean prov = true;
            prov &= (sect[0].getX() >= min(a.p.getX(), a.q.getX()));
            prov &= (sect[0].getX() >= min(b.p.getX(), b.q.getX()));
            prov &= (sect[0].getY() >= min(a.p.getY(), a.q.getY()));
            prov &= (sect[0].getY() >= min(b.p.getY(), b.q.getY()));
            prov &= (sect[0].getX() <= max(a.p.getX(), a.q.getX()));
            prov &= (sect[0].getX() <= max(b.p.getX(), b.q.getX()));
            prov &= (sect[0].getY() <= max(a.p.getY(), a.q.getY()));
            prov &= (sect[0].getY() <= max(b.p.getY(), b.q.getY()));
            return prov;
        } else {
            boolean prov1 = true;
            prov1 &= (a.p.getX() >= min(b.p.getX(), b.q.getX()));
            prov1 &= (a.p.getY() >= min(b.p.getY(), b.q.getY()));
            prov1 &= (a.p.getX() <= max(b.p.getX(), b.q.getX()));
            prov1 &= (a.p.getY() <= max(b.p.getY(), b.q.getY()));
            boolean prov2 = true;
            prov2 &= (a.q.getX() >= min(b.p.getX(), b.q.getX()));
            prov2 &= (a.q.getY() >= min(b.p.getY(), b.q.getY()));
            prov2 &= (a.q.getX() <= max(b.p.getX(), b.q.getX()));
            prov2 &= (a.q.getY() <= max(b.p.getY(), b.q.getY()));
            boolean prov3 = true;
            prov3 &= (b.q.getX() >= min(a.p.getX(), a.q.getX()));
            prov3 &= (b.q.getY() >= min(a.p.getY(), a.q.getY()));
            prov3 &= (b.q.getX() <= max(a.p.getX(), a.q.getX()));
            prov3 &= (b.q.getY() <= max(a.p.getY(), a.q.getY()));
            boolean prov4 = true;
            prov4 &= (b.p.getX() >= min(a.p.getX(), a.q.getX()));
            prov4 &= (b.p.getY() >= min(a.p.getY(), a.q.getY()));
            prov4 &= (b.p.getX() <= max(a.p.getX(), a.q.getX()));
            prov4 &= (b.p.getY() <= max(a.p.getY(), a.q.getY()));
            return prov1 || prov2 || prov3 || prov4;
        }
    }
    public static Point[] perLine(Sequence aS, Sequence bS) {
        double[] equaA = aS.equaLine();
        double[] equaB = bS.equaLine();
        double[] a = {equaA[0], equaB[0]};
        double[] b = {equaA[1], equaB[1]};
        double[] c = {equaA[2], equaB[2]};
        if (a[0] * b[1] != a[1] * b[0]) {
            Point[] res = new Point[1];
            res[0] = new Point();
            res[0].setY((c[1] * a[0] - c[0] * a[1]) / (b[0] * a[1] - b[1] * a[0]));
            res[0].setX((c[1] * b[0] - c[0] * b[1]) / (a[0] * b[1] - a[1] * b[0]));
            return res;
        } else if (a[0] * c[1] == a[1] * c[0] && b[0] * c[1] == b[1] * c[0]) {
            Point[] res = new Point[2];
            return res;
        } else {
            Point[] res = new Point[0];
            return res;
        }
    }
    public double[] equaLine() {
        double[] res = new double[3];
        res[0] = p.getY() - q.getY();
        res[1] = q.getX() - p.getX();
        res[2] = 0 - p.getX() * res[0] - p.getY() * res[1];
        return res;
    }
}
