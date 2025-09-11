package room;

import java.awt.*;
import java.util.ArrayList;

public class RoomManager {

    Room[] rooms;
    int currentRoomNumber;

    public RoomManager(int roomNumber){
        this.currentRoomNumber = roomNumber - 1;
        rooms = initRooms();
    }

    public void draw(Graphics2D g2, int panelWidth, int panelHeight){
        Room currentRoom = rooms[this.currentRoomNumber];
        g2.setColor(currentRoom.getColor());
        g2.fillRect(0,0,panelWidth,panelHeight);
        currentRoom.getDoor().draw(g2);
    }

    private Room[] initRooms(){
        Room[] temp = new Room[3];
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.BLUE);colors.add(Color.RED);colors.add(Color.GREEN);
        for (int i = 0; i < temp.length; i++) {
            Color color = colors.get((int) (Math.random() * colors.size()));
            temp[i] = new Room(i+1, color);
            colors.remove(temp[i].getColor());
        }
        return temp;
    }

    public Room getCurrentRoom(){
        return rooms[currentRoomNumber];
    }

}
