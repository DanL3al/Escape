package entity;

import java.awt.*;

public class Player {

    private int x;
    private int y;
    private final int speed;
    private String direction;

    public Player(){
        this.x = 200;
        this.y = 300;
        this.speed = 3;
        this.direction = "down";
    }

    public void update(boolean up, boolean down, boolean left, boolean right){
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

    public void draw(Graphics2D g2){
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
