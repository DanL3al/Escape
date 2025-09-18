package room;

import frame.GamePanel;

import java.awt.*;

public class Room {

    private final GamePanel gp;
    private Rectangle serverSolidArea;
    private Rectangle horrorGameSolidArea;
    private Rectangle labyrinthSolidArea;

    public Room(GamePanel gp){
        this.gp = gp;
        labyrinthSolidArea = new Rectangle(gp.getTileSize() * (gp.getMaxCol() - 2) + 10,gp.getTileSize() * 2,gp.getTileSize(),gp.getTileSize());
        horrorGameSolidArea = new Rectangle(gp.getTileSize() * (gp.getMaxCol() - 2) + 10, gp.getTileSize() * 6, gp.getTileSize(), gp.getTileSize());
        serverSolidArea = new Rectangle(gp.getTileSize() * (gp.getMaxCol() - 2) + 10,gp.getTileSize() * 10,gp.getTileSize(),gp.getTileSize());

    }

    public Rectangle getServerSolidArea() {
        return serverSolidArea;
    }

    public Rectangle getHorrorGameSolidArea() {
        return horrorGameSolidArea;
    }

    public Rectangle getLabyrinthSolidArea() {
        return labyrinthSolidArea;
    }
}
