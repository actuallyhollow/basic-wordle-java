package com.omaridris.wordle.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.github.stefanbirkner.systemlambda.SystemLambda;
import com.omaridris.wordle.utilities.Ansi;
import com.omaridris.wordle.utilities.Rgb;

/**
 * Conducts tests on the methods of {@link Display} using JUnit API,
 * and System Lambda API to intercept console output.
 * <p>
 * Trivial methods (e.g. hardcoded output and direct print statements) are not tested;
 * thus, only parameterized methods with formatting logic or user input will be tested.
 * 
 * @author Omar Idris
 */
public class DisplayTest {

    // ------*------ Test Constants (Hardcoded for strict UI assertions) ------*------
    
    private static final Rgb GREEN = new Rgb(65, 115, 60);
    private static final Rgb YELLOW = new Rgb(155, 135, 50);
    private static final Rgb GRAY = new Rgb(50, 50, 50);
    private static final Rgb RED = new Rgb(160, 50, 50);

    // ------*------ Testing printGuessPrompt() ------*------

    /**
     * Tests {@link Display#printGuessPrompt()} method to validate that it successfully
     * captures standard console input.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printGuessPrompt() is invoked while an arbitrary text String is injected
     *           into the standard input stream
     * @expected the returned String is strictly equal to the injected console input
     * @throws Exception if the System Lambda injection fails
     */
    @Test
    public void testPrintGuessPromptCapturesInput() throws Exception {

        // Arrange
        String input = "apple";
        String expectedOutput = input;
        
        // Act & Assert
        SystemLambda.withTextFromSystemIn(input).execute(() -> {
            Display display = new Display();
            String actualOutput = display.printGuessPrompt();
            display.closeIO();
            Assertions.assertEquals(expectedOutput, actualOutput, "The Scanner should accurately capture the given input.");
        });

    }

    /**
     * Tests {@link Display#printGuessPrompt()} method to validate that console input is
     * sanitized and trimmed before returning it to the caller.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printGuessPrompt() is invoked while an arbitrary text String containing
     *           whitespaces is injected into the standard input stream
     * @expected the returned String is stripped of leading and trailing whitespaces
     * @throws Exception if the System Lambda injection fails
     */
    @Test
    public void testPrintGuessPromptTrimsWhitespace() throws Exception {

        // Arrange
        String whitespaceInput = " apple ";
        String expectedOutput = whitespaceInput.trim();
        
        // Act & Assert
        SystemLambda.withTextFromSystemIn(whitespaceInput).execute(() -> {
            Display display = new Display();
            String actualOutput = display.printGuessPrompt();
            display.closeIO();
            Assertions.assertEquals(expectedOutput, actualOutput, "The input should have leading and trailing spaces trimmed.");
        });

    }

    // ------*------ Testing printError(String) ------*------

    /**
     * Tests {@link Display#printError(String)} method to validate that custom error
     * messages are correctly formatted and printed to the console.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printError(String) is invoked with an arbitrary exception message
     * @expected the intercepted console output injects and formats the passed message
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintErrorFormatsMessage() throws Exception {

        // Arrange
        Display display = new Display();
        String message = "Word must be exactly 5 letters long.";
        String tag = Ansi.getBackgroundSequence(DisplayTest.RED) + "[Error]" + Ansi.RESET;
        String expectedOutput = " !  " + tag + " : " + message + " Try another guess!\n";
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {display.printError(message);});

        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The error message printed to the console should match the expected message.");

    }

    /**
     * Tests {@link Display#printError(String)} method to validate that it safely outputs
     * the literal "null" to the console.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printError(String) is invoked with a null message
     * @expected the intercepted console output injects and formats "null"
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintErrorHandlesNullMessage() throws Exception {

        // Arrange
        Display display = new Display();
        String nullMessage = null;
        String tag = Ansi.getBackgroundSequence(DisplayTest.RED) + "[Error]" + Ansi.RESET;
        String expectedOutput = " !  " + tag + " : null Try another guess!\n";
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {display.printError(nullMessage);});

        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "A null error message should be safely printed as the literal \"null\".");
        
    }

    // ------*------ Testing printCorrectLetter(char) ------*------

    /**
     * Tests {@link Display#printCorrectLetter(char)} method to validate that correct
     * letters are printed to the console with a green background.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printCorrectLetter(char) is invoked with an arbitrary letter
     * @expected the intercepted console output injects and formats the passed letter
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintCorrectLetterFormatsCharacter() throws Exception {

        // Arrange
        Display display = new Display();
        char correctLetter = 'A';
        String expectedOutput = Ansi.getBackgroundSequence(DisplayTest.GREEN) + "[" + correctLetter + "]" + Ansi.RESET;
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {display.printCorrectLetter(correctLetter);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "A correct letter should be printed with a green background.");
        
    }

    // ------*------ Testing printMisplacedLetter(char) ------*------

    /**
     * Tests {@link Display#printMisplacedLetter(char)} method to validate that misplaced
     * letters are printed to the console with a yellow background.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printMisplacedLetter(char) is invoked with an arbitrary letter
     * @expected the intercepted console output injects and formats the passed letter
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintMisplacedLetterFormatsCharacter() throws Exception {

        // Arrange
        Display display = new Display();
        char misplacedLetter = 'L';
        String expectedOutput = Ansi.getBackgroundSequence(DisplayTest.YELLOW) + "[" + misplacedLetter + "]" + Ansi.RESET;
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {display.printMisplacedLetter(misplacedLetter);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "A misplaced letter should be printed with a yellow background.");
        
    }

    // ------*------ Testing printIncorrectLetter(char) ------*------

    /**
     * Tests {@link Display#printIncorrectLetter(char)} method to validate that incorrect
     * letters are printed to the console with a gray background.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printIncorrectLetter(char) is invoked with an arbitrary letter
     * @expected the intercepted console output injects and formats the passed letter
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintIncorrectLetterFormatsCharacter() throws Exception {

        // Arrange
        Display display = new Display();
        char incorrectLetter = 'P';
        String expectedOutput = Ansi.getBackgroundSequence(DisplayTest.GRAY) + "[" + incorrectLetter + "]" + Ansi.RESET;
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {display.printIncorrectLetter(incorrectLetter);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "An incorrect letter should be printed with a gray background.");
        
    }

    // ------*------ Testing printRemainingAttempts(int) ------*------

    /**
     * Tests {@link Display#printRemainingAttempts(int)} method to validate that
     * remaining attempt counts are correctly formatted and printed to the console.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printRemainingAttempts(int) is invoked with an arbitrary integer
     * @expected the intercepted console output injects and formats the passed integer
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintRemainingAttemptsFormatsInteger() throws Exception {
        
        // Arrange
        Display display = new Display();
        int attempts = 3;
        String expectedOutput = " *  You have (" + attempts + ") attempts remaining.\n";
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {display.printRemainingAttempts(attempts);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The message printed to the console should have the integer formatted.");
        
    }

    // ------*------ Testing printWinMessage(int) ------*------

    /**
     * Tests {@link Display#printWinMessage(int)} method to validate that attempt counts
     * used to win are correctly formatted and printed to the console.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printWinMessage(int) is invoked with an arbitrary integer
     * @expected the intercepted console output injects and formats the passed integer
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintWinMessageFormatsInteger() throws Exception {
        
        // Arrange
        Display display = new Display();
        int attempts = 3;
        String expectedOutput = " *  Splendid! You guessed correctly in (" + attempts + ") attempts.\n";
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {display.printWinMessage(attempts);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The message printed to the console should have the integer formatted.");
        
    }

    // ------*------ Testing printLossMessage(String) ------*------

    /**
     * Tests {@link Display#printLossMessage(String)} method to validate that hidden
     * target words are correctly formatted and printed to the console.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printLossMessage(String) is invoked with an arbitrary word
     * @expected the intercepted console output injects and formats the passed word
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintLossMessageFormatsTargetWord() throws Exception {

        // Arrange
        Display display = new Display();
        String word = "album";
        String expectedOutput = " *  The correct word was \"" + word + "\". Better luck next time!\n";
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {display.printLossMessage(word);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The message printed to the console should have the target word formatted.");

    }

}