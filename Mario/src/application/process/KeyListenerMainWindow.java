package application.process;

import java.awt.*;
import java.awt.event.*;
import application.Application;

/**
 * Created by ДОМ on 22.06.2016.
 */
public class KeyListenerMainWindow implements KeyListener{
    public boolean rightPressed = false;
    public boolean leftPressed = false;
    public boolean upPressed = false;
    public boolean downPressed = false;
    public KeyListenerMainWindow() {}
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getExtendedKeyCode());
        if (e.getKeyCode() == 39) { // right hero
            rightPressed = true;
        }
        if (e.getKeyCode() == 37) { // left hero
            leftPressed = true;
        }
        if (e.getKeyCode() == 38) { // up hero
            upPressed = true;
        }
        if (e.getKeyCode() == 40) { // down hero
            downPressed = true;
        }
    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 39) { // right stop
            rightPressed = false;
        }
        if (e.getKeyCode() == 37) { // left stop
            leftPressed = false;
        }
        if (e.getKeyCode() == 38) { // up stop
            upPressed = false;
        }
        if (e.getKeyCode() == 40) { // down stop
            downPressed = false;
        }
    }
    public void keyTyped(KeyEvent e) {}
}
