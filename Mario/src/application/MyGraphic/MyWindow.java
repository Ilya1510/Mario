package application.MyGraphic;
/**
 * Created by ДОМ on 22.06.2016.
 */
import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {
    public MyWindow(String name, int w, int h) {
        super(name);
        super.setSize(w, h);
    }
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
    @Override
    public void paint(Graphics g) {

    }
}
