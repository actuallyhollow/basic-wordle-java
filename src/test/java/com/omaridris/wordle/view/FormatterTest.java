package com.omaridris.wordle.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.github.stefanbirkner.systemlambda.SystemLambda;
import com.omaridris.wordle.utilities.Ansi;
import com.omaridris.wordle.utilities.Rgb;

/**
 * Conducts tests on the methods of {@link Formatter} using JUnit API,
 * and System Lambda API to intercept console output.
 * <p>
 * Trivial methods (e.g. hardcoded output and direct print statements) are not tested;
 * thus, only parameterized methods with formatting logic or user input will be tested.
 * 
 * @author Omar Idris
 */
public class FormatterTest {

    // ------*------ Test Constants (Hardcoded for strict assertions) ------*------
    
    private static final Rgb GREEN = new Rgb(65, 115, 60);
    private static final Rgb YELLOW = new Rgb(155, 135, 50);
    private static final Rgb GRAY = new Rgb(50, 50, 50);
    private static final Rgb WHITE = new Rgb(255, 255, 255);

    // ------*------ Testing printCorrectLetterToken(char) ------*------

    /**
     * Tests {@link Formatter#printCorrectLetterToken(char)} method to validate that
     * correct letters are formatted with a green background and a white foreground.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printCorrectLetterToken(char) is invoked with an arbitrary character
     * @expected the intercepted console output injects and formats the character
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintCorrectLetterTokenWithCharacter() throws Exception {

        // Arrange
        char correctLetter = 'A';
        String activeColors = Ansi.getBackgroundSequence(FormatterTest.GREEN) + Ansi.getForegroundSequence(FormatterTest.WHITE);
        String expectedOutput = activeColors + "[" + correctLetter + "]" + Ansi.RESET;
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Formatter.printCorrectLetterToken(correctLetter);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "A correct letter should be formatted with a green background and a white foreground.");
        
    }

    // ------*------ Testing printMisplacedLetterToken(char) ------*------

    /**
     * Tests {@link Formatter#printMisplacedLetterToken(char)} method to validate that
     * misplaced letters are formatted with a yellow background and a white foreground.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printMisplacedLetterToken(char) is invoked with an arbitrary character
     * @expected the intercepted console output injects and formats the character
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintMisplacedLetterTokenWithCharacter() throws Exception {

        // Arrange
        char misplacedLetter = 'L';
        String activeColors = Ansi.getBackgroundSequence(FormatterTest.YELLOW) + Ansi.getForegroundSequence(FormatterTest.WHITE);
        String expectedOutput = activeColors + "[" + misplacedLetter + "]" + Ansi.RESET;
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Formatter.printMisplacedLetterToken(misplacedLetter);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "A misplaced letter should be formatted with a yellow background and a white foreground.");
        
    }

    // ------*------ Testing printIncorrectLetterToken(char) ------*------

    /**
     * Tests {@link Formatter#printIncorrectLetterToken(char)} method to validate that
     * incorrect letters are formatted with a gray background and a white foreground.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printIncorrectLetterToken(char) is invoked with an arbitrary character
     * @expected the intercepted console output injects and formats the character
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintIncorrectLetterTokenWithCharacter() throws Exception {

        // Arrange
        char incorrectLetter = 'E';
        String activeColors = Ansi.getBackgroundSequence(FormatterTest.GRAY) + Ansi.getForegroundSequence(FormatterTest.WHITE);
        String expectedOutput = activeColors + "[" + incorrectLetter + "]" + Ansi.RESET;
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Formatter.printIncorrectLetterToken(incorrectLetter);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "An incorrect letter should be formatted with a gray background and a white foreground.");
        
    }

    // ------*------ Testing printAttemptsMessage(int) ------*------

    /**
     * Tests {@link Formatter#printAttemptsMessage(int)} method to validate that
     * remaining attempt counts are correctly formatted into the message.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printAttemptsMessage(int) is invoked with an arbitrary integer
     * @expected the intercepted console output injects and formats the integer
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintAttemptsMessageWithInteger() throws Exception {
        
        // Arrange
        int remainingAttempts = 3;
        String expectedOutput = " *  You have (" + remainingAttempts + ") attempts remaining.\n";
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Formatter.printAttemptsMessage(remainingAttempts);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The remaining attempts count should be correctly formatted.");
        
    }

    // ------*------ Testing printWinMessage(int) ------*------

    /**
     * Tests {@link Formatter#printWinMessage(int)} method to validate that victory
     * attempt counts are correctly formatted into the message.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printWinMessage(int) is invoked with an arbitrary integer
     * @expected the intercepted console output injects and formats the integer
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintWinMessageWithInteger() throws Exception {
        
        // Arrange
        int usedAttempts = 3;
        String expectedOutput = " *  Splendid! You guessed correctly in (" + usedAttempts + ") attempts.\n";
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Formatter.printWinMessage(usedAttempts);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The used attempts count should be correctly formatted.");
        
    }

    // ------*------ Testing printLossMessage(String) ------*------

    /**
     * Tests {@link Formatter#printLossMessage(String)} method to validate that target
     * words are correctly formatted into the message.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printLossMessage(String) is invoked with an arbitrary String
     * @expected the intercepted console output injects and formats the String
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintLossMessageWithString() throws Exception {

        // Arrange
        String target = "album";
        String expectedOutput = " *  The correct word was \"" + target + "\". Better luck next time!\n";
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Formatter.printLossMessage(target);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The hidden target word should be correctly formatted.");

    }

    // ------*------ Testing printErrorMessage(String) ------*------

    /**
     * Tests {@link Formatter#printErrorMessage(String)} method to validate that custom
     * error descriptions are correctly formatted into the message.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printErrorMessage(String) is invoked with an arbitrary String
     * @expected the intercepted console output injects and formats the String
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintErrorMessageWithString() throws Exception {

        // Arrange
        String message = "Word must be exactly 5 letters long.";
        String expectedOutput = " : " + message + " Try another guess!\n";
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Formatter.printErrorMessage(message);});

        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The error description message should be correctly formatted.");

    }

    // ------*------ Testing printCrashMessage(String) ------*------

    /**
     * Tests {@link Formatter#printCrashMessage(String)} method to validate that system
     * crash descriptions are correctly formatted into the message.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printCrashMessage(String) is invoked with an arbitrary String
     * @expected the intercepted console output injects and formats the String
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintCrashMessageWithString() throws Exception {

        // Arrange
        String message = "assets/answers.txt (No such file or directory)";
        String expectedOutput = " : " + message + " Shutting down application...\n";
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Formatter.printCrashMessage(message);});

        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The crash description message should be correctly formatted.");

    }

}