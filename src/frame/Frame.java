package frame;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private final int width = 600;
    private final int height = 600;

    public Frame(){
        this.setTitle("Escape");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(new Dimension(width, height));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
