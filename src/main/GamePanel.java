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
    private float xDelta = 100, yDelta = 100;

    private BufferedImage[][] animations;
    private MouseInputs mouseInputs;
    private int frames = 0;
    private long lastCheck = 0;
    private BufferedImage img;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;


    public GamePanel() {

        mouseInputs = new MouseInputs(this);

        importImg();
        loadAnimations();

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations(){
        animations = new BufferedImage[9][6];

        for(int j = 0; j < animations.length; j++)
            for(int i = 0; i < animations[j].length; i++)
             animations[j][i]= img.getSubimage(i*64, j*40,64, 40);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                try {
                    is.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
        }
    }


    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);

    }


    public void setDirection(int direction){
    this.playerDir = direction;
    moving = true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    private void updateAnimationTick() {
    aniTick++;
    if(aniTick >= aniSpeed){
        aniTick = 0;
        aniIndex++;
        if(aniIndex >= GetSpriteAmount(playerAction)){
            aniIndex = 0;
        }
    }
    }

    private void setAnimation() {
        if(moving)
            playerAction = RUNNING;
        else playerAction = IDLE;
    }

    private void updatePos() {

        if(moving){
            switch(playerDir){
                case LEFT:
                    xDelta -=5;
                    break;
                case   UP:
                    yDelta -=5;
                    break;
                case RIGHT:
                    xDelta +=5;
                    break;
                case DOWN:
                    yDelta +=5;
                    break;
            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimationTick();

        setAnimation();
        updatePos();

        g.drawImage(animations[playerAction][aniIndex],(int) xDelta,(int) yDelta,256,160, null);
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

