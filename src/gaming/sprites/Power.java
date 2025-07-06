package gaming.sprites;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Power extends Sprites {
    private String playerName;
    public Power(int x, String playerName){
        y = 50;
        h = 35;
        health = w = MAX_HEALTH;
        this.x = x;
        this.playerName = playerName;
    }
    public void setHealth(){
        health = health - (int)(MAX_HEALTH * 0.03);
    }
    @Override
    public BufferedImage defaultImage() {
        return null;
    }
    public void printBox(Graphics pen){
        pen.setColor(Color.RED);
        pen.fillRect(x,y,w,h);
        pen.setColor(Color.GREEN);
        pen.fillRect(x,y,health,h);
        pen.setColor(Color.WHITE);
        pen.setFont(new Font("times",Font.BOLD,30));
        pen.drawString(playerName,x, 120);
    }
}
