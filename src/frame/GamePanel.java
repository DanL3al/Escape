package frame;

import puzzle.PuzzleOne;
import entity.Player;
import handler.KeyHandler;
import puzzle.UI;
import room.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{

    /*Panel Dimension's Variables*/
    private final int originalTileSize = 24;
    private final int maxCol = 12;
    private final int maxRow = 12;
    private final int scale = 2;
    private final int tileSize = originalTileSize * scale;
    private final int screenWidth = tileSize * maxCol;
    private final int screenHeight = tileSize * maxRow;

    /*Object Variables*/
    private Thread thread;
    private final KeyHandler keyH = new KeyHandler(this);
    private final Player player = new Player(this);
    private final Room room = new Room(this);
    private final PuzzleOne puzzleOne = new PuzzleOne(this);
    private final UI ui = new UI(this);

    /*Game Logic Variables*/
    private int gameState;
    private final int controllingRobot = 1;
    private final int stealth = 2;
    private final int solvingPuzzle = 3;



    public GamePanel(){
        this.gameState = stealth;
        this.setBounds(0,0,screenWidth,screenHeight);
        this.setBackground(Color.green);
        this.setFocusable(true);
        this.addKeyListener(this.keyH);
    }
    public void startGameThread(){
        this.thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        int FPS = 60;
        double drawInterval = 1000000000f/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (thread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update(){
        if(gameState == solvingPuzzle){
            puzzleOne.update();
            ui.update();
        }else{
            player.update(keyH.isUp(),keyH.isDown(),keyH.isLeft(),keyH.isRight());
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(gameState == solvingPuzzle){
            puzzleOne.draw(g2);
            ui.draw(g2);
        }else{
            room.draw(g2);
            player.draw(g2);
        }
        g2.dispose();
    }


    public Rectangle getRoomSolidArea(){
        return room.getSolidArea();
    }

    public boolean isCollidingWithDoor(){
        return player.isCollisionWithDoor();
    }
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
    public int getGameState() {
        return gameState;
    }
    public int getControllingRobot() {
        return controllingRobot;
    }
    public int getStealth() {
        return stealth;
    }
    public int getSolvingPuzzle() {
        return solvingPuzzle;
    }
    public int getTileSize() {
        return tileSize;
    }

    public void setControllingRobot(boolean controlling){
        player.setControllingRobot(controlling);
    }

    public int getMaxCol() {
        return maxCol;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public void setShieldUp(boolean shieldUp){
        puzzleOne.setShield(shieldUp);
    }

    public boolean getShieldUp(){
        return puzzleOne.isShieldUp();
    }

    public void createCookie(){
        puzzleOne.createCookie();
    }

    public Rectangle getRobotShieldArea(){
        return puzzleOne.robotShieldArea();
    }

    public Rectangle getRobotArea(){
        return puzzleOne.robotArea();
    }

    public int getServerX(){
        return puzzleOne.getServerX();
    }
    public int getServerY(){
        return puzzleOne.getServerY();
    }

    public BufferedImage getCookieImage(){
        return puzzleOne.getCookieImage();
    }

    public BufferedImage getShieldImage(){
        return puzzleOne.getShieldImage();
    }

    public int getCookiesRemaining(){
        return puzzleOne.getCookiesRemaining();
    }

    public boolean robotCanThrow(){
        return puzzleOne.getRobotCanThrow();
    }

    public void setRobotCookiesRemaining(){
        puzzleOne.setRobotCookiesRemaining();
    }

    public void setServerHeat(){
        puzzleOne.setServerHeat();
    }


    public void setHealth(){
        puzzleOne.setHealth();
    }

    public int getHealth(){
        return puzzleOne.getHealth();
    }

    public BufferedImage getHeartImage(){
        return puzzleOne.getHeart();
    }

    public BufferedImage getCurrentHeatImage(){
        return puzzleOne.getCurrentHeatImage();
    }

}
