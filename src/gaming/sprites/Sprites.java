package gaming.sprites;

import gaming.constants.GameConstants;
import gaming.constants.PlayerConstants;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Sprites implements GameConstants, PlayerConstants {
    protected int x;
    protected int y;
    protected int h;
    protected int w;
    protected int speed;
    protected BufferedImage image;
    protected int currentMove;
    public  abstract BufferedImage defaultImage();
    protected int imageIndex;
    protected int force;
    protected  boolean isJump;
    protected boolean isCollide;
    protected boolean isAttacking;
    protected  int health;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean getIsAttacking() {
        return isAttacking;
    }

    public void setIsAttacking(boolean isAttacking) {
        this.isAttacking = isAttacking;
    }
    public boolean isCollide() {
        return isCollide;
    }
    public void setCollide(boolean collide) {
        isCollide = collide;
    }
    public Sprites(){
        h = 230;
        w = 180;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setCurrentMove(int currentMove) {
        this.currentMove = currentMove;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void move(){
        x += speed;
    }
    public void PrintPlayer(Graphics pen){
        pen.drawImage(defaultImage(),x,y,w,h,null);
    }
}
