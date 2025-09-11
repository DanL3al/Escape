package entity;

import frame.GamePanel;
import room.Door;
import room.Room;

import java.awt.*;

public class Player {

    private GamePanel gp;
    private int x;
    private int y;
    private final int speed;
    private String direction;
    private Rectangle solidArea;
    private boolean collidingWithDoor;

    public Player(GamePanel gp){
        this.x = 200;
        this.y = 300;
        this.speed = 3;
        this.direction = "down";
        this.solidArea = new Rectangle(x,y,32,32);
        this.gp = gp;
    }

    public void update(boolean up, boolean down, boolean left, boolean right){
        collidingWithDoor = false;
        checkCollision();


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
        }

    }

    private void checkCollision(){
        this.solidArea.x = x;
        this.solidArea.y = y;

        Room currentRoom = gp.getCurrentRoom();
        Door door = currentRoom.getDoor();

        if(this.solidArea.intersects(door.getSolidArea())){
            collidingWithDoor = true;
        }

    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.pink);
        g2.fillRect(x,y,32,32);
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
}
