package room;

import frame.GamePanel;

import java.awt.*;

public class Room {


    //character hover over a place,and it opens a puzzle
    //each room has it's unique puzzle
    private final GamePanel gp;
    private Rectangle solidArea;

    public Room(GamePanel gp){
        this.gp = gp;
        solidArea = new Rectangle(gp.getTileSize() * (gp.getMaxCol() - 2),gp.getTileSize() * (gp.getMaxRow() - 2),gp.getTileSize(),gp.getTileSize());
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.fillRect(solidArea.x,solidArea.y, solidArea.width, solidArea.height);
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }
}
