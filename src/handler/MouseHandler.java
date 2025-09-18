package handler;

import frame.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    private final GamePanel gp;

    public MouseHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(gp.getGameState() == gp.getSolvingLabyrinth()){

            int mouseX = e.getX();
            int mouseY = e.getY();

            int startX = gp.getTileSize() * 20 / 2 - 20 * 20;
            int startY = gp.getTileSize() * 20 / 2 - 20 * 20;

            if(mouseX >= startX && mouseX < startX + 20 * 20 &&
            mouseY >= startY && mouseY < startY + 20 * 20){
                int col = (mouseX - startX) / 20;
                int row = (mouseY - startY) / 20;

                gp.setClicked(col,row);
                gp.repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
