package frame;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    public Menu() {
        setTitle("ESCAPE IA");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(Color.GRAY);

        JLabel tituloLabel = new JLabel("ESCAPE IA");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 80));
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botaoJogar = new JButton("Jogar");
        botaoJogar.setFont(new Font("Arial", Font.BOLD, 30));
        botaoJogar.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoJogar.addActionListener(e -> {
            new Frame().setVisible(true);
            dispose();
        });
        JButton botaoSair = new JButton("Sair");
        botaoSair.setFont(new Font("Arial", Font.BOLD, 30));
        botaoSair.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoSair.addActionListener(e -> {
            dispose();
        });

        painel.add(Box.createVerticalGlue());
        painel.add(tituloLabel);
        painel.add(Box.createRigidArea(new Dimension(0, 140)));
        painel.add(botaoJogar);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));
        painel.add(botaoSair);
        painel.add(Box.createVerticalGlue());

        add(painel);
        setVisible(true);
    }
}