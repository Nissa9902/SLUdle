import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class BoardPanel extends JPanel implements Observer{//implements observer
    private int maxCol;
    private int maxRow;
    private LetterTile[][] tiles;
    private int currentRow;
    private int currentCol;
    private LetterTile[] currentGuess;
    private String mode;

    public BoardPanel(int wordLength, String mode){
        super();

        this.maxRow = wordLength;
        this.maxCol = wordLength + 1;
        this.tiles = new LetterTile[maxCol][maxRow];

        this.setLayout(new GridLayout(maxCol, maxRow, 4, 4));

        for (int row = 0; row < maxCol; row++){
            for(int col = 0; col < maxRow; col++){
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
        if(currentCol != maxCol && currentRow != maxRow){
            tiles[currentRow][currentCol].setLetter(letter);
            tiles[currentRow][currentCol].setStatus("unchecked");
            currentGuess[currentCol] = tiles[currentRow][currentCol];
            currentCol++;
        }
    }

    public void setBackspace(){
        tiles[currentRow][currentCol-1].setStatus("empty");
        currentCol--;
    }

    public void setTileStatus(int row, int col, String status){
        tiles[row][col].setStatus(status);
    }

    public void setTileLetter(int row, int col, char letter){
        tiles[row][col].setLetter(letter);
    }

    public void resetBoard(){
        for (int row = 0; row < maxCol; row++){
            for(int col = 0; col < maxRow; col++){
                this.setTileLetter(row, col, ' ');
                this.setTileStatus(row, col, "empty");
            }
        }
    }

    public void addTile(LetterTile tile){
        JLabel temp = new JLabel(tile);
        Border line = BorderFactory.createLineBorder(new Color(189, 189, 189));
        temp.setBorder(line);
        add(temp);
    }

    @Override
    public void update() { //might move some of this logic to SLUdleFrame
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
        //called on enter
        //guess() should update tile statuses and return true or false
        //if true (win) display congrats, record and display stats for current session, reset board and button panel
        //if false check if on last row
            //if on last row (loss) display loss, record and display stats for current session, reset board and button panel
            //if not, update current row
    }
}
