package com.omaridris.wordle.model;

import com.omaridris.wordle.utilities.FrequencyMap;

/**
 * Represents an immutable, 5-letter English Word.
 * <p>
 * This class defines the raw text String and letter Frequency Map of a single Word;
 * thus, it ensures only 5-letter English words are instantiated.
 * <p>
 * It cannot be extended and can only be directly instantiated.
 * 
 * @author Omar Idris
 */
public final class Word {

    // ------*------ Attributes ------*------

    private final String text;
    private final FrequencyMap letterFrequencies;

    /**
     * Instantiates a new Word object.
     * 
     * @param text the English word
     * @throws IllegalArgumentException if text is null or is not a 5-letter English word
     */
    public Word(String text) throws IllegalArgumentException {

        this.validateText(text);
        this.text = text.toUpperCase().trim();

        this.letterFrequencies = new FrequencyMap();
        this.recordFrequencies();

    }

    // ------*------ Getters / Accessors ------*------

    /**
     * Gets the value of the underlying String text.
     * 
     * @return the raw text of the Word
     */
    public String getText() {
        return this.text;
    }

    /**
     * Gets a safe, decoupled copy of the underlying letter Frequency Map.
     * 
     * @return a copy of the letter frequencies of the Word
     */
    public FrequencyMap getLetterFrequencies() {
        return this.letterFrequencies.copy();
    }

    // ------*------ Helper Methods ------*------

    /**
     * Validates the Word's raw text according to specific criteria.
     * <p>
     * The criteria specify that the text must be a valid String object (not null) and
     * must be exactly 5 letters long.
     * 
     * @param text the English word to validate
     * @throws IllegalArgumentException if text is null or is not a 5-letter English word
     */
    private void validateText(String text) throws IllegalArgumentException {

        if(text == null) {
            throw new IllegalArgumentException("Word cannot be null.");
        }

        if(text.trim().length() != 5) {
            throw new IllegalArgumentException("Word must be exactly 5 letters long.");
        }

    }

    /**
     * Iterates over the characters of the Word's raw text and increments their
     * frequencies in the underlying letter Frequency Map.
     * <p>
     * It is mutative as it modifies the map's internal state.
     * 
     * @throws IllegalArgumentException if the character is not an English letter
     */
    private void recordFrequencies() throws IllegalArgumentException {

        char[] characters = this.text.toCharArray();

        for(char character : characters) {
            this.letterFrequencies.increment(character);
        }

    }

}