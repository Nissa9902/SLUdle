import javax.swing.*;
import java.awt.event.*;

public class GameDriver{
    private ConfigFrame config;
    
    public GameDriver() {

        ActionListener startListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Button Pressed");

                boolean isHard = config.getDifficulty().equals("HARD");
                String mode = config.getMode();

                //Normal Word Bank via https://github.com/charlesreid1/five-letter-words/blob/master/sgb-words.txt
                SecretWord secretWord = new SecretWord("NormalWordBank.txt");//defaulting to Normal mode for Alpha v

                System.out.println(secretWord);
                config.setVisible(false);
                config.dispose();

                Controller c = new Controller(secretWord, false); //hard mode off temporarily
                SLUdleFrame frame = new SLUdleFrame ("SLUdle", secretWord, c, "BASIC", false);
            }
         };

        this.config = new ConfigFrame("SLUdle", startListener);
    }

    public static void main(String[] args) {
        GameDriver newGame = new GameDriver();
    }
}