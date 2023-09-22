package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {
    public boolean upPressed,downPressed,rightPressed,leftPressed,upperRightCross,upperLeftCross,downRightCross,downLeftCross;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e,KeyEvent d) {
        int code = e.getKeyCode();
        int code2 = d.getKeyCode();

        if (code == KeyEvent.VK_W && code2 == KeyEvent.VK_D){
            upperRightCross = true;
        } else if (code == KeyEvent.VK_W){
            upPressed = true;
        }

        if (code == KeyEvent.VK_A){
            leftPressed = true;
        }

        if (code == KeyEvent.VK_D){
            rightPressed = true;
        }

        if (code == KeyEvent.VK_S){
            downPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e,KeyEvent d) {
        int code = e.getKeyCode();
        int code2 = d.getKeyCode();

        if (code == KeyEvent.VK_W && code2 == KeyEvent.VK_D){
            upperRightCross = false;
        } else if (code == KeyEvent.VK_W){
            upPressed = false;
        }

        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }

        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
    }
}
