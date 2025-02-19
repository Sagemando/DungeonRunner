package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Utils.Constants.Directions.*;
import static Utils.Constants.Directions.DOWN;
import static Utils.Constants.PlayerConstants.*;

public class Player extends Entity{
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;

    private BufferedImage[][] animations;
    public Player(float x, float y){
        super(x,y);
        loadAnimations();
    }

        public void update(){
            updatePos();
            updateAnimationTick();
            setAnimation();
        }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex],(int) x,(int) y,256,160, null);

    }


    public void setDirection(int direction){
        this.playerDir = direction;
        moving = true;
    }


    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= getSpriteAmount(playerAction)){
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
        moving = false;
        if(left && !right){
            x -= playerSpeed;
            moving = true;
        }else if(right && !left){
            x += playerSpeed;
            moving = true;
        }else if(up && !down){
            y -= playerSpeed;
            moving = true;
        }else if(down && !up){
            y += playerSpeed;
            moving = true;
        }
    }

    private void loadAnimations(){
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");

        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[9][6];
            for(int j = 0; j < animations.length; j++)
                for(int i = 0; i < animations[j].length; i++)
                    animations[j][i]= img.getSubimage(i*64, j*40,64, 40);
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

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
