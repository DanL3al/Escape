package entity;

import frame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {


    private GamePanel gp;

    /*POSITION VARIABLES*/
    private int x;
    private int y;
    private final int speed;
    private String direction;

    /*IMAGE VARIABLES*/
    private int spriteCounter;
    private int currentSprite = 1;
    private BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    private BufferedImage deactivated;

    /*GAME LOGIC VARIABLES*/
    private boolean controllingRobot = false;


    public Player(GamePanel gp){
        setImages();
        this.x = 200;
        this.y = 300;
        this.speed = 3;
        this.direction = "down";
        this.gp = gp;
    }

    public void draw(Graphics2D g2){

        /*Logic for drawing the robot's movement*/
        if(controllingRobot){
            BufferedImage image = null;

            switch (direction){
                case "up":
                    if(currentSprite == 1){
                        image = up1;
                    }else{
                        image = up2;
                    }
                    break;
                case "down":
                    if(currentSprite == 1){
                        image = down1;
                    }else{
                        image = down2;
                    }
                    break;
                case "left":
                    if(currentSprite == 1){
                        image = left1;
                    }else{
                        image = left2;
                    }
                    break;
                case "right":
                    if(currentSprite == 1){
                        image = right1;
                    }else{
                        image = right2;
                    }
                    break;
            }
            g2.fillRect(0,0,gp.getTileSize(),gp.getTileSize());
            g2.drawImage(image,x,y,gp.getTileSize(),gp.getTileSize(),null);
        }
        /*Logic for drawing the player and pc while player not using the robot*/
        //TODO: IMPLEMENT IT WHEN THE SPRITE IS DONE
        else{
            g2.fillRect(0,0,gp.getTileSize(),gp.getTileSize());
            g2.setColor(Color.YELLOW);
            g2.fillRect(x,y,gp.getTileSize(),gp.getTileSize());
        }
    }

    public void update(boolean up, boolean down, boolean left, boolean right){


        if(controllingRobot){
            if(up){
                this.direction = "up";
            }else if(down){
                this.direction = "down";
            }else if(left){
                this.direction = "left";
            }else if(right){
                this.direction = "right";
            }

            if(up || down || left || right){
                switch (direction){
                    case "up" -> this.y -= speed;
                    case "down" -> this.y += speed;
                    case "left" -> this.x -= speed;
                    case "right" -> this.x += speed;
                }

                spriteCounter++;
                if(spriteCounter >= 12){
                    if(currentSprite == 1){
                        currentSprite = 2;
                    }else{
                        currentSprite = 1;
                    }
                    spriteCounter = 0;
                }
            }
        }


    }


    private void setImages(){
        try{
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/up-1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/up-2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/down-1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/down-2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/left-1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/left-2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/right-1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/right-2.png"));
            deactivated = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/deactivated.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setControllingRobot(boolean controllingRobot) {
        this.controllingRobot = controllingRobot;
    }

    public boolean isControllingRobot() {
        return controllingRobot;
    }
}
