package gaming.structure;

import gaming.constants.GameConstants;
import javax.swing.*;
import java.io.IOException;

public class GameFrame extends  JFrame implements GameConstants {
    GameFrame() throws IOException {
        setTitle(TITLE);
        Board b1 = new Board();
        add(b1);
        setVisible(true);
        setSize(GWIDTH,GHEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Board b = new Board();
        b1.startGame();
        add(b1);
    }
}

