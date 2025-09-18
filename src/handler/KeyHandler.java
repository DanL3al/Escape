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
                resetCoordinates();
            }
            else if(e.getKeyCode() == KeyEvent.VK_T){
                if(gp.collidingWithLabyrinth()){
                    if(!gp.labyrinthFinished()){
                        gp.setGameState(gp.getSolvingLabyrinth());
                    }
                }else if(gp.collidingWithHorrorGame()){
                    if(!gp.isHorrorGameWon()){
                        gp.setGameState(gp.getSolvingHorrorPuzzle());
                    }
                }else if(gp.collidingWithServerGame()){
                    if(!gp.serverPuzzleFinished()){
                        gp.setGameState(gp.getShowingPuzzleObjective());
                    }
                }
            }
        }
        /*Keys logic for stealth mode*/
        else if(gp.getGameState() == gp.getStealth()){
            if(e.getKeyCode() == KeyEvent.VK_E){
                resetCoordinates();
                gp.setGameState(gp.getControllingRobot());
                gp.setControllingRobot(true);
                if(!gp.isSwitchedForTheFirstTime()){
                    gp.setSwitchedForTheFirstTime(true);
                }

            }
        }

        /*Keys logic for start of the game*/
        else if(gp.getGameState() == gp.getGameStarted()){
            if(e.getKeyCode() == KeyEvent.VK_T){
                if(gp.isShowedFirstDialogue()){
                    gp.setGameState(gp.getStealth());
                    gp.setCanDraw();
                }
            }
        }

        /*Keys logic for dialogue mode*/
        else if(gp.getGameState() == gp.getInteracting()){
            if(e.getKeyCode() == KeyEvent.VK_T){
                gp.setGameState(gp.getControllingRobot());
            }
        }

        /*Keys logic for solving server puzzle*/
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

        /*Keys Logic for solving Horror Puzzle*/
        else if(gp.getGameState() == gp.getSolvingHorrorPuzzle()){
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                gp.setFlashlight(true);
            }if(e.getKeyCode() == KeyEvent.VK_W){
                gp.setWalking(true);
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

        else if(gp.getGameState() == gp.getSolvingHorrorPuzzle()){
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                gp.setFlashlight(false);
            }
            if(e.getKeyCode() == KeyEvent.VK_W){
                gp.setWalking(false);
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

    public void resetCoordinates(){
        this.right = false;
        this.left = false;
        this.up = false;
        this.down = false;
    }


}
