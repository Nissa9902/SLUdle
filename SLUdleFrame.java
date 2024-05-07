import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private Calculator calculator;
    private JButton resetButton;
    
    public SLUdleFrame(String title, SecretWord secretWord, 
    String mode, boolean isHard, ActionListener resetListener,
    Calculator scoreCalculator){

        super(title);
        int wordLength = secretWord.length();

        this.guessCount = 1;
        this.maxGuess = wordLength + 1;

        this.secretWord = secretWord;
        this.isHard = isHard;

        this.inWord = new ArrayList<Character>();
        this.invalid = new ArrayList<Character>();
        this.found = new char[secretWord.length()];

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100 * wordLength + 240, 100 * (wordLength + 1) + 300));
        panel.setBackground(Color.WHITE);
        
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        this.boardPanel = new BoardPanel(wordLength, mode, scoreCalculator);
        boardPanel.setBackground(Color.WHITE);

        c.anchor = GridBagConstraints.CENTER;

        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = wordLength;
        c.gridheight = wordLength + 1;
        panel.add(boardPanel, c);

        this.messageLabel = new JLabel(" ");
        messageLabel.setAlignmentX(CENTER_ALIGNMENT);

        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridheight = 1;
        panel.add(messageLabel, c);

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

        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridheight = 2;
        panel.add(keyboard, c);

        this.calculator = scoreCalculator;
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = 1;
        c.gridheight = 2;
        panel.add(calculator, c);
        
        this.resetButton =  new JButton("Reset");
        resetButton.addActionListener(resetListener);
        
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = 1;
        c.gridheight = 2;
        panel.add(resetButton, c);

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
                foundInGuess += guess[i].getLetter();

            } else {
                guess[i].setStatus("incorrect");
                result = false;
            }
        }

        String containedInGuess = "";
        for(int i = 0; i < guess.length; i++){
            if(word.contains(String.valueOf(guess[i].getLetter())) && 
            !guess[i].getStatus().equals("correct") && 
            !foundInGuess.contains(String.valueOf(guess[i].getLetter()))){
                
                char letter = guess[i].getLetter();

                int count = 0;
                for (int j = 0; j < word.length(); j++) {
                    if (word.charAt(j) == letter) {
                        count++;
                    }
                }

                if(containedInGuess.contains(String.valueOf(letter)) && count > 1){
                    guess[i].setStatus("contains");
                    containedInGuess += letter;
                } else if(!containedInGuess.contains(String.valueOf(letter))){
                    guess[i].setStatus("contains");
                    containedInGuess += letter;
                }
                
            }
        }

        if(isHard){
            for(int j = 0; j < guess.length; j++){
                if(guess[j].getStatus().equals("correct")){
                    found[j] = guess[j].getLetter();

                } else if (guess[j].getStatus().equals("contains")){
                    inWord.add(guess[j].getLetter());

                } else if (guess[j].getStatus().equals("incorrect")){
                    if(!inWord.contains(guess[j].getLetter())){
                        invalid.add(guess[j].getLetter());
                    }
                }
            }
        }

        keyboard.updateKeyColors(guess);
        
        boardPanel.nextRow();
        boardPanel.revalidate();
        boardPanel.repaint();
        
        if(result){
            messageLabel.setText("You Won :)");
            keyboard.disableKeyboard();
            calculator.updateStreak(1);
        } else if (guessCount == maxGuess){
            messageLabel.setText("Answer: " + secretWord);
            keyboard.disableKeyboard();
            calculator.updateStreak(0);
        
        }
        guessCount++;
        
        return result;
    }

    // Method to validate the guessed word
    public boolean validateGuess(LetterTile[] guess) {
        //check guess follows hard rules if applicable
        if(isHard){
            boolean hardGuessValid = validateHardMode(guess);
            if(!hardGuessValid){
                return false;
            }
        }

        //check for empty tiles or invalid characters
        String guessStr = "";
        for(LetterTile tile: guess){
            if(tile != null){
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
            this.messageLabel.setText(" ");
            return true;
        } else {
            this.messageLabel.setText("Not a word");
            return false;
        }
    }

    public boolean validateHardMode(LetterTile[] guess){
        //check if found letters are used
        for(int i = 0; i < found.length; i++){
            if(found[i] != 0){
                if(guess[i].getLetter() != found[i]){
                    this.messageLabel.setText("Didn't use found letters");
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
                this.messageLabel.setText("Not all contained letters used");
                return false;
            }
        }

        //check if any invalid letters are used
        System.out.println(invalid);
        for(LetterTile tile: guess){
            if(invalid.contains(tile.getLetter())){
                this.messageLabel.setText("Used invalid letter");
                return false;
            }
        }
        this.messageLabel.setText("Winner!");
        return true;
    }
}
