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

    public BoardPanel(int wordLength, String mode){
        super();

        this.maxRow = wordLength + 1;
        this.maxCol = wordLength;
        this.tiles = new LetterTile[maxRow][maxCol];

        this.setLayout(new GridLayout(maxRow, maxCol, 4, 4));

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

    public void addTile(LetterTile tile){
        JLabel temp = new JLabel(tile);
        Border line = BorderFactory.createLineBorder(new Color(189, 189, 189));
        temp.setBorder(line);
        add(temp);
    }

}
