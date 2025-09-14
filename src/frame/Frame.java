package frame;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private final GamePanel gp;

    public Frame(){
        this.setTitle("Escape");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(576,576));
        this.setLocationRelativeTo(null);
        this.gp = new GamePanel();
        this.add(gp);
        this.setVisible(true);
        gp.startGameThread();
    }
}
