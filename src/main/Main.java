package main;

import frame.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame menuFrame = new JFrame("Menu");
        menuFrame.setSize(600, 600);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setLayout(new BorderLayout());

        JLabel title = new JLabel("ESCAPE", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        menuFrame.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.setPreferredSize(new Dimension(120, 50));
        centerPanel.add(startButton);
        menuFrame.add(centerPanel, BorderLayout.CENTER);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                abrirJogo();
            }
        });

        menuFrame.setVisible(true);
    }

    private static void abrirJogo() {
        Frame frame = new Frame();
    }
}
