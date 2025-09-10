package frame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel(int width, int height){
        this.setBounds(0,0,width,height);
        this.setBackground(Color.green);
    }

    public void startGameThread(){}

}
