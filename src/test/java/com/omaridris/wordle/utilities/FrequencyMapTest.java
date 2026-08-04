package com.omaridris.wordle.utilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Conducts tests on the methods of {@link FrequencyMap} using JUnit API.
 * <p>
 * Trivial methods (e.g. standard constructors, setters, or getters) are not tested;
 * thus, only methods with non-linear logic will be tested.
 * 
 * @author Omar Idris
 */
public class FrequencyMapTest {

    // ------*------ Testing increment(char) ------*------

    /**
     * Tests {@link FrequencyMap#increment(char)} method to validate that frequency
     * increases on uppercase letters.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario increment(char) is invoked with an uppercase letter
     * @expected the frequency of the letter increases by 1
     */
    @Test
    public void testIncrementOnUppercaseLetter() {

        // Arrange
        FrequencyMap frequencyMap = new FrequencyMap();
        char uppercaseLetter = 'A';
        int expectedFrequency = 1;

        // Act
        frequencyMap.increment(uppercaseLetter);
        int actualFrequency = frequencyMap.getFrequency(uppercaseLetter);

        // Assert
        Assertions.assertEquals(expectedFrequency, actualFrequency, "Uppercase 'A' should have a frequency of 1 after incrementing once.");

    }

    /**
     * Tests {@link FrequencyMap#increment(char)} method to validate that frequency
     * increases on lowercase letters.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario increment(char) is invoked with a lowercase letter
     * @expected the frequency of the letter increases by 1
     */
    @Test
    public void testIncrementOnLowercaseLetter() {

        // Arrange
        FrequencyMap frequencyMap = new FrequencyMap();
        char lowercaseLetter = 'a';
        int expectedFrequency = 1;

        // Act
        frequencyMap.increment(lowercaseLetter);
        int actualFrequency = frequencyMap.getFrequency(lowercaseLetter);

        // Assert
        Assertions.assertEquals(expectedFrequency, actualFrequency, "Lowercase 'a' should have a frequency of 1 after incrementing once.");

    }

    /**
     * Tests {@link FrequencyMap#increment(char)} method to validate the proper handling
     * of invalid characters.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario increment(char) is invoked with an invalid character (non-letter)
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testIncrementOnInvalidCharacter() {

        // Arrange
        FrequencyMap frequencyMap = new FrequencyMap();
        char invalidCharacter = '$';

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {frequencyMap.increment(invalidCharacter);}, "Invalid character '$' should result in IllegalArgumentException.");

    }

    // ------*------ Testing decrement(char) ------*------

    /**
     * Tests {@link FrequencyMap#decrement(char)} method to validate that frequency
     * decreases on uppercase letters.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario decrement(char) is invoked with an uppercase letter
     * @expected the frequency of the letter decreases by 1
     */
    @Test
    public void testDecrementOnUppercaseLetter() {

        // Arrange
        FrequencyMap frequencyMap = new FrequencyMap();
        char uppercaseLetter = 'A';
        int expectedFrequency = 0;

        // Act
        frequencyMap.increment(uppercaseLetter);
        frequencyMap.decrement(uppercaseLetter);
        int actualFrequency = frequencyMap.getFrequency(uppercaseLetter);

        // Assert
        Assertions.assertEquals(expectedFrequency, actualFrequency, "Uppercase 'A' should have a frequency of 0 after decrementing once.");


    }

    /**
     * Tests {@link FrequencyMap#decrement(char)} method to validate that frequency
     * decreases on lowercase letters.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario decrement(char) is invoked with a lowercase letter
     * @expected the frequency of the letter decreases by 1
     */
    @Test
    public void testDecrementOnLowercaseLetter() {

        // Arrange
        FrequencyMap frequencyMap = new FrequencyMap();
        char lowercaseLetter = 'a';
        int expectedFrequency = 0;

        // Act
        frequencyMap.increment(lowercaseLetter);
        frequencyMap.decrement(lowercaseLetter);
        int actualFrequency = frequencyMap.getFrequency(lowercaseLetter);

        // Assert
        Assertions.assertEquals(expectedFrequency, actualFrequency, "Lowercase 'a' should have a frequency of 0 after decrementing once.");


    }

    /**
     * Tests {@link FrequencyMap#decrement(char)} method to validate the proper handling
     * of decreasing letters that have no recorded frequency.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario decrement(char) is invoked with a zero-frequency letter
     * @expected IllegalStateException is thrown
     */
    @Test
    public void testDecrementOnLetterWithNoFrequency() {

        // Arrange
        FrequencyMap frequencyMap = new FrequencyMap();
        char letter = 'A';

        // Act & Assert
        Assertions.assertThrows(IllegalStateException.class, () -> {frequencyMap.decrement(letter);}, "A letter with no frequency should result in IllegalStateException after decreasing.");

    }

    /**
     * Tests {@link FrequencyMap#decrement(char)} method to validate the proper handling
     * of invalid characters.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario decrement(char) is invoked with an invalid character (non-letter)
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testDecrementOnInvalidCharacter() {

        // Arrange
        FrequencyMap frequencyMap = new FrequencyMap();
        char invalidCharacter = '$';

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {frequencyMap.decrement(invalidCharacter);}, "Invalid character '$' should result in IllegalArgumentException.");

    }

    // ------*------ Testing getFrequency(char) ------*------

    /**
     * Tests {@link FrequencyMap#getFrequency(char)} method to validate frequency access
     * on uppercase letters.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario getFrequency(char) is invoked with an uppercase letter
     * @expected the frequency of the letter is returned
     */
    @Test
    public void testGetFrequencyOnUppercaseLetter() {

        // Arrange
        FrequencyMap frequencyMap = new FrequencyMap();
        char uppercaseLetter = 'A';
        int expectedFrequency = 0;

        // Act
        int actualFrequency = frequencyMap.getFrequency(uppercaseLetter);

        // Assert
        Assertions.assertEquals(expectedFrequency, actualFrequency, "Uppercase 'A' is not recorded and should have a frequency of 0.");

    }

    /**
     * Tests {@link FrequencyMap#getFrequency(char)} method to validate frequency access
     * on lowercase letters.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario getFrequency(char) is invoked with a lowercase letter
     * @expected the frequency of the letter is returned
     */
    @Test
    public void testGetFrequencyOnLowercaseLetter() {

        // Arrange
        FrequencyMap frequencyMap = new FrequencyMap();
        char lowercaseLetter = 'a';
        int expectedFrequency = 0;

        // Act
        int actualFrequency = frequencyMap.getFrequency(lowercaseLetter);

        // Assert
        Assertions.assertEquals(expectedFrequency, actualFrequency, "Lowercase 'a' is not recorded and should have a frequency of 0.");

    }

    /**
     * Tests {@link FrequencyMap#getFrequency(char)} method to validate the proper handling
     * of invalid characters.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario getFrequency(char) is invoked with an invalid character (non-letter)
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testGetFrequencyOnInvalidCharacter() {

        // Arrange
        FrequencyMap frequencyMap = new FrequencyMap();
        char invalidCharacter = '$';

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {frequencyMap.getFrequency(invalidCharacter);}, "Invalid character '$' should result in IllegalArgumentException.");

    }

    // ------*------ Testing isEmpty() ------*------

    /**
     * Tests {@link FrequencyMap#isEmpty()} method to validate that unpopulated Frequency
     * Maps are indeed empty.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario isEmpty() is invoked on an empty map
     * @expected a true boolean is returned
     */
    @Test
    public void testIsEmptyOnUnpopulatedMap() {

        // Arrange
        FrequencyMap frequencyMap = new FrequencyMap();
        boolean expectedResult = true;

        // Act
        boolean actualResult = frequencyMap.isEmpty();

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "An empty map should have all frequencies set to 0.");

    }

    /**
     * Tests {@link FrequencyMap#isEmpty()} method to validate that populated Frequency
     * Maps are indeed non-empty.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario isEmpty() is invoked on a non-empty map
     * @expected a false boolean is returned
     */
    @Test
    public void testIsEmptyOnPopulatedMap() {

        // Arrange
        FrequencyMap frequencyMap = new FrequencyMap();
        boolean expectedResult = false;
        char letter = 'A';

        // Act
        frequencyMap.increment(letter);
        boolean actualResult = frequencyMap.isEmpty();

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "A non-empty map should have at least one frequency greater than 0.");

    }

    // ------*------ Testing clear() ------*------

    /**
     * Tests {@link FrequencyMap#clear()} method to validate that unpopulated Frequency
     * Maps are cleared properly.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario clear() is invoked on an already-empty map
     * @expected the map remains empty
     */
    @Test
    public void testClearOnUnpopulatedMap() {

        // Arrange
        FrequencyMap frequencyMap = new FrequencyMap();
        boolean expectedResult = true;

        // Act
        frequencyMap.clear();
        boolean actualResult = frequencyMap.isEmpty();

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "An empty map should stay empty after clearing.");

    }

    /**
     * Tests {@link FrequencyMap#clear()} method to validate that populated Frequency
     * Maps are cleared properly.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario clear() is invoked on a non-empty map
     * @expected the map becomes empty, and all frequencies are set to 0
     */
    @Test
    public void testClearOnPopulatedMap() {

        // Arrange
        FrequencyMap frequencyMap = new FrequencyMap();
        boolean expectedResult = true;
        int expectedFrequency = 0;
        char letter = 'A';

        // Act
        frequencyMap.increment(letter);
        frequencyMap.clear();
        boolean actualResult = frequencyMap.isEmpty();
        int actualFrequency = frequencyMap.getFrequency(letter);

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "A non-empty map should become empty after clearing.");
        Assertions.assertEquals(expectedFrequency, actualFrequency, "The frequency of any letter should be 0 after clearing.");

    }

}