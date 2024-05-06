import javax.swing.*;
import java.awt.event.*;
import javax.swing.UIManager;

public class Game{
    private ConfigFrame config;
    private SLUdleFrame gameFrame;
    private ActionListener resetListener;
    private Calculator scoreCalculator;

    public Game(ActionListener resetListener, Calculator scoreCalculator) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }

        this.resetListener = resetListener;
        this.scoreCalculator = scoreCalculator;

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

                gameFrame = new SLUdleFrame ("SLUdle", secretWord, mode, isHard, resetListener, scoreCalculator);
            }
         };

        this.config = new ConfigFrame("SLUdle", startListener);
    }

    public void close(){
        //this.gameFrame.dispatchEvent(new WindowEvent(gameFrame, WindowEvent.WINDOW_CLOSING));
        gameFrame.setVisible(false); //you can't see me!
        gameFrame.dispose();
    }

    /*public static void main(String[] args) {      
        new GameDriver(); 

    }*/
}