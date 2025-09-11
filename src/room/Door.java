package room;

import java.awt.*;

public class Door {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Rectangle solidArea;
    private final Handle handle;
    private boolean open;

    public Door(){
        this.handle = new Handle();
        this.open = handle.getPuzzle().isStatus();
        this.height = 40;
        this.width = 40;
        this.x = 540;
        this.y = 520;
        this.solidArea = new Rectangle(x,y,width,height);
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.darkGray);
        g2.fillRect(x,y,width,height);
        g2.setColor(Color.YELLOW);
        g2.drawRect(solidArea.x, solidArea.y,solidArea.width, solidArea.height);
        handle.draw(g2);
    }

    public void update(){
        this.open = handle.getPuzzle().isStatus();
        if(open){
            System.out.println("ABRIU A PORTA");
        }
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }



}
