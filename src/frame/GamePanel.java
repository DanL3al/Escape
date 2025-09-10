package frame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    private Thread thread;

    public GamePanel(int width, int height){
        this.setBounds(0,0,width,height);
        this.setBackground(Color.green);
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

    public void update(){}

    public void paintComponent(Graphics g){}
}
