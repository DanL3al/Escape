package frame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Frame extends JFrame {

    private JLayeredPane layeredPane;
    private JPanel optionsPanel;
    private GamePanel gp;

    public Frame() {
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layeredPane = new JLayeredPane();

        layeredPane.setBounds(0, 0, getWidth(), getHeight());
        setContentPane(layeredPane);
        setupOptionsPanel();
        setupGlobalKeyBindings();
        sala1();
    }

    private void setupGlobalKeyBindings() {
        InputMap inputMap = layeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = layeredPane.getActionMap();

        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        inputMap.put(escapeKeyStroke, "toggleOptions");

        actionMap.put("toggleOptions", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionsPanel.setVisible(!optionsPanel.isVisible());
            }
        });
    }

    private void setupOptionsPanel() {
        TitledBorder bordaComTitulo = BorderFactory.createTitledBorder("OPÇÕES");
        bordaComTitulo.setTitleFont(new Font("Arial", Font.BOLD, 20));
        bordaComTitulo.setTitleJustification(TitledBorder.CENTER);
        bordaComTitulo.setTitleColor(Color.WHITE);

        optionsPanel = new JPanel();
        optionsPanel.setBackground(new Color(0, 0, 0, 150));
        optionsPanel.setLayout(new GridBagLayout());
        optionsPanel.setBorder(bordaComTitulo);

        int panelWidth = 400;
        int panelHeight = 300;
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        optionsPanel.setBounds((screenWidth - panelWidth) / 2, (screenHeight - panelHeight) / 2, panelWidth, panelHeight);

        JButton botaoSair = new JButton("SAIR DO JOGO");
        botaoSair.setFont(new Font("Tahoma", Font.BOLD, 30));
        botaoSair.addActionListener(e -> System.exit(0));
        optionsPanel.add(botaoSair);
        optionsPanel.setVisible(false);
        layeredPane.add(optionsPanel, JLayeredPane.POPUP_LAYER);
    }

    private void sala1(){
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int panelWidth = 575;
        int panelHeight = 575;
        int x = (screenWidth - panelWidth) / 2;
        int y = (screenHeight - panelHeight) / 2;

        gp = new GamePanel();
        gp.setLayout(new GridBagLayout());
        gp.setBounds(x, y, panelWidth, panelHeight);
        gp.setVisible(true);
        gp.startGameThread();
        layeredPane.add(gp);
    }


}