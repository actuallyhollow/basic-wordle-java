package com.omaridris.wordle.model;

import com.omaridris.wordle.utilities.FrequencyMap;

/**
 * Matches guessed Words against target Words to generate match State feedback.
 * <p>
 * This utility class handles the core evaluation logic of guessed Words. By utilizing
 * the underlying {@link FrequencyMap} data structure, it ensures linear performance and
 * precise letter frequency tracking; thus, it correctly processes duplicate letters.
 * <p>
 * It cannot be extended or instantiated and can only be statically accessed.
 * 
 * @author Omar Idris
 * @see Word
 * @see FrequencyMap
 */
public final class Matcher {

    // ------*------ Nested Types ------*------

    /**
     * Represents a match State for an individual letter in the guessed Word.
     * <p>
     * This enum defines the four possible feedback outcomes to ensure strictly
     * categorized, type-safe match results during evaluation.
     * 
     * @author Omar Idris
     */
    public static enum State {

        /**
         * Indicates a letter that has not been processed by the evaluation algorithm.
         */
        UNCHECKED,

        /**
         * Indicates a letter that matches the exact correct position in the target Word.
         */
        CORRECT,

        /**
         * Indicates a letter that is present in the target Word but placed incorrectly.
         */
        MISPLACED,

        /**
         * Indicates a letter that is absent from the target Word or entirely consumed.
         */
        INCORRECT

    }

    // ------*------ Attributes ------*------

    /**
     * Prevents instantiation of new Matcher objects.
     * 
     * @throws UnsupportedOperationException if instantiated internally or by Reflection.
     */
    private Matcher() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Matcher objects should not be instantiated.");
    }

    // ------*------ State Evaluation ------*------

    /**
     * Evaluates a guessed Word against a target Word to generate their match States.
     * <p>
     * Implements a two-pass evaluation algorithm that processes exact matches before
     * positional mismatches. By utilizing Frequency Map's O(1) access time, it operates
     * in O(n) time complexity. Additionally, the map's precise tracking ensures
     * duplicate letters receive accurate feedback.
     * 
     * @param target the correct target Word
     * @param guess the guessed Word to evaluate
     * @return an array of match States corresponding to each letter in the guess
     * @throws IllegalArgumentException if either passed Words is null
     */
    public static State[] evaluate(Word target, Word guess) throws IllegalArgumentException {

        if(target == null) {
            throw new IllegalArgumentException("Target word cannot be null.");
        }

        if(guess == null) {
            throw new IllegalArgumentException("Guessed word cannot be null.");
        }

        char[] targetLetters = target.getText().toCharArray();
        char[] guessLetters = guess.getText().toCharArray();
        State[] states = new State[targetLetters.length];
        FrequencyMap targetFrequencies = target.getLetterFrequencies();

        Matcher.evaluateMatches(targetLetters, guessLetters, states, targetFrequencies);
        Matcher.evaluateMismatches(targetLetters, guessLetters, states, targetFrequencies);

        return states;

    }

    // ------*------ Helper Methods ------*------

    /**
     * Performs the first evaluation pass to identify exact letter matches.
     * <p>
     * By comparing each letter against its target, it marks positional matches as
     * CORRECT, decrements their frequency, and temporarily marks the rest as UNCHECKED.
     * 
     * @param target the character array of the target Word
     * @param guess the character array of the guessed Word
     * @param states the State array storing match feedback
     * @param frequencies the Frequency Map of the target Word
     */
    private static void evaluateMatches(char[] target, char[] guess, State[] states, FrequencyMap frequencies) {

        for(int i = 0; i < target.length; i++) {
            if(target[i] == guess[i]) {
                states[i] = State.CORRECT;
                frequencies.decrement(guess[i]);
            } else {
                states[i] = State.UNCHECKED;
            }
        }

    }

    /**
     * Performs the second evaluation pass to identify positional letter mismatches.
     * <p>
     * By checking each UNCHECKED letter's frequency, it marks letters present in the map
     * as MISPLACED, decrements their frequency, and finally marks the rest as INCORRECT.
     * 
     * @param target the character array of the target Word
     * @param guess the character array of the guessed Word
     * @param states the State array storing match feedback
     * @param frequencies the Frequency Map of the target Word
     */
    private static void evaluateMismatches(char[] target, char[] guess, State[] states, FrequencyMap frequencies) {

        for(int i = 0; i < target.length; i++) {
            if(states[i] == State.UNCHECKED) {
                if(frequencies.getFrequency(guess[i]) > 0) {
                    states[i] = State.MISPLACED;
                    frequencies.decrement(guess[i]);
                } else {
                    states[i] = State.INCORRECT;
                }
                
            }
        }

    }

}