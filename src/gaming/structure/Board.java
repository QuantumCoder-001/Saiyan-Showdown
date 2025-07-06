package gaming.structure;
import gaming.constants.*;
import gaming.sprites.PlayerLeft;
import gaming.sprites.PlayerRight;
import gaming.sprites.Power;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
public class Board extends JPanel implements GameConstants, PlayerConstants {
    BufferedImage imagebg;
    private PlayerRight playerR;
    private PlayerLeft playerL;
    // ADDED: To keep track of currently pressed keys
    private Set<Integer> pressedKeys = new HashSet<>();
    // ADDED: Timer for the game loop
    private Timer gameLoopTimer;
    private Power gokuPower;
    private Power jinPower;
    private boolean isGameOver;
    private int gameTime = 30;
    private Timer countdownTimer;
    public void startGame() {
        countdownTimer = new Timer(1000, e -> {
            if (gameTime > 0 && !isGameOver) {
                gameTime--;
            }
            else if (gameTime == 0) {
                isGameOver = true;
                countdownTimer.stop();
                gameLoopTimer.stop();
            }
            repaint();
        });
        countdownTimer.start();
        gameLoopTimer.start();
    }
    private void loadPower(){
        gokuPower = new Power(850,"SON GOKU");
        jinPower = new Power(25,"JIN KAZAMA");
    }
    private void paintPower(Graphics pen){
        gokuPower.printBox(pen);
        jinPower.printBox(pen);
    }
    public Board() throws IOException {
        LoadBGImage();
        playerR = new PlayerRight();
        playerL = new PlayerLeft();
        setFocusable(true);
        bindEvents();
        loadPower();
        gameLoopTimer = new Timer(GAME_DELAY, e -> {
            updateGame();
            repaint();
            playerL.fall();
            playerR.fall();
            collide();
        });
    }
    private boolean isCollide(){
        int xDistance = Math.abs(playerL.getX() - playerR.getX());
        int yDistance = Math.abs(playerL.getY() - playerR.getY());
        int maxW = Math.max(playerL.getW(), playerR.getW());
        int maxH = Math.max(playerL.getH(), playerR.getH());
        return xDistance <= maxW-35 && yDistance <=maxH;
    }
    public void collide(){
        if(isCollide()){
            if(playerL.getIsAttacking() && !playerR.getIsAttacking() && !(playerR.isjump())){
                playerR.setCurrentMove(DAMAGED);
                gokuPower.setHealth();
            }
            else if(playerR.getIsAttacking() && !playerL.getIsAttacking() && !(playerL.isjump())){
                playerL.setCurrentMove(DAMAGED);
                jinPower.setHealth();
            }
            if(gokuPower.getHealth()<=0 || jinPower.getHealth()<=0){
                isGameOver = true;
            }
            playerL.setCollide(true);
            playerL.setSpeed(0);
        }
        else{
            playerL.setSpeed(SPEED);
        }
    }
    private void printMessage(Graphics pen){
        if(gokuPower.getHealth()<=0){
            pen.drawString("JIN KAZAMA WON", 525, 270);
        }
        else if(jinPower.getHealth()<=0){
            pen.drawString("SON GOKU WON", 525, 270);
        }
        else{
            pen.drawString("DRAW", 610, 270);
        }
        pen.setColor(Color.RED);
        pen.setFont(new Font("times",Font.BOLD,50));
        pen.drawString("GAME OVER", 510, 210);
    }
    private void bindEvents(){
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode()
                        == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SHIFT)
                {
                    pressedKeys.remove(e.getKeyCode());
                }
                if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D ||
                        e.getKeyCode() == KeyEvent.VK_C || e.getKeyCode() == KeyEvent.VK_Z
                        || e.getKeyCode() == KeyEvent.VK_W)
                {
                    pressedKeys.remove(e.getKeyCode());
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                pressedKeys.add(e.getKeyCode());
            }
        });
    }
    private void updateGame() {
        // Player Right (Arrow Keys + SHIFT + ENTER)
        if (pressedKeys.contains(KeyEvent.VK_LEFT)) {
            if (isCollide() && playerR.getX() > playerL.getX()) {
                playerR.setSpeed(0);
            }
            else {
                playerR.setSpeed(-SPEED);
            }
            playerR.setCurrentMove(WALK);
            playerR.move();
        }

        else if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {
            playerR.setSpeed(SPEED);
            playerR.setCurrentMove(WALK);
            playerR.move();
        }
        else if(pressedKeys.contains(KeyEvent.VK_ENTER)){
            playerR.setIsAttacking(true);
            playerR.setCurrentMove(PUNCH);
        }
        else if(pressedKeys.contains(KeyEvent.VK_SHIFT)){
            playerR.setIsAttacking(true);
            playerR.setCurrentMove(KICK);
        }
        else if(pressedKeys.contains(KeyEvent.VK_UP)){
            playerR.jump();
        }
        else {
            playerR.setSpeed(0);
        }

        // Player Left (A, D, W, Z, C keys)
        if (pressedKeys.contains(KeyEvent.VK_A)) {
            playerL.setSpeed(-SPEED);
            playerL.setCurrentMove(WALK);
            playerL.move();
            playerL.setCollide(false);
        }
        else if (pressedKeys.contains(KeyEvent.VK_D)) {
            if(isCollide()){
                playerL.setSpeed(0);
            }
            else{
                playerL.setSpeed(SPEED);
            }
            playerL.move();
            playerL.setCurrentMove(WALK);
        }
        else if(pressedKeys.contains(KeyEvent.VK_C)) {
            playerL.setIsAttacking(true);
            playerL.setCurrentMove(PUNCH);
        }
        else if(pressedKeys.contains(KeyEvent.VK_Z)) {
            playerL.setIsAttacking(true);
            playerL.setCurrentMove(KICK);
        }
        else if(pressedKeys.contains(KeyEvent.VK_W)) {
            playerL.jump();
        }
        else {
            playerL.setSpeed(0);
        }
    }

    @Override
    public void paintComponent(Graphics pen){
        super.paintComponent(pen);
        paintBackGround(pen);
        playerR.PrintPlayer(pen);
        playerL.PrintPlayer(pen);
        paintPower(pen);
        if(isGameOver){
            printMessage(pen);
            gameLoopTimer.stop();
        }

        pen.setColor(Color.YELLOW);
        pen.setFont(new Font("Arial", Font.BOLD, 40));
        pen.drawString("Time: " + gameTime, 580, 50);
    }
    private void paintBackGround(Graphics pen){
        pen.drawImage(imagebg,0,0,GWIDTH,GHEIGHT,null);
    }
    private void LoadBGImage(){
        try {
            imagebg = ImageIO.read(Board.class.getResource("bg.png"));
        }
        catch (Exception e) {
            System.out.println("Image Loading Failed, Loading Default...");
            imagebg = new BufferedImage(GWIDTH, GHEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = imagebg.createGraphics();
            g2.setColor(Color.CYAN);
            g2.fillRect(0, 0, GWIDTH, GHEIGHT);
            g2.dispose();
        }
    }
}