package room;

import java.awt.*;

public class Handle {
    private final Rectangle solidArea;
    private final Puzzle puzzle;

    public Handle(){
        this.puzzle = new Puzzle();
        this.solidArea = new Rectangle(540,400,30,30);
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.black);
        g2.drawRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
        g2.setColor(Color.CYAN);
        g2.fillRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }
}
