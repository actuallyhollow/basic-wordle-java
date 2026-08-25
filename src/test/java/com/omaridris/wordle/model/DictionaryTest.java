package com.omaridris.wordle.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Conducts tests on the methods of {@link Dictionary} using JUnit API.
 * <p>
 * Trivial methods (e.g. standard constructors, setters, or getters) are not tested;
 * thus, only methods with non-linear logic will be tested.
 * 
 * @author Omar Idris
 */
public class DictionaryTest {

    // ------*------ Testing Dictionary() ------*------

    /**
     * Tests {@link Dictionary#Dictionary()} constructor to validate the proper
     * instantiation and loading of the answers file.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario Dictionary() is invoked
     * @expected a Dictionary object is instantiated with all target Words loaded
     */
    @Test
    public void testInstantiation() {

        // Arrange, Act & Assert
        Dictionary dictionary = Assertions.assertDoesNotThrow(() -> {return new Dictionary();}, "The answers file should be loaded successfully.");
        Assertions.assertNotNull(dictionary, "Dictionary should be created successfully.");

    }

    // ------*------ Testing getRandom() ------*------

    /**
     * Tests {@link Dictionary#getRandom()} method to validate that the retrieval returns
     * a valid Word from the Dictionary.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario getRandom() is invoked
     * @expected a valid, randomly selected Word is returned
     */
    @Test
    public void testGetRandomReturnsValidWord() {

        // Arrange
        Dictionary dictionary = Assertions.assertDoesNotThrow(() -> {return new Dictionary();});
        boolean expectedResult = true;

        // Act
        Word randomWord = dictionary.getRandom();
        boolean actualResult = dictionary.isValid(randomWord);

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The randomly generated word must exist in the dictionary.");

    }
    
    /**
     * Tests {@link Dictionary#getRandom()} method to validate that the retrieval is
     * random and unpredictable.
     * <p>
     * While it is technically possible for CSPRNG to select the same Word twice in a
     * row, the odds are 1 in 2,315, or ~0.0432%.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario getRandom() is invoked twice
     * @expected two different, randomly selected Words are returned
     */
    @Test
    public void testGetRandomIsUnpredictable() {
        
        // Arrange
        Dictionary dictionary = Assertions.assertDoesNotThrow(() -> {return new Dictionary();});
        boolean expectedEquality = false;

        // Act
        Word word1 = dictionary.getRandom();
        Word word2 = dictionary.getRandom();
        boolean actualEquality = word1.getText().equalsIgnoreCase(word2.getText());

        // Assert
        Assertions.assertEquals(expectedEquality, actualEquality, "Consecutive random pulls should ideally differ.");

    }
    
    // ------*------ Testing isValid(Word) ------*------

    /**
     * Tests {@link Dictionary#isValid(Word)} method to validate the lookup of valid
     * Words in the Dictionary.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario isValid(Word) is invoked with a valid Word
     * @expected a true boolean is returned, indicating that the Word is indeed valid
     */
    @Test
    public void testIsValidWithValidWord() {

        // Arrange
        Dictionary dictionary = Assertions.assertDoesNotThrow(() -> {return new Dictionary();});
        Word validWord = new Word("comic");
        boolean expectedResult = true;

        // Act
        boolean actualResult = dictionary.isValid(validWord);

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "A valid word must be present in the dictionary.");

    }


    /**
     * Tests {@link Dictionary#isValid(Word)} method to validate the absence of invalid
     * Words in the Dictionary.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario isValid(Word) is invoked with an invalid Word
     * @expected a false boolean is returned, indicating that the Word is indeed invalid
     */
    @Test
    public void testIsValidWithInvalidWord() {

        // Arrange
        Dictionary dictionary = Assertions.assertDoesNotThrow(() -> {return new Dictionary();});
        Word invalidWord = new Word("zzzzz");
        boolean expectedResult = false;

        // Act
        boolean actualResult = dictionary.isValid(invalidWord);

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "An invalid word cannot be present in the dictionary.");

    }

    /**
     * Tests {@link Dictionary#isValid(Word)} method to validate the lookup of valid
     * Words in the Dictionary, regardless of their case.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario isValid(Word) is invoked with valid Words of different cases
     * @expected a true boolean is returned, indicating that Word case is irrelevant
     */
    @Test
    public void testIsValidWithMixedCaseWord() {

        // Arrange
        Dictionary dictionary = Assertions.assertDoesNotThrow(() -> {return new Dictionary();});
        Word word1 = new Word("apple");
        Word word2 = new Word("APPLE");
        Word word3 = new Word("ApPLe");
        boolean expectedResult1 = true;
        boolean expectedResult2 = true;
        boolean expectedResult3 = true;

        // Act
        boolean actualResult1 = dictionary.isValid(word1);
        boolean actualResult2 = dictionary.isValid(word2);
        boolean actualResult3 = dictionary.isValid(word3);

        // Assert
        Assertions.assertEquals(expectedResult1, actualResult1, "A valid lowercase word should be located in the dictionary.");
        Assertions.assertEquals(expectedResult2, actualResult2, "A valid uppercase word should be located in the dictionary.");
        Assertions.assertEquals(expectedResult3, actualResult3, "A valid mixed-case word should be located in the dictionary.");

    }

    /**
     * Tests {@link Dictionary#isValid(Word)} method to validate the proper handling of
     * null Words.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario isValid(Word) is invoked with a null Word
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testIsValidWithNullWord() {

        // Arrange
        Dictionary dictionary = Assertions.assertDoesNotThrow(() -> {return new Dictionary();});
        Word nullWord = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {dictionary.isValid(nullWord);}, "An invalid null word should result in IllegalArgumentException.");

    }

}