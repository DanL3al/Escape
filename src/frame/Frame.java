package frame;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private final GamePanel gp;

    public Frame(){
        this.setTitle("Escape");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //this.setPreferredSize(new Dimension(576,576));
        this.gp = new GamePanel();
        this.add(gp);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        gp.startGameThread();
    }
}
