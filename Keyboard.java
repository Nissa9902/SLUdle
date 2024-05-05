import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Keyboard extends JPanel {
    private static final String ALPHABET = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private KeyboardButton[] keys;
    private JButton enter;
    private JButton backspace;
    private BoardPanel grid;
    private SLUdleFrame frame;
    private String mode;

    public Keyboard(BoardPanel board, ActionListener enterListener, String mode) {
        this.grid = board;
        this.mode = mode;

        setLayout(new GridLayout(4, 10, 2, 2));
        keys = new KeyboardButton[26];

        // buttons 
        int index = 0;
        for (int i = 0; i < 19; i++) {
            if(mode.equals("SLU")){
                keys[i] = new SLUKeyboardButton(String.valueOf((char) (ALPHABET.charAt(i))));
            
            } else {
                keys[i] = new KeyboardButton(String.valueOf((char) (ALPHABET.charAt(i))));
            }

            keys[i].setStatus("unchecked");
            keys[i].addActionListener(new KeyboardListener());
            keys[i].setMargin(new Insets(1,1,1,1));
            
            Font newButtonFont = new Font(keys[i].getFont().getName(),keys[i].getFont().getStyle(),16);
            keys[i].setFont(newButtonFont);
            add(keys[i]);
        }

        this.backspace = new JButton("<=");
        backspace.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                board.backspace();
            }
        });
        backspace.setBackground(new Color(204, 204, 204));
        backspace.setForeground(new Color(64, 64, 64));

        backspace.setMargin(new Insets(1,1,1,1));
        add(backspace);
        

        for (int i = 19; i < 26; i++) {
            if(mode.equals("SLU")){
                keys[i] = new SLUKeyboardButton(String.valueOf((char) (ALPHABET.charAt(i))));
            } else {
                keys[i] = new KeyboardButton(String.valueOf((char) (ALPHABET.charAt(i))));
            }

            keys[i].setStatus("unchecked");
            keys[i].addActionListener(new KeyboardListener());
            keys[i].setMargin(new Insets(1,1,1,1));

            Font newButtonFont = new Font(keys[i].getFont().getName(),keys[i].getFont().getStyle(),16);
            keys[i].setFont(newButtonFont);
            add(keys[i]);
        }

        this.enter = new JButton("ENTER");
        enter.setBackground(new Color(204, 204, 204));
        enter.setForeground(new Color(64, 64, 64));

        enter.addActionListener(enterListener);
        enter.setMargin(new Insets(1,1,1,1));
        add(enter);

    }

    private class KeyboardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            char letter = button.getText().charAt(0);
            grid.addTypedLetter(letter);
        }
    }

    public void updateKeyColors(LetterTile[] guess) {
        for (LetterTile tile : guess) {
            int index = ALPHABET.indexOf(Character.toUpperCase(tile.getLetter()));

            if (index != -1) {
                if(tile.getStatus().equals("correct")){
                    keys[index].setStatus("correct");
                } else if (tile.getStatus().equals("contains")){
                    if(!keys[index].getStatus().equals("correct")){
                        keys[index].setStatus("contains");
                    }
                } else if (tile.getStatus().equals("incorrect")){
                    if(!keys[index].getStatus().equals("correct")){
                        keys[index].setStatus("incorrect");
                    }
                }
            }    
        }
        
    }

    public void resetKeyColors() {
        for (JButton key : keys) {
            key.setBackground(Color.LIGHT_GRAY);
        }
    }

    private Color getColorForStatus(String status) {
        switch (status) {
            case "correct":
                return new Color(0, 61, 165); // SLU Blue
            case "contains":
                return new Color(237, 139, 0); //  Orange
            case "incorrect":
                return new Color(120, 124, 127); // Dark gray
            default:
                return Color.LIGHT_GRAY;
        }
    }

    public void disableKeyboard(){
        for(KeyboardButton key: keys){
            key.setEnabled(false);
        }
        enter.setEnabled(false);
        backspace.setEnabled(false);
    }
}
