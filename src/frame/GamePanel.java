package frame;

import entity.Player;
import handler.KeyHandler;

import javax.swing.*;
import java.awt.*;

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

    /*Game Logic Variables*/
    private int gameState;
    private final int controllingRobot = 1;
    private final int stealth = 2;

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
        player.update(keyH.isUp(),keyH.isDown(),keyH.isLeft(),keyH.isRight());

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
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
    public int getTileSize() {
        return tileSize;
    }
    public void setControllingRobot(boolean controlling){
        player.setControllingRobot(controlling);
    }

}
