package com.omaridris.wordle.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.omaridris.wordle.model.Matcher.State;

/**
 * Conducts tests on the methods of {@link Matcher} using JUnit API.
 * <p>
 * Trivial methods (e.g. standard constructors, setters, or getters) are not tested;
 * thus, only methods with non-linear logic will be tested.
 * 
 * @author Omar Idris
 */
public class MatcherTest {

    // ------*------ Testing evaluate(Word, Word) ------*------

    /**
     * Tests {@link Matcher#evaluate(Word, Word)} method to validate the evaluation of
     * guesses with exactly matching letters relative to the target.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario evaluate(Word, Word) is invoked with a fully matching guess
     * @expected the evaluator marks all letters as CORRECT
     */
    @Test
    public void testEvaluateWithMatchingLetterGuess() {

        // Arrange
        Word target = new Word("tulip");
        Word guess = new Word("tulip");
        State[] expectedStates = {State.CORRECT, State.CORRECT, State.CORRECT, State.CORRECT, State.CORRECT};
        
        // Act
        State[] actualStates = Matcher.evaluate(target, guess);
        
        // Assert
        Assertions.assertArrayEquals(expectedStates, actualStates, "A guess with exactly matching letters should have all its states as correct.");
        
    }

    /**
     * Tests {@link Matcher#evaluate(Word, Word)} method to validate the evaluation of
     * guesses with entirely misplaced letters relative to the target.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario evaluate(Word, Word) is invoked with a fully misplaced guess
     * @expected the evaluator marks all letters as MISPLACED
     */
    @Test
    public void testEvaluateWithMisplacedLetterGuess() {

        // Arrange
        Word target = new Word("charm");
        Word guess = new Word("march");
        State[] expectedStates = {State.MISPLACED, State.MISPLACED, State.MISPLACED, State.MISPLACED, State.MISPLACED};
        
        // Act
        State[] actualStates = Matcher.evaluate(target, guess);
        
        // Assert
        Assertions.assertArrayEquals(expectedStates, actualStates, "A guess with entirely misplaced letters should have all its states as misplaced.");
        
    }

    /**
     * Tests {@link Matcher#evaluate(Word, Word)} method to validate the evaluation of
     * guesses with completely mismatching letters relative to the target.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario evaluate(Word, Word) is invoked with a fully mismatching guess
     * @expected the evaluator marks all letters as INCORRECT
     */
    @Test
    public void testEvaluateWithMismatchingLetterGuess() {

        // Arrange
        Word target = new Word("camel");
        Word guess = new Word("bunny");
        State[] expectedStates = {State.INCORRECT, State.INCORRECT, State.INCORRECT, State.INCORRECT, State.INCORRECT};
        
        // Act
        State[] actualStates = Matcher.evaluate(target, guess);
        
        // Assert
        Assertions.assertArrayEquals(expectedStates, actualStates, "A guess with completely mismatching letters should have all its states as incorrect.");
        
    }

    /**
     * Tests {@link Matcher#evaluate(Word, Word)} method to validate the evaluation of
     * guesses with matching, misplaced, and mismatching letters relative to the target.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario evaluate(Word, Word) is invoked with a mixed-state guess
     * @expected the evaluator properly marks letters as CORRECT, MISPLACED, or INCORRECT
     */
    @Test
    public void testEvaluateWithMixedLetterGuess() {

        // Arrange
        Word target = new Word("plant");
        Word guess = new Word("petal");
        State[] expectedStates = {State.CORRECT, State.INCORRECT, State.MISPLACED, State.MISPLACED, State.MISPLACED};
        
        // Act
        State[] actualStates = Matcher.evaluate(target, guess);
        
        // Assert
        Assertions.assertArrayEquals(expectedStates, actualStates, "A guess with mixed-state letters should accurately have its states as correct, misplaced, or incorrect.");
        
    }

    /**
     * Tests {@link Matcher#evaluate(Word, Word)} method to validate the evaluation of
     * guesses with duplicate letters relative to the target, ensuring exact matches are
     * prioritized and character frequencies are exhausted.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario evaluate(Word, Word) is invoked with a duplicate-letter guess
     * @expected the evaluator marks excess duplicate letters as INCORRECT
     */
    @Test
    public void testEvaluateWithDuplicateLetterGuess() {

        // Arrange
        Word target = new Word("eagle");
        Word guess = new Word("geese");
        State[] expectedStates = {State.MISPLACED, State.MISPLACED, State.INCORRECT, State.INCORRECT, State.CORRECT};
        
        // Act
        State[] actualStates = Matcher.evaluate(target, guess);
        
        // Assert
        Assertions.assertArrayEquals(expectedStates, actualStates, "A guess with duplicate letters should prioritize correct matches and exhaust character counts.");

    }

    /**
     * Tests {@link Matcher#evaluate(Word, Word)} method to validate the evaluation of
     * guesses never leaks the internal UNCHECKED State.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario evaluate(Word, Word) is invoked with any valid guess
     * @expected the evaluator guarantees no letter is marked as UNCHECKED
     */
    @Test
    public void testEvaluateDoesNotLeakUncheckedStates() {

        // Arrange
        Word target = new Word("river");
        Word guess = new Word("water");
        State[] expectedStates = {State.INCORRECT, State.INCORRECT, State.INCORRECT, State.CORRECT, State.CORRECT};
        
        // Act
        State[] actualStates = Matcher.evaluate(target, guess);

        // Assert
        Assertions.assertArrayEquals(expectedStates, actualStates, "A fully evaluated guess should never retain an unchecked state.");

    }

    /**
     * Tests {@link Matcher#evaluate(Word, Word)} method to validate the proper handling
     * of null target Words.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario evaluate(Word, Word) is invoked with a null target
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testEvaluateWithNullTarget() {

        // Arrange
        Word target = null;
        Word guess = new Word("apple");
        
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {Matcher.evaluate(target, guess);}, "A null target should result in IllegalArgumentException.");
                
    }

    /**
     * Tests {@link Matcher#evaluate(Word, Word)} method to validate the proper handling
     * of null guess Words.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario evaluate(Word, Word) is invoked with a null guess
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testEvaluateWithNullGuess() {

        // Arrange
        Word target = new Word("mango");
        Word guess = null;
        
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {Matcher.evaluate(target, guess);}, "A null guess should result in IllegalArgumentException.");
                
    }

}