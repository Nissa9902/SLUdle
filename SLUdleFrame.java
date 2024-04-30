import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout; 
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class SLUdleFrame extends JFrame {
    private BoardPanel boardPanel;
    private Keyboard keyboard;
    private JLabel messageLabel;
    private ArrayList<Character> inWord;
    private ArrayList<Character> invalid;
    private char[] found;
    private int guessCount;
    private int maxGuess;

    private SecretWord secretWord;
    private String mode;
    private boolean isHard;
    
    public SLUdleFrame(String title, SecretWord secretWord, String mode, boolean isHard){
        super(title);
        int wordLength = secretWord.length();

        this.guessCount = 1;
        this.maxGuess = wordLength + 1;

        this.secretWord = secretWord;
        this.mode = mode;
        this.isHard = isHard;

        this.inWord = new ArrayList<Character>();
        this.invalid = new ArrayList<Character>();
        this.found = new char[secretWord.length()];

        JPanel panel = new JPanel();
        //panel.setPreferredSize(new Dimension(60 * wordLength + 240, 60 * (wordLength + 1) + 250));
        panel.setBackground(Color.WHITE);

        this.boardPanel = new BoardPanel(wordLength, mode);
       // boardPanel.setPreferredSize(new Dimension(60 * wordLength, 60 * (wordLength + 1)));
        boardPanel.setBackground(Color.WHITE);

        ActionListener enterListener = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println(boardPanel.getCurrentGuessString());
                if(validateGuess(boardPanel.getCurrentGuess())){
                    System.out.println("guess is valid");
                    guess(boardPanel.getCurrentGuess());
                }
            }
        };
        this.keyboard = new Keyboard(boardPanel, enterListener, mode);
        //keyboard.setPreferredSize(new Dimension(475, 200));

        this.messageLabel = new JLabel("");
        messageLabel.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(boardPanel);
        panel.add(keyboard);
        panel.add(messageLabel);
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
    }

    public boolean guess(LetterTile[] guess){
        boolean result = true;
        String word = secretWord.getSecretWord();
        String foundInGuess = "";

        for(int i = 0; i < guess.length; i++){
            if(guess[i].getLetter() == word.charAt(i)){
                guess[i].setStatus("correct");
                found[i] = guess[i].getLetter();
                foundInGuess += guess[i].getLetter();

            } else {
                guess[i].setStatus("incorrect");
                invalid.add(guess[i].getLetter());
                result = false;
            }
        }

        for(int i = 0; i < guess.length; i++){
            if(word.contains(String.valueOf(guess[i].getLetter())) && !guess[i].getStatus().equals("correct") && 
            !foundInGuess.contains(String.valueOf(guess[i].getLetter()))){
                guess[i].setStatus("contains");
                inWord.add(guess[i].getLetter());
                result = false;
            }
        }

        keyboard.updateKeyColors(guess);
        
        boardPanel.nextRow();
        boardPanel.revalidate();
        boardPanel.repaint();

        if(result){
            messageLabel.setText("You Won :)");
            keyboard.disableKeyboard();
        } else if (guessCount == maxGuess){
            messageLabel.setText("Answer: " + secretWord);
            keyboard.disableKeyboard();
        }
        guessCount++;
        return result;
    }

    // Method to validate the guessed word
    public boolean validateGuess(LetterTile[] guess) {

        /*check guess follows hard rules if applicable
        if(mode.equals("HARD")){
            boolean hardGuessValid = validateHardMode(guess);
            if(!hardGuessValid){
                return false;
            }
        }*/

        //check for empty tiles or invalid characters
        String guessStr = "";
        for(LetterTile tile: guess){
            if(tile.getStatus().equals("empty")){
                this.messageLabel.setText("Too short");
                return false;
            }

            if(Character.isLetter(tile.getLetter())){
                guessStr += tile.getLetter();
            } else {
                this.messageLabel.setText("Invalid letter(s)");
                return false;
            }
        }

        guessStr = guessStr.toLowerCase();
        //check for correct length
        boolean validLength = guessStr.length() == secretWord.length();
        if(!validLength){
            this.messageLabel.setText("Too short");
            return false;
        }

        //guessed word needs to be either in our word bank or dictionary
        if(secretWord.isInDictionary(guessStr) || secretWord.isInBank(guessStr)){
            this.messageLabel.setText("");
            return true;
        } else {
            this.messageLabel.setText("Not a word");
            return false;
        }
    }

    //TODO: not currently functioning
    public boolean validateHardMode(LetterTile[] guess){
        //check if found letters are used
        for(int i = 0; i < found.length; i++){
            if(found[i] != 0){
                if(guess[i].getLetter() != found[i]){
                    return false;
                }
            }
        }

        //check if all "inWord" letters are used
        for(char letter: inWord){
            boolean found = false;

            for(LetterTile tile: guess){
                if(tile.getLetter() == letter){
                    found = true;
                    break;
                }
            }

            if(!found){
                return false;
            }
        }

        //check if any invalid letters are used
        for(LetterTile tile: guess){
            if(invalid.contains(tile.getLetter())){
                return false;
            }
        }
        this.messageLabel.setText("Winner!");
        return true;
    }


}
