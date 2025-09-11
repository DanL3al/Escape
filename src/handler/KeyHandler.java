package handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean up,down,left,right,ePressed;

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            up = true;
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            down = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            left = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            right = true;
        }else if(e.getKeyCode() == KeyEvent.VK_E){
            ePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            up = false;
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            down = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            left = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            right = false;
        }else if(e.getKeyCode() == KeyEvent.VK_E){
            ePressed = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isePressed() {
        return ePressed;
    }
}
