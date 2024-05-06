import javax.swing.*;
import java.awt.event.*;
import javax.swing.UIManager;

public class GameDriver{
    private ConfigFrame config;
    
    public GameDriver() {

        ActionListener startListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start Button Pressed");

                boolean isHard = config.getDifficulty().equals("HARD");
                String mode = config.getMode();

                //Normal Word Bank via https://github.com/charlesreid1/five-letter-words/blob/master/sgb-words.txt
                SecretWord secretWord;
                if(mode.equals("SLU")){
                    secretWord = new SecretWord("SLUWordBank.txt");
                } else {
                    secretWord = new SecretWord("NormalWordBank.txt");
                }

                System.out.println(secretWord);
                config.setVisible(false);
                config.dispose();

                SLUdleFrame frame = new SLUdleFrame ("SLUdle", secretWord, mode, isHard);
            }
         };

        this.config = new ConfigFrame("SLUdle", startListener);
    }
   
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        
        new GameDriver(); 

    }
}