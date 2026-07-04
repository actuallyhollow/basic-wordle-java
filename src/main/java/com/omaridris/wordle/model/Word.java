package com.omaridris.wordle.model;

import com.omaridris.wordle.utilities.FrequencyMap;

/**
 * Represents a Word, specifically a 5-letter English word.
 * <p>
 * This class defines the raw text and letter frequency map of a single word, ensuring
 * only 5-letter English words are instantiated.
 * 
 * @author Omar Idris
 */
public class Word {

    // ----*---- Attributes ----*----

    private String text;
    private FrequencyMap frequencyMap;

    /**
     * Instantiates a new Word object.
     * <p>
     * 
     * @param text the English word.
     * @throws IllegalArgumentException if the passed text is null, or is not a 5-letter English word
     */
    public Word(String text) throws IllegalArgumentException {

        this.validateText(text);
        this.text = text.toUpperCase().trim();

        this.frequencyMap = new FrequencyMap();
        this.populateMap();

    }

    // ----*---- Getters / Accessors ----*----

    /**
     * Gets the value of text.
     * 
     * @return The text of the Word
     */
    public String getText() {
        return this.text;
    }

    /**
     * Gets the value of frequency map.
     * 
     * @return The frequency map of the Word
     */
    public FrequencyMap getFrequencyMap() {
        return this.frequencyMap;
    }

    // ----*---- Helper Methods ----*----

    /**
     * Validates the Word's text according to a certain criteria.
     * <p>
     * The criteria is that the text must be a valid object (not null), and must be exactly 5 letters long.
     * <p>
     * 
     * @param text the English Word to validate
     * @throws IllegalArgumentException if the passed text is null, or is not a 5-letter English word
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
     * Iterates over the Word's characters and increment their frequencies.
     * <p>
     * It is mutative as it modifies the map's internal state.
     * <p>
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