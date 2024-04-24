import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SecretWord {
    private String secretWord;

    // Constructor: Choose a random word from the text file
    public SecretWord(String filename) {
        ArrayList<String> words = readWordsFromFile(filename);
        if (words != null && !words.isEmpty()) {
            Random rand = new Random();
            this.secretWord = words.get(rand.nextInt(words.size()));
        } else {
            System.out.println("Error: Unable to read words from the file.");
            // Provide a default word in case of failure
            this.secretWord = "default";
        }
    }

    // Method to read words from the text file
    private ArrayList<String> readWordsFromFile(String filename) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return words;
    }

    // Method to validate guessed word
    public boolean isValidGuess(String guess) {
        return guess.length() == secretWord.length() && isValidForm(guess);
    }

    // Method to check if guessed word has the correct form
    private boolean isValidForm(String guess) {
        for (char c : guess.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    // Method to update letter tiles to correct colors (for GUI implementation)
    // This method would typically be used to visually represent the correctness of each letter in the guessed word
    public void updateLetterTiles(String guess) {
        // Implementation would vary depending on the GUI framework used
        // For example, you might update the color of each letter tile based on its correctness
    }

    // Getter method to retrieve the secret word (for debugging or display purposes)
    public String getSecretWord() {
        return secretWord;
    }
}
