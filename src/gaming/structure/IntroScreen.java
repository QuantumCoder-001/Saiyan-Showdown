package gaming.structure;
import jaco.mp3.player.MP3Player;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class IntroScreen extends JWindow {
    private MP3Player player;
    private void playSound(){
        player = new MP3Player(IntroScreen.class.getResource("MUI.mp3"));
        player.setRepeat(true);
        player.play();
    }
    private JLabel label = new JLabel();
    public IntroScreen() throws Exception {
        setSize(1300, 730);
        setLayout(new BorderLayout());
        BufferedImage org = ImageIO.read(IntroScreen.class.getResource("Introscreen.jpg"));
        Image scaledImg = org.getScaledInstance(1300, 730, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(scaledImg);
        label.setIcon(icon);
        this.add(label);
        setLocationRelativeTo(null);
    }
    public void showIntroThenStartGame() throws Exception {
        setVisible(true);
        playSound();
        Timer t = new Timer(10000, e -> {
            setVisible(false);
            dispose();
            try {
                GameFrame obj = new GameFrame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        t.setRepeats(false);
        t.start();
    }
    public static void main(String[] args) {
        try {
            IntroScreen screen  = new IntroScreen();
            screen.showIntroThenStartGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
