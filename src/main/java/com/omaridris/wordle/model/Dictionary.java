package com.omaridris.wordle.model;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;

/**
 * Represents a Dictionary containing all valid Words for both game targets and guesses.
 * <p>
 * This class defines the underlying answers List containing the Words' raw Strings,
 * a CSPRNG-based retrieval for a target Word, and O(log n) validation for user guesses.
 * 
 * @author Omar Idris
 */
public class Dictionary {

    // ------*------ Attributes ------*------

    private ArrayList<String> answers;

    /**
     * Instantiates a new Dictionary object.
     * <p>
     * By default, the Dictionary utilizes the original 2,315 curated Wordle answers
     * extracted from the pre-NYT source code, sourced via Cyrus Freshman's GitHub Gist
     * archive, and loads them in the answers List.
     * 
     * @throws FileNotFoundException if the answers asset is missing or inaccessible
     */
    public Dictionary() throws FileNotFoundException {
        this.answers = new ArrayList<>();
        this.loadList();
    }

    // ------*------ Accessing & Validating ------*------

    /**
     * Returns a randomly selected Word from the Dictionary's answers List.
     * <p>
     * This method utilizes a Cryptographically Secure Pseudo-Random Number Generator to
     * randomly select the target index. CSPRNG uses OS-level environmental entropy
     * rather than a deterministic mathematical seed; thus, it ensures the chosen target
     * Word is completely unpredictable.
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
     * Determines whether a Word exists in the Dictionary's answers List by performing
     * a Binary Search; hence, it verifies its validity.
     * 
     * @param word the Word to validate
     * @return true if the Word exists, false otherwise
     * @throws IllegalArgumentException if the passed Word is null
     */
    public boolean isValid(Word word) throws IllegalArgumentException {

        if(word == null) {
            throw new IllegalArgumentException("Word cannot be null.");
        }

        String target = word.getText();
        int result = this.binarySearch(target);
        return result != -1;

    }

    // ------*------ Helper Methods ------*------

    /**
     * Loads the contents of "answers-alphabetical.txt" into the answers List.
     * <p>
     * The file contains 2,315 alphabetically ordered words, each on a separate line.
     * Each line is loaded as a single element to the List. It is assumed that the file
     * cannot be empty under any circumstance.
     * 
     * @throws FileNotFoundException if the answers asset is missing or inaccessible
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
     * Performs an iterative Binary Search to locate a specific Word's raw text within
     * the answers List.
     * <p>
     * Assuming that the List is ordered alphabetically, the algorithm operates in
     * O(log n) time complexity.
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