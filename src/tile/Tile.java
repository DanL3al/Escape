package tile;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage image;
    private boolean collision = false;
    private boolean isDoor = false;
    private boolean isEscapeDoor = false;
    private boolean open = false;

    public Tile(boolean collision, boolean isDoor, boolean isEscapeDoor){
        setTileInfo(collision,isDoor,isEscapeDoor);
    }

    public void setTileInfo(boolean collision, boolean isDoor, boolean isEscapeDoor) {
        this.collision = collision;
        if(isDoor){
            this.isDoor = true;
        }
        if(isEscapeDoor){
            this.isEscapeDoor = true;
        }
        if(isDoor || isEscapeDoor){
            this.collision = true;
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean isCollision() {
        return collision;
    }

    public boolean isDoor() {
        return isDoor;
    }

    public boolean isEscapeDoor() {
        return isEscapeDoor;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
