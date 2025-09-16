package entity;

import frame.GamePanel;
import tutorialButtons.E;

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
    private BufferedImage deactivatedUp,deactivatedDown, deactivatedLeft,deactivatedRight;
    private BufferedImage computerOn, computerOff;
    //if controlling player, this variable will save the pc deactivated and draw it, else it will draw the robot int the
    //ground
    private BufferedImage currentDeactivated;

    /*GAME LOGIC VARIABLES*/
    private boolean controllingRobot = false;
    private final E E;

    /*COLLISION VARIABLES*/
    private Rectangle solidArea;
    private boolean collisionWithDoor = false;
    private int solidAreaDefaultX, solidAreaDefaultY;
    private int solidAreaWidth, solidAreaHeight;



    public Player(GamePanel gp){
        setImages();
        this.x = 200;
        this.y = 300;
        this.speed = 3;
        this.direction = "down";
        this.gp = gp;
        setSolidArea();
        E = new E(gp);
    }

    public void draw(Graphics2D g2){

        if(!gp.isSwitchedForTheFirstTime() && gp.isCanDraw()){
            E.draw(g2,x,y);
        }

        /*Logic for drawing the robot's movement*/
        if(controllingRobot){
            currentDeactivated = computerOff;
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
            g2.drawImage(currentDeactivated,0,0,gp.getTileSize(),gp.getTileSize(),null);
            g2.drawImage(image,x,y,gp.getTileSize(),gp.getTileSize(),null);
        }
        /*Logic for drawing the player and pc while player not using the robot*/
        //TODO: Change it in the future, each room will have a different object for it to go stealth
        else{
            g2.drawImage(computerOn,0,0,gp.getTileSize(),gp.getTileSize(),null);
            g2.drawImage(currentDeactivated,x,y,gp.getTileSize(),gp.getTileSize(),null);
        }
    }

    public void update(boolean up, boolean down, boolean left, boolean right){

        if(!gp.isSwitchedForTheFirstTime() && gp.isCanDraw()){
            E.update();
        }

        collisionWithDoor = false;
        checkCollision();


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
        else{
            switch (direction){
                case "up" -> currentDeactivated = deactivatedUp;
                case "down" -> currentDeactivated = deactivatedDown;
                case "left" -> currentDeactivated = deactivatedLeft;
                case "right" -> currentDeactivated = deactivatedRight;
            }
        }
    }

    private void checkCollision(){
        this.solidArea.x = x + gp.getTileSize() / 4;
        this.solidArea.y = y + gp.getTileSize() / 4;

        if(solidArea.intersects(gp.getRoomSolidArea())){
            collisionWithDoor = true;
        }

        this.solidArea.x = solidAreaDefaultX;
        this.solidArea.y = solidAreaDefaultY;
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
            deactivatedUp = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/deactivated-up.png"));
            deactivatedDown = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/deactivated-left.png"));
            deactivatedLeft = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/deactivated-left.png"));
            deactivatedRight = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/deactivated-right.png"));
            computerOn = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/computer-on.png"));
            computerOff = ImageIO.read(getClass().getClassLoader().getResourceAsStream("playerAssets/computer-off.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setSolidArea(){
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
        solidAreaWidth = 20;
        solidAreaHeight = 20;
        this.solidArea = new Rectangle(solidAreaDefaultX,solidAreaDefaultY,solidAreaWidth,solidAreaHeight);
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

    public boolean isCollisionWithDoor() {
        return collisionWithDoor;
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
