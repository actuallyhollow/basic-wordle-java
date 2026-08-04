package com.omaridris.wordle.model;

import com.omaridris.wordle.utilities.FrequencyMap;

/**
 * Represents a Word, specifically a 5-letter English word.
 * <p>
 * This class defines the raw text String and letter Frequency Map of a single word;
 * thus, it ensures only 5-letter English words are instantiated.
 * 
 * @author Omar Idris
 */
public class Word {

    // ------*------ Attributes ------*------

    private String text;
    private FrequencyMap frequencyMap;

    /**
     * Instantiates a new Word object.
     * 
     * @param text the English word
     * @throws IllegalArgumentException if text is null or is not a 5-letter English word
     */
    public Word(String text) throws IllegalArgumentException {

        this.validateText(text);
        this.text = text.toUpperCase().trim();

        this.frequencyMap = new FrequencyMap();
        this.populateMap();

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
     * Gets the value of the underlying Frequency Map.
     * 
     * @return the letter Frequency Map of the Word
     */
    public FrequencyMap getFrequencyMap() {
        return this.frequencyMap;
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

        text = text.toUpperCase().trim();

        if(text.length() != 5) {
            throw new IllegalArgumentException("Word must be exactly 5 letters long.");
        }

    }

    /**
     * Iterates over the characters of the Word's raw text and increments their
     * frequencies in the underlying Frequency Map.
     * <p>
     * It is mutative as it modifies the map's internal state.
     * 
     * @throws IllegalArgumentException if the character is not an English letter
     */
    private void populateMap() throws IllegalArgumentException {

        char[] characters = this.text.toCharArray();

        for(char character : characters) {
            this.frequencyMap.increment(character);
        }

    }

}