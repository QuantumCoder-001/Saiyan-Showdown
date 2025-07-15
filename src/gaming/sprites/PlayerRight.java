package gaming.sprites;

import gaming.constants.GameConstants;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerRight extends Sprites implements GameConstants {
    private BufferedImage[] walkImage = new BufferedImage[5];
    private BufferedImage[] punchImage = new BufferedImage[4];
    private BufferedImage[] specialMoveImage = new BufferedImage[6];
    private BufferedImage[] damageImage = new BufferedImage[2];

    public PlayerRight() throws IOException {
        x = 1000;
        y = FLOOR-h;
        speed = 0;
        currentMove = STANDING;
        image = ImageIO.read(PlayerRight.class.getResource(GOKU));
        WalkImages();
        specialMoveImages();
        punchImages();
        damageImages();
    }
    private void damageImages(){
        damageImage[0] = image.getSubimage(424,596,91,143);
        damageImage[1] = image.getSubimage(1253,629,96,130);
    }
    private BufferedImage loadDamagedImages(){
        if(imageIndex> damageImage.length-1){
            imageIndex = 0;
            currentMove = STANDING;
        }
        BufferedImage img = damageImage[imageIndex];
        imageIndex++;
        return img;
    }
    private void WalkImages(){
        walkImage[0] = image.getSubimage(1296,165,96,111);
        walkImage[1] = image.getSubimage(1187,166,96,105);
        walkImage[2] = image.getSubimage(1080,166,103,97);
        walkImage[3] = image.getSubimage(928,167,147,84);
        walkImage[4] = image.getSubimage(642,164,79,125);
    }
    private BufferedImage loadWalkImages(){
        if(imageIndex> walkImage.length-1){
            imageIndex = 0;
            currentMove = STANDING;
        }
        BufferedImage img = walkImage[imageIndex];
        imageIndex++;
        return img;
    }
    private void punchImages(){
        punchImage[1] = image.getSubimage(1488,1098,100,122);
        punchImage[0] = image.getSubimage(901,1267,107,122);
        punchImage[2] = image.getSubimage(755,1269,141,125);
        punchImage[3] = image.getSubimage(1349,1100, 132,126);

    }
    private BufferedImage loadPunchImages(){
        if(imageIndex> punchImage.length-1){
            imageIndex = 0;
            currentMove = STANDING;
            isAttacking = false;
        }
        BufferedImage img = punchImage[imageIndex];
        imageIndex++;
        return img;
    }
    private void specialMoveImages(){
        specialMoveImage[0] = image.getSubimage(1239,1254,69,156);
        specialMoveImage[1] = image.getSubimage(945,769,79,152);
        specialMoveImage[2] = image.getSubimage(1480,1588,105,113);
        specialMoveImage[3] = image.getSubimage(1360,1593,101,108);
        specialMoveImage[4] = image.getSubimage(1232,1585,102,118);
        specialMoveImage[5] = image.getSubimage(1126,1604,94,101);
    }
    private  BufferedImage loadSpecialMoveImages(){
        if(imageIndex> specialMoveImage.length-1){
            imageIndex = 0;
            currentMove = STANDING;
            isAttacking = false;
        }
        BufferedImage img = specialMoveImage[imageIndex];
        imageIndex++;
        return img;
    }
    public void jump(){
        if(!isJump){
            force = DEFAULT_FORCE;
            y += force;
            isJump = true;
        }
    }
    public boolean isjump(){
        return isJump;
    }
    public void fall(){
        if(y>= FLOOR-h){
            isJump = false;
            return;
        }
        force += GRAVITY;
        y += force;
    }
    @Override
    public BufferedImage defaultImage() {
        if(isJump){
            return image.getSubimage(639,167,80,121);
        }
        else if(currentMove == DAMAGED){
            return loadDamagedImages();
        }
        else if(currentMove == WALK){
            return loadWalkImages();
        }
        else if(currentMove == KICK){
            return loadSpecialMoveImages();
        }
        else if(currentMove == PUNCH){
            return loadPunchImages();
        }

        return image.getSubimage(444,770,65,158);
    }
}
