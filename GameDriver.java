import javax.swing.*;
import java.awt.event.*;

public class GameDriver{
    private ConfigFrame config;
    
    public GameDriver() {

        ActionListener startListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Button Pressed");

                String secretWord = "secret";
                Controller c = new Controller(secretWord);

                config.setVisible(false);
                config.dispose();

                SLUdleFrame frame = new SLUdleFrame ("SLUdle", 5, c, "BASIC", config.getDifficulty());
            }
         };

        this.config = new ConfigFrame("SLUdle", startListener);
    }

    public static void main(String[] args) {
        GameDriver newGame = new GameDriver();
    }
}