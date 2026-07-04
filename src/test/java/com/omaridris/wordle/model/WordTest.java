package com.omaridris.wordle.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.omaridris.wordle.utilities.FrequencyMap;

/**
 * Conducts tests on the methods of {@link Word} using JUnit API.
 * <p>
 * Trivial methods (e.g. standard constructors, setters, getters) are not tested.
 * Only methods with non-linear logic will be tested.
 * 
 * @author Omar Idris
 */
public class WordTest {

    // ----*---- Testing Instantiation ----*----

    /**
     * Tests {@link Word#Word(String)} constructor to validate proper instantiation on
     * valid text (non-null String of length 5).
     * <p>
     * Tests are conducted using the AAA pattern (arrange, act, assert).
     * <ul><li>
     *   <b>Scenario:</b>
     *     <ul><li>
     *       Word() is invoked with a valid text.
     *     </li></ul>
     * </li></ul>
     * <ul><li>
     *   <b>Expected:</b>
     *     <ul><li>
     *       a Word object is instantiated with the valid text and its character frequencies as attributes.
     *     </li></ul>
     * </li></ul>
     */
    @Test
    public void testInstantiationOnValidText() {

        // Arrange
        String validText = "array";
        String expectedText = validText.toUpperCase().trim();
        int expectedFrequencyOfA = 2;
        int expectedFrequencyOfR = 2;
        int expectedFrequencyOfY = 1;
        int expectedFrequencyOfZ = 0;

        // Act
        Word word = new Word(validText);
        String actualText = word.getText();
        FrequencyMap frequencies = word.getFrequencyMap();
        int actualFrequencyOfA = frequencies.getFrequency('A');
        int actualFrequencyOfR = frequencies.getFrequency('R');
        int actualFrequencyOfY = frequencies.getFrequency('Y');
        int actualFrequencyOfZ = frequencies.getFrequency('Z');

        // Assert
        Assertions.assertNotNull(word, "A word object with a valid text should not be null.");
        Assertions.assertEquals(expectedText, actualText, "Text attribute should equal the passed capitalized text.");
        Assertions.assertEquals(expectedFrequencyOfA, actualFrequencyOfA, "Frequency of character 'A' should be exactly 2.");
        Assertions.assertEquals(expectedFrequencyOfR, actualFrequencyOfR, "Frequency of character 'R' should be exactly 2.");
        Assertions.assertEquals(expectedFrequencyOfY, actualFrequencyOfY, "Frequency of character 'Y' should be exactly 1.");
        Assertions.assertEquals(expectedFrequencyOfZ, actualFrequencyOfZ, "Frequency of an absent character should be exactly 0.");

    }

    /**
     * Tests {@link Word#Word(String)} constructor to validate proper instantiation on invalid null text.
     * <p>
     * Tests are conducted using the AAA pattern (arrange, act, assert).
     * <ul><li>
     *   <b>Scenario:</b>
     *     <ul><li>
     *       Word() is invoked with a null text.
     *     </li></ul>
     * </li></ul>
     * <ul><li>
     *   <b>Expected:</b>
     *     <ul><li>
     *       IllegalArgumentException is thrown.
     *     </li></ul>
     * </li></ul>
     */
    @Test
    public void testInstantiationOnNullText() {

        // Arrange
        String nullText = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Word(nullText);}, "Invalid null text should result in IllegalArgumentException.");

    }

    /**
     * Tests {@link Word#Word(String)} constructor to validate proper instantiation on
     * invalid text (String of length not equal to 5).
     * <p>
     * Tests are conducted using the AAA pattern (arrange, act, assert).
     * <ul><li>
     *   <b>Scenario:</b>
     *     <ul><li>
     *       Word() is invoked with an invalid text.
     *     </li></ul>
     * </li></ul>
     * <ul><li>
     *   <b>Expected:</b>
     *     <ul><li>
     *       IllegalArgumentException is thrown.
     *     </li></ul>
     * </li></ul>
     */
    @Test
    public void testInstantiationOnInvalidText() {

        // Arrange
        String invalidText = "pointer";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Word(invalidText);}, "Invalid text of length not equal to 5 should result in IllegalArgumentException.");

    }

}