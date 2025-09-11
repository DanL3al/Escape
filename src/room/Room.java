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
