package handler;

import frame.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private final GamePanel gp;
    private boolean up,down,left,right;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        /*Keys Logic For Controlling Robot*/
        if(gp.getGameState() == gp.getControllingRobot()){
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
            }
            else if(e.getKeyCode() == KeyEvent.VK_E){
                gp.setControllingRobot(false);
                gp.setGameState(gp.getStealth());
            }
            else if(e.getKeyCode() == KeyEvent.VK_T){
                if(gp.isCollidingWithDoor()){
                    gp.setGameState(gp.getSolvingPuzzle());
                }
            }
        }
        /*Keys logic for stealth mode*/
        else if(gp.getGameState() == gp.getStealth()){
            if(e.getKeyCode() == KeyEvent.VK_E){
                gp.setGameState(gp.getControllingRobot());
                gp.setControllingRobot(true);
            }
        }

        /*Keys logic for solving puzzle*/
        else if(gp.getGameState() == gp.getSolvingPuzzle()){
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                gp.setShieldUp(true);
            }else if(e.getKeyCode() == KeyEvent.VK_W){
                if(!gp.getShieldUp() && gp.getCookiesRemaining() > 0 && gp.robotCanThrow()){
                    gp.createCookie();
                    gp.setRobotCookiesRemaining();
                }
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        /*Keys Logic For Controlling Robot*/
        if(gp.getGameState() == gp.getControllingRobot()){
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
            }
        }
        /*Keys logic for solving puzzle*/
        else if(gp.getGameState() == gp.getSolvingPuzzle()){
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                gp.setShieldUp(false);
            }
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


}
