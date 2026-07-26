package com.omaridris.wordle.model;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;

/**
 * Represents a Dictionary containing all valid Words for both game targets and user guesses.
 * <p>
 * This class defines random retrieval for a target Word using CSPRNG,
 * and O(log n) validation for user guesses.
 * 
 * @author Omar Idris
 */
public class Dictionary {

    // ----*---- Attributes ----*----

    private ArrayList<String> answers;

    /**
     * Instantiates a new Dictionary object.
     * <p>
     * By default, the Dictionary utilizes the original 2,315 curated Wordle answers extracted from the pre-NYT
     * source code, sourced via Cyrus Freshman's GitHub Gist archive.
     * 
     * @throws FileNotFoundException if the "answers-alphabetical.txt" file is missing or inaccessible.
     */
    public Dictionary() throws FileNotFoundException {
        this.answers = new ArrayList<>();
        this.loadList();
    }

    // ----*---- Accessing & Validating ----*----

    /**
     * Returns a randomly selected Word from the Dictionary.
     * <p>
     * This method utilizes a Cryptographically Secure Pseudo-Random Number Generator to randomly
     * select the target index. CSPRNG uses OS-level environmental entropy rather than a deterministic
     * mathematical seed, thus ensuring the chosen target Word is completely unpredictable.
     * <p>
     * 
     * @return a randomly selected Word
     * @throws IndexOutOfBoundsException if the selected index is out of range (0, size-1)
     * @throws IllegalArgumentException if the selected Word fails validation
     */
    public Word getRandom() throws IndexOutOfBoundsException, IllegalArgumentException {
        SecureRandom secureRandom = new SecureRandom();
        int index = secureRandom.nextInt(this.answers.size());
        String text = this.answers.get(index);
        return new Word(text);
    }

    /**
     * Determines whether a Word exists in the Dictionary by performing a Binary Search.
     * Hence, determining its validity.
     * <p>
     * 
     * @param word the Word to validate
     * @return true if the word exists, false otherwise
     * @throws IllegalArgumentException if the passed Word is null
     */
    public boolean isValid(Word word) throws IllegalArgumentException {

        if(word == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }

        String target = word.getText();
        int result = this.binarySearch(target);
        return result != -1;

    }

    // ----*---- Helper Methods ----*----

    /**
     * Loads the contents of "answers-alphabetical.txt" into the answers List.
     * <p>
     * The file contains 2,315 alphabetically-ordered words, each on a separate line. Each line is
     * loaded as a single element to the List. It is assumed that the file cannot be empty under any circumstance.
     * <p>
     * 
     * @throws FileNotFoundException if the "answers-alphabetical.txt" file is missing or inaccessible.
     */
    private void loadList() throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("assets/answers-alphabetical.txt"));
        
        try {
            while(scanner.hasNextLine() == true) {
                this.answers.add(scanner.nextLine().trim());
            }
        } finally {
            scanner.close();
        }

    }

    /**
     * Performs an iterative Binary Search to locate a specific word/text within the answers List.
     * <p>
     * By assuming that the List is ordered alphabetically, the algorithm operates in O(log n) time complexity.
     * <p>
     *
     * @param target the raw text to locate
     * @return the index of the target text if found, otherwise -1
     */
    private int binarySearch(String target) {

        int left = 0;
        int right = this.answers.size() - 1;

        while(left <= right) {

            int mid = left + (right - left) / 2;
            String current = this.answers.get(mid);

            if(current.compareToIgnoreCase(target) == 0) {
                return mid;
            } else if(current.compareToIgnoreCase(target) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        
        return -1;

    }
    
}