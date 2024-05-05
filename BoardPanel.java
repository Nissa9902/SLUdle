import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class BoardPanel extends JPanel {
    private int maxCol;
    private int maxRow;
    private LetterTile[][] tiles;
    private int currentRow;
    private int currentCol;
    private LetterTile[] currentGuess;
    private String mode;
    private JLabel currentStreakLabel;
    private JLabel maxStreakLabel;
    private JLabel guessCountLabel;
    private Calculator Calculator;
    private int currentGuessCount;

    public BoardPanel(int wordLength, String mode, Calculator Calculator){
        super();

        this.maxRow = wordLength + 1;
        this.maxCol = wordLength;
        this.tiles = new LetterTile[maxRow][maxCol];

        this.setLayout(new GridLayout(maxRow, maxCol, 4, 4));
        
        this.Calculator = Calculator;
        this.currentGuessCount = 0;

        for(int row = 0; row < maxRow; row++){
            for (int col = 0; col < maxCol; col++){                
                LetterTile newTile;
                if(mode.equals("SLU")){
                    newTile = new SLULetterTile(' ', "empty");
                } else {
                    newTile = new LetterTile(' ', "empty");
                }
                tiles[row][col] = newTile;
                this.addTile(newTile);
            }
        }
        this.currentRow = 0;
        this.currentCol = 0;
        this.currentGuess = new LetterTile[maxCol];
        this.mode = mode;
        
        // labels for displaying streaks/guess count
        currentStreakLabel = new JLabel("Current Streak: 0");
        maxStreakLabel = new JLabel("Max Streak: 0");
        guessCountLabel = new JLabel("Guesses: 0");

        // adds labels to the panel
        JPanel statsPanel = new JPanel(new GridLayout(1, 3));
        statsPanel.add(currentStreakLabel);
        statsPanel.add(maxStreakLabel);
        statsPanel.add(guessCountLabel);
        add(statsPanel, BorderLayout.SOUTH);

    }

    public void addTypedLetter(char letter){
        if(currentCol < maxCol && currentRow < maxRow){
            tiles[currentRow][currentCol].setLetter(letter);
            tiles[currentRow][currentCol].setStatus("unchecked");
            currentGuess[currentCol] = tiles[currentRow][currentCol];
            currentCol++;
        }
        this.revalidate();
        this.repaint();
    }

    public void backspace(){
        if(currentCol > 0){
            tiles[currentRow][currentCol-1].setStatus("empty");
            currentCol--;
            this.revalidate();
            this.repaint();
        }
    }

    public LetterTile[] getCurrentGuess(){
        return currentGuess;
    }

    public String getCurrentGuessString(){
        String guess = "";
        for(LetterTile tile: currentGuess){
            if(tile != null){
                guess += tile.getLetter();
            }
            
        }
        return guess;
    }

    public void nextRow(){
        if(currentRow < maxRow - 1){
            currentRow++;
            currentCol = 0;
        }
        for(int i = 0; i < currentGuess.length; i++){
            currentGuess[i] = null;
        }
        // Update guess count
        currentGuessCount++;
        updateStats();
    }

    public void setTileStatus(int row, int col, String status){
        tiles[row][col].setStatus(status);
        this.revalidate();
        this.repaint();
    }

    public void setTileLetter(int row, int col, char letter){
        tiles[row][col].setLetter(letter);
        this.revalidate();
        this.repaint();
    }

    public void resetBoard(){
        for (int row = 0; row < maxCol; row++){
            for(int col = 0; col < maxRow; col++){
                this.setTileLetter(row, col, ' ');
                this.setTileStatus(row, col, "empty");
            }
        }
        currentGuessCount = 0;
        updateStats();
    }

    public void addTile(LetterTile tile){
        JLabel temp = new JLabel(tile);
        Border line = BorderFactory.createLineBorder(new Color(189, 189, 189));
        temp.setBorder(line);
        add(temp);
    }
    
    //updates score
    public void updateStats() {
        currentStreakLabel.setText("Current Streak: " + Calculator.getCurrentStreak());
        maxStreakLabel.setText("Max Streak: " + Calculator.getMaxStreak());
        guessCountLabel.setText("Guesses: " + currentGuessCount);

}