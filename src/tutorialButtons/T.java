package tutorialButtons;

import frame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class T {

    private final GamePanel gp;

    private BufferedImage tOne, tTwo,tThree,tFour;
    private int currentSprite = 1;
    private int spriteCounter = 0;

    public T(GamePanel gp){
        this.gp = gp;
        init();
    }

    public void update(){
        spriteCounter++;
        if(spriteCounter >= 14){
            if(currentSprite == 1) {
                currentSprite = 2;
            }else if(currentSprite == 2){
                currentSprite = 3;
            }else if(currentSprite == 3){
                currentSprite = 4;
            }else{
                currentSprite = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2, int playerX, int playerY){
        BufferedImage current = null;
        switch (currentSprite){
            case 1 -> current = tOne;
            case 2 -> current = tTwo;
            case 3 -> current = tThree;
            case 4 -> current = tFour;
        }
        g2.drawImage(current,playerX, playerY - gp.getTileSize() / 3 * 2, gp.getTileSize(),gp.getTileSize(),null);
    }

    private void init(){
        try{
            tOne = ImageIO.read(getClass().getClassLoader().getResourceAsStream("buttonImage/t-1.png"));
            tTwo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("buttonImage/t-2.png"));
            tThree = ImageIO.read(getClass().getClassLoader().getResourceAsStream("buttonImage/t-3.png"));
            tFour = ImageIO.read(getClass().getClassLoader().getResourceAsStream("buttonImage/t-3.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
