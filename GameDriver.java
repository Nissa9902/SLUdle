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
            // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException e) {
        // handle exception
        }
        catch (ClassNotFoundException e) {
        // handle exception
        }
        catch (InstantiationException e) {
        // handle exception
        }
        catch (IllegalAccessException e) {
        // handle exception
        }
        
        GameDriver newGame = new GameDriver();
    }
}