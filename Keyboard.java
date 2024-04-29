import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Keyboard extends JPanel implements Observer {
    private static final String ALPHABET = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private JButton[] keys;
    private BoardPanel grid;

    public Keyboard(BoardPanel grid) {
        this.grid = grid;
        setLayout(new GridLayout(3, 10));
        keys = new JButton[26];

        // buttons 
        int index = 0;
        for (int i = 0; i < 19; i++) {
            keys[i] = new JButton(String.valueOf((char) (ALPHABET.charAt(i))));
            keys[i].setBackground(Color.LIGHT_GRAY);
            keys[i].addActionListener(new KeyboardListener());
            add(keys[i]);
        }

        JButton backspace = new JButton("<=");
        backspace.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                grid.backspace();
            }
        });
        backspace.setMargin(new Insets(1,1,1,1));
        add(backspace);
        

        for (int i = 19; i < 26; i++) {
            keys[i] = new JButton(String.valueOf((char) (ALPHABET.charAt(i))));
            keys[i].setBackground(Color.LIGHT_GRAY);
            keys[i].addActionListener(new KeyboardListener());
            add(keys[i]);
        }

        JButton enter = new JButton("ENTER");
        enter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                
            }
        });
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

    public void updateKeyColor(char letter, Color color) {
        int index = ALPHABET.indexOf(Character.toUpperCase(letter));
        if (index != -1) {
            keys[index].setBackground(color);
        }
    }

    public void resetKeyColors() {
        for (JButton key : keys) {
            key.setBackground(Color.LIGHT_GRAY);
        }
    }

    @Override
    public void update(LetterTile[] guess, boolean isHard) {
        // Update key colors based on the guess made
        for (LetterTile tile : guess) {
            updateKeyColor(tile.getLetter(), getColorForStatus(tile.getStatus()));
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
}
