import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SecretWord {
    private String secretWord;
    private ArrayList<String> wordBank;
    private List<String> dictionary;

    /* Enumeration to represent game modes
    public enum GameMode {
        NORMAL,
        SLU
    }*/

    // Constructor: Choose a random word from the provided word bank file based on the selected mode
    public SecretWord(String filename) {
        this.wordBank = readWordBank(filename);
        this.secretWord = chooseRandomWord();
        this.dictionary = new ArrayList<String>();

        try {
            //dictionary file via https://github.com/dwyl/english-words
            File dict = new File("dictionary.txt");
            Scanner in = new Scanner(dict);
            while(in.hasNextLine()){
                String line = in.nextLine();
                dictionary.add(line);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }

    public int length(){
        return secretWord.length();
    }

    /* Method to choose a random 5-letter word from the word bank file for normal mode
    private String chooseRandomWord() {
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
    }*/

    //Method to choose a random SLU-related word from the word bank file for SLU mode
    private String chooseRandomWord(){
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

    //method to check if the guessed word is in the Word Bank
    public boolean isInBank(String guess){
        return wordBank.contains(guess); 
    }

    //method to check if guessed word is in dictionary
    public boolean isInDictionary(String guess){
        int index = Collections.binarySearch(dictionary, guess);
        return index >= 0;
    }

    // Getter method to retrieve the secret word
    public String getSecretWord() {
        return secretWord;
    }

    public String toString(){
        return secretWord;
    }
}

