package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import static Utils.Constants.PlayerConstants.*;
import static Utils.Constants.Directions.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game) {
        this.game = game;

        mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);

    }

    public void updateGame() {
        game.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame(){
        return game;
    }

}








//   random = new Random();
//    private float xDir = 0.001f, yDir = 0.001f;
//    private Color color = new Color(220,91,21);
//  private Random random;
//    private void updateRectangle(){
//         xDelta+= xDir;
//        if(xDelta > 400 || xDelta < 0){
//        xDir *= -1;
//        color = getRndColor();
//        }
//         yDelta+= yDir;
//        if(yDelta > 400 || yDelta < 0){
//        yDir *= -1;
//        color = getRndColor();
//        }
//    }


 /*   private Color getRndColor(){
    int r = random.nextInt(255);
    int g = random.nextInt(255);
    int b = random.nextInt(255);

    return new Color(r,g,b);
    }

}*/

