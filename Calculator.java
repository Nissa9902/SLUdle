import javax.swing.*;
import java.awt.*;

public class Calculator extends JPanel {
    private JLabel guessesLabel;
    private int totalGuesses;
    private int streak; // variable to track streak
    private JLabel streakLabel; // variable to displays streak

    public Calculator() {
        super();
        setLayout(new GridLayout(2, 1));
        guessesLabel = new JLabel("Total Guesses: 0");
        streakLabel = new JLabel("Streak: 0");
        add(guessesLabel);
        add(streakLabel); 
    }

    public void updateStats( int guess) {
        totalGuesses += guess;
        guessesLabel.setText("Total Guesses: " + totalGuesses);
        updateStreak(guess);
        repaint();
    }

    public void reset() {
        totalGuesses = 0;
        guessesLabel.setText("Total Guesses: 0");
        streakLabel.setText("Streak: 0");
    }

    public void updateStreak(int num) {
        if (num == 1) { 
            streak++;
            streakLabel.setText("Streak: " + streak);
        } else { 
            streak = 0;
            streakLabel.setText("Streak: 0");
        }
    }

}
