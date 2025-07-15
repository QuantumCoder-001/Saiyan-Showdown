package gaming.sprites;
import gaming.constants.GameConstants;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerLeft extends Sprites implements GameConstants {
    private BufferedImage[] walkImage = new BufferedImage[5];
    private BufferedImage[] damageImage = new BufferedImage[3];
    private BufferedImage[] kickImage = new BufferedImage[7];
    private BufferedImage[] punchImage = new BufferedImage[6];
    public PlayerLeft() throws IOException {
        x = 100;
        y = FLOOR - h;
        speed = 0;
        currentMove = STANDING;
        image = ImageIO.read(PlayerRight.class.getResource(JIN));
        walkImages();
        punchImages();
        kickImages();
        damageImages();
    }
    private void walkImages(){

        walkImage[0] = image.getSubimage(20,185,75,102);
        walkImage[1] = image.getSubimage(101,184,74,105);
        walkImage[2] = image.getSubimage(180,184,79,101);
        walkImage[3] = image.getSubimage(270,185,70,100);
        walkImage[4] = image.getSubimage(346,185,65,100);
    }
    private BufferedImage loadWalkImages(){
        if(imageIndex> walkImage.length-1){
            imageIndex = 0;
            currentMove = STANDING;
            isAttacking = false;
        }
        BufferedImage img = walkImage[imageIndex];
        imageIndex++;
        return img;
    }
    private void kickImages(){
        kickImage[0] = image.getSubimage(22,3673,69,103);
        kickImage[1] = image.getSubimage(469,3666,72,106);
        kickImage[2] = image.getSubimage(21,3527,60,105);
        kickImage[3] = image.getSubimage(89,3523,59,107);
        kickImage[4] = image.getSubimage(157,3495,59,134);
        kickImage[5] = image.getSubimage(226,3520,82,111);
        kickImage[6] = image.getSubimage(320,3519,57,112);
    }
    private BufferedImage loadKickImages(){
        if(imageIndex> kickImage.length-1){
            imageIndex = 0;
            currentMove = STANDING;
            isAttacking = false;
        }
        BufferedImage img = kickImage[imageIndex];
        imageIndex++;
        return img;
    }
    private void punchImages(){
        punchImage[0] = image.getSubimage(22,3060,74,107);
        punchImage[1] = image.getSubimage(100,3057,64,106);
        punchImage[2] = image.getSubimage(176,3057,68,107);
        punchImage[3] = image.getSubimage(253,3064,98,100);
        punchImage[4] = image.getSubimage(362,3066,86,96);
        punchImage[5] = image.getSubimage(538,3061,65,104);
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
    private void damageImages(){
        damageImage[0] = image.getSubimage(241,3818,58,107);
        damageImage[1] = image.getSubimage(167,3818,63,106);
        damageImage[2] = image.getSubimage(96,3814,60,111);
    }
    private BufferedImage loadDamageImages(){
        if(imageIndex> damageImage.length-1){
            imageIndex = 0;
            currentMove = STANDING;
        }
        BufferedImage img = damageImage[imageIndex];
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
            return image.getSubimage(22,623,70,86);
        }
        else if(currentMove == DAMAGED){
            return loadDamageImages();
        }
        else if(currentMove == WALK){
            return loadWalkImages();
        }
        else if(currentMove == KICK){
            return loadKickImages();
        }
        else if(currentMove == PUNCH){
            return loadPunchImages();
        }
        return image.getSubimage(425,43,72,105);
    }
}
