package room;

import java.awt.*;

public class Room {

    private int roomNumber;
    private Color color; // will change to sprite in the future
    private final Door door = new Door();

    public Room(int roomNumber, Color color) {
        this.roomNumber = roomNumber;
        this.color = color;
    }

    public void draw(Graphics2D g2, int panelWidth, int panelHeight){
        g2.setColor(this.color);
        g2.fillRect(0,0,panelWidth,panelHeight);
        door.draw(g2);
    }

    public void update(){
        this.door.update();
    }

    public Door getDoor() {
        return door;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Color getColor() {
        return color;
    }

}
