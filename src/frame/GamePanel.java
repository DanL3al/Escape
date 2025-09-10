package frame;

import handler.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    private Thread thread;
    private final KeyHandler keyH = new KeyHandler();

    public GamePanel(int width, int height){
        this.setBounds(0,0,width,height);
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
        if(keyH.isDown() || keyH.isLeft() || keyH.isRight() || keyH.isUp()){
            System.out.println("FUNCIONANDO");
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.dispose();
    }
}
