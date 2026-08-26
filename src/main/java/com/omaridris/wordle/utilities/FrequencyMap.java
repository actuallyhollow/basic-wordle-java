package com.omaridris.wordle.utilities;

/**
 * Defines a custom, highly specialized implementation of an array-based Hash Map.
 * <p>
 * This Data Structure specializes in tracking the frequencies of English letters;
 * thus, it achieves O(1) time complexity for all operations using direct array indexing.
 * <p>
 * It cannot be extended and can only be directly instantiated.
 * 
 * @author Omar Idris
 */
public final class FrequencyMap {

    // ------*------ Attributes ------*------

    private static final int ALPHABET_SIZE = 26;
    private int[] frequencies;

    /**
     * Instantiates a new Frequency Map object.
     * <p>
     * By default, the underlying array's size is fixed to 26 indices, representing the
     * English alphabet, with all letter frequencies initialized to 0.
     */
    public FrequencyMap() {
        this.frequencies = new int[FrequencyMap.ALPHABET_SIZE];
    }

    // ------*------ Frequency Modification ------*------

    /**
     * Increases the frequency of a character by 1 in a Frequency Map.
     * <p>
     * It is mutative as it modifies the map's internal state.
     * 
     * @param character the English letter to increment
     * @throws IllegalArgumentException if the character is not an English letter
     */
    public void increment(char character) throws IllegalArgumentException {
        int index = this.getIndex(character);
        this.frequencies[index]++;
    }

    /**
     * Decreases the frequency of a character by 1 in a Frequency Map.
     * <p>
     * It is mutative as it modifies the map's internal state.
     * 
     * @param character the English letter to decrement
     * @throws IllegalArgumentException if the character is not an English letter
     * @throws IllegalStateException if the character has a frequency of 0
     */
    public void decrement(char character) throws IllegalArgumentException, IllegalStateException {

        int index = this.getIndex(character);
        
        if(this.frequencies[index] <= 0) {
            throw new IllegalStateException("Cannot decrement: No occurrences of \'" + character + "\' left.");
        } else {
            this.frequencies[index]--;
        }

    }

    // ------*------ Frequency Access ------*------

    /**
     * Returns the frequency of a character in a Frequency Map.
     * <p>
     * It is non-mutative as it does not modify the map's internal state.
     * 
     * @param character the English letter to access
     * @return the frequency of the character
     * @throws IllegalArgumentException if the character is not an English letter
     */
    public int getFrequency(char character) throws IllegalArgumentException {
        int index = this.getIndex(character);
        return this.frequencies[index];
    }

    // ------*------ State Operations ------*------

    /**
     * Checks if a Frequency Map has no frequencies greater than 0.
     * <p>
     * It is non-mutative as it does not modify the map's internal state.
     * 
     * @return true if all character frequencies are 0, false otherwise 
     */
    public boolean isEmpty() {

        for(int frequency : this.frequencies) {
            if(frequency > 0) {
                return false;
            }
        }
        return true;

    }

    /**
     * Sets all frequencies to 0 in a Frequency Map.
     * <p>
     * It is mutative as it modifies the map's internal state.
     */
    public void clear() {
        this.frequencies = new int[FrequencyMap.ALPHABET_SIZE];
    }

    /**
     * Instantiates and returns a deep copy of a Frequency Map.
     * <p>
     * A deep copy is a completely decoupled duplicate with an entirely new reference;
     * thus any modifications to the copy will not affect the original map.
     * <p>
     * It is non-mutative as it does not modify the map's internal state.
     * 
     * @return a new FrequencyMap object with identical character frequencies
     */
    public FrequencyMap copy() {
        FrequencyMap copy = new FrequencyMap();
        copy.frequencies = this.frequencies.clone();
        return copy;
    }

    // ------*------ Helper Methods ------*------

    /**
     * Computes the array index of a character in a Frequency Map.
     * <p>
     * The unique index is obtained by subtracting the ASCII value of an uppercase 'A'
     * from the ASCII value of the character.
     * 
     * @param character the English letter to compute its index
     * @return the computed array index (0-25)
     * @throws IllegalArgumentException if the character is not an English letter
     */
    private int getIndex(char character) throws IllegalArgumentException {

        int index = Character.toUpperCase(character) - 'A';

        if((index < 0) || (index > FrequencyMap.ALPHABET_SIZE - 1)) {
            throw new IllegalArgumentException("Character \'" + character + "\' must be an English letter.");
        } else {
            return index;
        }

    }

}