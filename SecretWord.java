import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SecretWord {
    private String secretWord;
    private ArrayList<Character> contains;
    private ArrayList<Character> invalid;
    private ArrayList<String> wordBank;
    private char[] found;

    // Enumeration to represent game modes
    public enum GameMode {
        NORMAL,
        SLU
    }

    public SecretWord(String filename) {
        this.secretWord = chooseRandomWord(filename);
        this.contains = new ArrayList<Character>();
        this.found = new char[secretWord.length()];
    }

    // Constructor: Choose a random word from the provided word bank file based on the selected mode
    public SecretWord(String filename, GameMode mode) {
        if (mode == GameMode.NORMAL) {
            this.wordBank= readWordBank(filename);
            this.secretWord = chooseRandomWord();
        } else if (mode == GameMode.SLU) {
            this.wordBank= readWordBank(filename);
            this.secretWord = chooseRandomSLUWord();
        }
    }

    
    // Method to choose a random 5-letter word from the word bank file for normal mode
    private String chooseRandomWord(String filename) {
        ArrayList<String> wordBank = readWordBank(filename);
        if (wordBank.isEmpty()) {
            System.out.println("Error: Empty word bank.");
            return ""; // Return an empty string in case of error
        }
        Random rand = new Random();
        String word = "";
        do {
            word = wordBank.get(rand.nextInt(wordBank.size()));
        } while (word.length() != 5); // Ensure the chosen word is 5 letters long
        return word;
    }

    // Method to choose a random SLU-related word from the word bank file for SLU mode
    private String chooseRandomSLUWord(String filename) {
        ArrayList<String> wordBank = readWordBank(filename);
        if (wordBank.isEmpty()) {
            System.out.println("Error: Empty word bank.");
            return ""; // Return an empty string in case of error
        }
        Random rand = new Random();
        return wordBank.get(rand.nextInt(wordBank.size()));
    }

    // Method to read words from the word bank file
    private ArrayList<String> readWordBank(String filename) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading word bank file: " + e.getMessage());
        }
        return words;
    }

    // Method to validate the guessed word
    public boolean isValidGuess(String guess) {
        return guess.length() == secretWord.length() && isValidForm(guess);
    }

    // Method to check if the guessed word has the correct form
    private boolean isValidForm(String guess) {
        for (char c : guess.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
     // method to check if the guessed word is in the Word Bank
    private boolean WordInBank (String guess){
        return wordBank. contains (guess); 
    }

    //basic guess method, not following mode or difficulty settings
    public boolean guess(LetterTile[] guess){
        //guess is not a real word or not the correct length
        if(!isValidGuess(guess)){
            return false;
        }
        boolean result = true;

        for(int i = 0; i < guess.length; i++){
            if(guess[i].getLetter() == secretWord.charAt(i)){
                guess[i].setStatus("correct");
                found[i] = guess[i].getLetter();

            } else if(secretWord.contains(String.valueOf(guess[i].getLetter()))){
                guess[i].setStatus("contains");
                contains.add(guess[i].getLetter());

                result = false;
            } else {
                guess[i].setStatus("incorrect");
                invalid.add(guess[i].getLetter());
                
                result = false;
            }
        }

        return result;
    }

    // Getter method to retrieve the secret word
    public String getSecretWord() {
        return secretWord;
    }

    public String toString(){
        return secretWord;
    }
}

