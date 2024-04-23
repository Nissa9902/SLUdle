import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class BoardPanel extends JPanel{//implements observer
    private int xDim;
    private int yDim;
    private LetterTile[][] tiles;

    public BoardPanel(int wordLength){
        super();

        this.xDim = wordLength;
        this.yDim = wordLength + 1;
        this.tiles = new LetterTile[yDim][xDim];

        this.setLayout(new GridLayout(yDim, xDim, 4, 4));
        for (int row = 0; row < yDim; row++){
            for(int col = 0; col < xDim; col++){
                LetterTile newTile = new LetterTile(' ', "empty");
                tiles[row][col] = newTile;
                this.addTile(newTile);
            }
            
        }

        setTileLetter(0, 0, 'A');
        setTileStatus(0, 0, "contains");
    }

    public void addTile(LetterTile tile){
        JLabel temp = new JLabel(tile);
        Border line = BorderFactory.createLineBorder(new Color(189, 189, 189));
        temp.setBorder(line);
        add(temp);
    }

    public void setTileStatus(int row, int col, String status){
        tiles[row][col].setStatus(status);
    }

    public void setTileLetter(int row, int col, char letter){
        tiles[row][col].setLetter(letter);
    }
}
