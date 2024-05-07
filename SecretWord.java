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

    // Constructor: Choose a random word from the provided word bank file based on the selected mode
    public SecretWord(String filename) {
        this.wordBank = readWordBank(filename);
        this.secretWord = chooseRandomWord().toUpperCase();
        //dictionary file via https://github.com/dwyl/english-words
        this.dictionary = readWordBank("dictionary.txt");
    }

    public int length(){
        return secretWord.length();
    }

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
                if(isAllLetters(line.trim())){
                    words.add(line.trim().toLowerCase());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading word bank file: " + e.getMessage());
        }
        return words;
    }

    private boolean isAllLetters(String word){
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!Character.isAlphabetic(c)){
                return false;
            }
        }
        return true;
    }
    //method to check if the guessed word is in the Word Bank
    public boolean isInBank(String guess){
        return wordBank.contains(guess.toLowerCase()); 
    }

    //method to check if guessed word is in dictionary
    public boolean isInDictionary(String guess){
        int index = Collections.binarySearch(dictionary, guess.toLowerCase().trim());
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

