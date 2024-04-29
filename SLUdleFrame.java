import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class SLUdleFrame extends JFrame {
    private BoardPanel boardPanel;
    private Keyboard keyboard;
    private ArrayList<Character> inWord;
    private ArrayList<Character> invalid;
    private char[] found;

    private SecretWord secretWord;
    private String mode;
    private boolean isHard;
    
    public SLUdleFrame(String title, SecretWord secretWord, Controller c, String mode, boolean isHard){
        super(title);
        int wordLength = secretWord.length();
        this.secretWord = secretWord;
        this.mode = mode;
        this.isHard = isHard;

        this.inWord = new ArrayList<Character>();
        this.invalid = new ArrayList<Character>();
        this.found = new char[secretWord.length()];

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 600));
        panel.setBackground(Color.WHITE);

        this.boardPanel = new BoardPanel(wordLength, mode);
        boardPanel.setPreferredSize(new Dimension(60 * wordLength, 60 * (wordLength + 1)));
        boardPanel.setBackground(Color.WHITE);
        c.register(boardPanel);

        this.keyboard = new Keyboard(boardPanel);
        keyboard.setPreferredSize(new Dimension(475, 200));

        panel.add(boardPanel);
        panel.add(keyboard);
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
    }

    public boolean guess(LetterTile[] guess){
        boolean result = true;
        String word = secretWord.getSecretWord();
        for(int i = 0; i < guess.length; i++){
            if(guess[i].getLetter() == word.charAt(i)){
                guess[i].setStatus("correct");
                found[i] = guess[i].getLetter();

            } else if(word.contains(String.valueOf(guess[i].getLetter()))){
                guess[i].setStatus("contains");
                inWord.add(guess[i].getLetter());

                result = false;
            } else {
                guess[i].setStatus("incorrect");
                invalid.add(guess[i].getLetter());
                
                result = false;
            }
        }

        return result;
    }

    // Method to validate the guessed word
    public boolean validateGuess(LetterTile[] guess) {

        //check guess follows hard rules if applicable
        if(mode.equals("HARD")){
            boolean hardGuessValid = validateHardMode(guess);
            if(!hardGuessValid){
                return false;
            }
        }

        //check for empty tiles or invalid characters
        String guessStr = "";
        for(LetterTile tile: guess){
            if(tile.getStatus().equals("empty")){
                return false;
            }

            if(Character.isLetter(tile.getLetter())){
                guessStr += tile.getLetter();
            } else {
                return false;
            }
        }

        //check for correct length
        boolean validLength = guessStr.length() == secretWord.length();
        if(!validLength){
            return false;
        }

        //guessed word needs to be either in our word bank or dictionary
        return secretWord.isInDictionary(guessStr) || secretWord.isInBank(guessStr);
    }

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

        return true;
    }


}
