package main;

import javax.swing.*;

public class GameWindow {
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel){
    jframe = new JFrame();


    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.add(gamePanel);
    jframe.setVisible(true);
    jframe.setResizable(false);
    jframe.pack();
    jframe.setLocationRelativeTo(null);

    }
}
