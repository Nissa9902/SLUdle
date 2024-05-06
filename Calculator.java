import javax.swing.*;
import java.awt.*;

public class Calculator extends JPanel {
    private JLabel guessesLabel;
    private int totalGuesses;

    public Calculator() {
        super();
        setLayout(new GridLayout(2, 1));
        guessesLabel = new JLabel("Total Guesses: 0");
        add(guessesLabel);
    }

    public void updateStats( int guess) {
        totalGuesses += guess;
        guessesLabel.setText("Total Guesses: " + totalGuesses);
        repaint();
    }

    //public void reset() {
    //    totalGuesses = 6;
    //    guessesLabel.setText("Total Guesses: 0");
    //}
}