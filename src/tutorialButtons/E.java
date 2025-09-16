package tutorialButtons;

import frame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class E {

    private final GamePanel gp;

    private BufferedImage eOne, eTwo,eThree,eFour;
    private int currentSprite = 1;
    private int spriteCounter = 0;

    public E(GamePanel gp){
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
            case 1 -> current = eOne;
            case 2 -> current = eTwo;
            case 3 -> current = eThree;
            case 4 -> current = eFour;
        }
        g2.drawImage(current,playerX, playerY - gp.getTileSize() / 3 * 2, gp.getTileSize(),gp.getTileSize(),null);
    }

    private void init(){
        try{
            eOne = ImageIO.read(getClass().getClassLoader().getResourceAsStream("buttonImage/e-1.png"));
            eTwo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("buttonImage/e-2.png"));
            eThree = ImageIO.read(getClass().getClassLoader().getResourceAsStream("buttonImage/e-3.png"));
            eFour = ImageIO.read(getClass().getClassLoader().getResourceAsStream("buttonImage/e-4.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
