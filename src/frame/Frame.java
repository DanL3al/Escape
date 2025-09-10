package frame;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private final int width = 600;
    private final int height = 600;
    private final GamePanel gp;

    public Frame(){
        this.setTitle("Escape");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(new Dimension(width, height));
        this.setLocationRelativeTo(null);
        this.gp = new GamePanel(width,height);
        this.add(gp);
        this.setVisible(true);
        gp.startGameThread();
    }
}
