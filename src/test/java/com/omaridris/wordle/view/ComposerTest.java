package com.omaridris.wordle.view;

import java.io.InputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.github.stefanbirkner.systemlambda.SystemLambda;
import com.omaridris.wordle.model.Word;
import com.omaridris.wordle.model.Matcher.State;

/**
 * Conducts tests on the methods of {@link Composer} using JUnit API,
 * and System Lambda API to intercept console output.
 * <p>
 * Trivial methods (e.g. hardcoded output and direct print statements) are not tested;
 * thus, only parameterized methods with formatting logic or user input will be tested.
 * 
 * @author Omar Idris
 */
public class ComposerTest {

    // ------*------ Testing Composer(InputStream) ------*------

    /**
     * Tests {@link Composer#Composer(InputStream)} constructor to validate the proper
     * handling of null input streams.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario Composer(InputStream) is invoked with a null stream
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testInstantiationWithNullInputStream() {

        // Arrange
        InputStream nullStream = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Composer(nullStream);}, "A null input stream should result in IllegalArgumentException.");

    }

    // ------*------ Testing printCrashScreen(String) ------*------

    /**
     * Tests {@link Composer#printCrashScreen(String)} method to validate that system
     * crash messages are correctly formatted into the screen.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printCrashScreen(String) is invoked with an arbitrary String
     * @expected the intercepted console output injects and formats the String
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintCrashScreenWithString() throws Exception {

        // Arrange
        Composer composer = new Composer(System.in);
        String message = "assets/answers-alphabetical.txt (No such file or directory)";
        boolean expectedResult = true;

        // Act
        String output = SystemLambda.tapSystemOut(() -> {composer.printCrashScreen(message);});
        boolean actualResult = output.contains(message);

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The crash description message should be correctly formatted.");

    }

    /**
     * Tests {@link Composer#printCrashScreen(String)} method to validate that null crash
     * messages are safely replaced with a default fallback string.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printCrashScreen(String) is invoked with a null String
     * @expected the intercepted console output injects the default "Unknown crash."
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintCrashScreenWithNull() throws Exception {

        // Arrange
        Composer composer = new Composer(System.in);
        String nullMessage = null;
        boolean expectedResult = true;

        // Act
        String output = SystemLambda.tapSystemOut(() -> {composer.printCrashScreen(nullMessage);});
        boolean actualResult = output.contains("Unknown crash.");

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "A null crash description message should be replaced with the default fallback.");

    }

    // ------*------ Testing printFeedbackNotice(Word, State[]) ------*------

    /**
     * Tests {@link Composer#printFeedbackNotice(Word, State[])} method to validate that
     * guesses are correctly formatted into the notice with backgrounds corresponding to
     * their States.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printFeedbackNotice(Word, State[]) is invoked with a valid guess and
     *           states array
     * @expected the intercepted console output injects and formats the guess Word's
     *           letters based on their States
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintFeedbackNoticeWithValidParameters() throws Exception {

        // Arrange
        Composer composer = new Composer(System.in);
        Word guess = new Word("apple");
        State[] states = {State.INCORRECT, State.MISPLACED, State.CORRECT, State.INCORRECT, State.INCORRECT};
        boolean expectedResult = true;

        // Act
        String output = SystemLambda.tapSystemOut(() -> {composer.printFeedbackNotice(guess, states);});
        boolean actualResult = output.contains("A") && output.contains("P") && output.contains("L") && output.contains("E");

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "A valid guess should be correctly formatted corresponding to its states.");

    }

    /**
     * Tests {@link Composer#printFeedbackNotice(Word, State[])} method to validate the
     * proper handling of null guess Words.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printFeedbackNotice(Word, State[]) is invoked with a null guess Word
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testPrintFeedbackNoticeWithNullGuessWord() {

        // Arrange
        Composer composer = new Composer(System.in);
        Word nullGuess = null;
        State[] states = {State.INCORRECT, State.MISPLACED, State.CORRECT, State.INCORRECT, State.INCORRECT};

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {composer.printFeedbackNotice(nullGuess, states);}, "A null guess should result in IllegalArgumentException.");

    }

    /**
     * Tests {@link Composer#printFeedbackNotice(Word, State[])} method to validate the
     * proper handling of null State arrays.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printFeedbackNotice(Word, State[]) is invoked with a null State array
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testPrintFeedbackNoticeWithNullStatesArray() {

        // Arrange
        Composer composer = new Composer(System.in);
        Word guess = new Word("apple");
        State[] nullStates = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {composer.printFeedbackNotice(guess, nullStates);}, "A null states array should result in IllegalArgumentException.");

    }

    /**
     * Tests {@link Composer#printFeedbackNotice(Word, State[])} method to validate the
     * proper handling of invalid State elements.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printFeedbackNotice(Word, State[]) is invoked with a State array
     *           containing an invalid State element
     * @expected IllegalStateException is thrown
     */
    @Test
    public void testPrintFeedbackNoticeWithInvalidStateElements() {

        // Arrange
        Composer composer = new Composer(System.in);
        Word guess = new Word("apple");
        State[] invalidElementStates = {State.INCORRECT, State.UNCHECKED, State.CORRECT, State.INCORRECT, State.INCORRECT};

        // Act & Assert
        Assertions.assertThrows(IllegalStateException.class, () -> {composer.printFeedbackNotice(guess, invalidElementStates);}, "An invalid state element should result in IllegalStateException.");

    }

    /**
     * Tests {@link Composer#printFeedbackNotice(Word, State[])} method to validate the
     * proper handling of null State elements.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printFeedbackNotice(Word, State[]) is invoked with a State array
     *           containing a null State element
     * @expected IllegalStateException is thrown
     */
    @Test
    public void testPrintFeedbackNoticeWithNullStateElements() {

        // Arrange
        Composer composer = new Composer(System.in);
        Word guess = new Word("apple");
        State[] nullElementStates = {State.INCORRECT, null, State.CORRECT, State.INCORRECT, State.INCORRECT};

        // Act & Assert
        Assertions.assertThrows(IllegalStateException.class, () -> {composer.printFeedbackNotice(guess, nullElementStates);}, "A null state element should result in IllegalStateException.");

    }


    // ------*------ Testing printAttemptsNotice(int) ------*------

    /**
     * Tests {@link Composer#printAttemptsNotice(int)} method to validate that remaining
     * attempt counts are correctly formatted into the notice.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printAttemptsNotice(int) is invoked with an arbitrary integer
     * @expected the intercepted console output injects and formats the integer
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintAttemptsNoticeWithInteger() throws Exception {

        // Arrange
        Composer composer = new Composer(System.in);
        int attempts = 3;
        boolean expectedResult = true;

        // Act
        String output = SystemLambda.tapSystemOut(() -> {composer.printAttemptsNotice(attempts);});
        boolean actualResult = output.contains(Integer.toString(attempts));

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The remaining attempts count should be correctly formatted.");

    }

    // ------*------ Testing printErrorNotice(String) ------*------

    /**
     * Tests {@link Composer#printErrorNotice(String)} method to validate that custom
     * error messages are correctly formatted into the screen.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printErrorNotice(String) is invoked with an arbitrary String
     * @expected the intercepted console output injects and formats the String
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintErrorNoticeWithString() throws Exception {

        // Arrange
        Composer composer = new Composer(System.in);
        String message = "Word must be exactly 5 letters long.";
        boolean expectedResult = true;

        // Act
        String output = SystemLambda.tapSystemOut(() -> {composer.printErrorNotice(message);});
        boolean actualResult = output.contains(message);

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The error description message should be correctly formatted.");

    }

    /**
     * Tests {@link Composer#printErrorNotice(String)} method to validate that null error
     * messages are safely replaced with a default fallback string.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printErrorNotice(String) is invoked with a null String
     * @expected the intercepted console output injects the default "Unknown error."
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintErrorNoticeWithNull() throws Exception {

        // Arrange
        Composer composer = new Composer(System.in);
        String nullMessage = null;
        boolean expectedResult = true;

        // Act
        String output = SystemLambda.tapSystemOut(() -> {composer.printErrorNotice(nullMessage);});
        boolean actualResult = output.contains("Unknown error.");

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "A null error description message should be replaced with the default fallback.");

    }

    // ------*------ Testing printWinSummary(int) ------*------

    /**
     * Tests {@link Composer#printWinSummary(int)} method to validate that victory
     * attempt counts are correctly formatted into the summary.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printWinSummary(int) is invoked with an arbitrary integer
     * @expected the intercepted console output injects and formats the integer
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintWinSummaryWithInteger() throws Exception {

        // Arrange
        Composer composer = new Composer(System.in);
        int attempts = 3;
        boolean expectedResult = true;

        // Act
        String output = SystemLambda.tapSystemOut(() -> {composer.printWinSummary(attempts);});
        boolean actualResult = output.contains(Integer.toString(attempts));

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The used attempts count should be correctly formatted.");

    }

    // ------*------ Testing printLossSummary(Word) ------*------

    /**
     * Tests {@link Composer#printLossSummary(Word)} method to validate that target Words
     * are correctly formatted into the summary
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printLossSummary(Word) is invoked with a valid target Word
     * @expected the intercepted console output injects and formats the Word
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testPrintLossSummaryWithWord() throws Exception {

        // Arrange
        Composer composer = new Composer(System.in);
        Word target = new Word("apple");
        boolean expectedResult = true;

        // Act
        String output = SystemLambda.tapSystemOut(() -> {composer.printLossSummary(target);});
        boolean actualResult = output.contains(target.getText());

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The hidden target Word should be correctly formatted into the loss summary.");

    }

    /**
     * Tests {@link Composer#printLossSummary(Word)} method to validate the proper
     * handling of null target Words.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printLossSummary(Word) is invoked with a null target Word
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testPrintLossSummaryWithNull() {

        // Arrange
        Composer composer = new Composer(System.in);
        Word nullTarget = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {composer.printLossSummary(nullTarget);}, "A null target Word should result in IllegalArgumentException.");

    }

    // ------*------ Testing promptGuess() ------*------

    /**
     * Tests {@link Composer#promptGuess()} method to validate that standard console
     * input is successfully read by the underlying Scanner.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario promptGuess() is invoked while an arbitrary String is injected into the
     *           standard input stream
     * @expected the returned String is strictly equal to the injected console input
     * @throws Exception if the System Lambda injection fails
     */
    @Test
    public void testPromptGuessReadsInput() throws Exception {

        // Arrange
        String input = "apple";
        String expectedOutput = input;

        // Act & Assert
        SystemLambda.withTextFromSystemIn(input).execute(() -> {
            Composer composer = new Composer(System.in);
            String actualOutput = composer.promptGuess();
            composer.closeInput();
            Assertions.assertEquals(expectedOutput, actualOutput, "The Scanner should successfully read the injected input.");
        });

    }

    /**
     * Tests {@link Composer#promptGuess()} method to validate that console input is
     * sanitized and trimmed before returning it to the invoker.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario promptGuess() is invoked while an arbitrary String containing
     *           whitespaces is injected into the standard input stream
     * @expected the returned String is stripped of leading and trailing whitespaces
     * @throws Exception if the System Lambda injection fails
     */
    @Test
    public void testPromptGuessTrimsWhitespace() throws Exception {

        // Arrange
        String whitespaceInput = " apple ";
        String expectedOutput = whitespaceInput.trim();

        // Act & Assert
        SystemLambda.withTextFromSystemIn(whitespaceInput).execute(() -> {
            Composer composer = new Composer(System.in);
            String actualOutput = composer.promptGuess();
            composer.closeInput();
            Assertions.assertEquals(expectedOutput, actualOutput, "The returned input should have leading and trailing spaces trimmed.");
        });

    }

}