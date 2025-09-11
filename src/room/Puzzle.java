package room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Puzzle {

    JFrame puzzleFrame;
    JButton button;

    public Puzzle(){
        puzzleFrame = new JFrame("Puzzle");
        puzzleFrame.setLayout(null);
        puzzleFrame.setSize(300,300);
        puzzleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button = new JButton();
        button.setBounds(50,50,180,100);

        button.setText("Resolver o puzzle");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == button){
                    puzzleFrame.dispose();
                }
            }
        });
        puzzleFrame.add(button);

        int screenWidth  = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

        int x = (screenWidth - puzzleFrame.getWidth()) / 2;
        int y = (screenHeight - puzzleFrame.getHeight()) / 2;

        x -= 300;

        puzzleFrame.setLocation(x, y);

        puzzleFrame.setVisible(true);
        //exclude();
    }

    /*private void exclude(){
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puzzleFrame.dispose();
            }
        });
        timer.start();
    }*/
}
