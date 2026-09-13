package com.omaridris.wordle.view;

import java.io.InputStream;
import java.util.Scanner;
import com.omaridris.wordle.model.Word;
import com.omaridris.wordle.model.Matcher.State;

/**
 * Composes the console's output structures and manages standard user interactions.
 * <p>
 * This class acts as the main View layer in the MVC architecture; thus, it strictly
 * manages both input and output streams. It uses the API provided by {@link Formatter}
 * to assemble low-level text tokens, headers, messages, and sequences into cohesive
 * domain-specific screens, notices, and summaries.
 * <p>
 * It cannot be extended and can only be directly instantiated.
 * 
 * @author Omar Idris
 * @see Formatter
 */
public final class Composer {

    // ------*------ Attributes ------*------

    private final Scanner scanner;

    /**
     * Instantiates a new Composer object.
     * <p>
     * Initializes the underlying Scanner to read input directly from the passed input
     * stream.
     * 
     * @param inputStream the custom input stream to read from
     * @throws IllegalArgumentException if the input stream is null
     */
    public Composer(InputStream inputStream) throws IllegalArgumentException {

        if(inputStream == null) {
            throw new IllegalArgumentException("Input stream cannot be null.");
        }

        this.scanner = new Scanner(inputStream);
    }

    // ------*------ System Screens ------*------

    /**
     * Prints the initial instruction screen to the console that introduces the core
     * mechanics and visual rules at startup.
     * <p>
     * This screen is composed of a greeting header, the rules sequence, and the visual
     * color feedback key, enclosed by leading and trailing separators.
     */
    public void printInstructionScreen() {
        Formatter.printSpacedSeparatorToken();
        
        Formatter.printGreetingHeader();
        Formatter.printNewlineToken();

        Formatter.printRulesHeader();
        Formatter.printRulesSequence();
        Formatter.printNewlineToken();

        Formatter.printColorKeyHeader();
        Formatter.printColorKeySequence();
        Formatter.printNewlineToken();

        Formatter.printSeparatorToken();
    }

    /**
     * Prints the final closing screen to the console that indicates the current
     * session's termination.
     * <p>
     * This screen is composed of a termination header, enclosed by leading and
     * trailing separators.
     */
    public void printClosingScreen() {
        Formatter.printSpacedSeparatorToken();
        Formatter.printClosingHeader();
        Formatter.printSpacedSeparatorToken();
    }

    /**
     * Prints a crash screen to the console that describes unrecoverable system errors.
     * <p>
     * This screen is composed of a red crash token and the system description of the
     * crash, enclosed by leading and trailing separators.
     * 
     * @param message the system crash description, or null to print "Unknown crash."
     */
    public void printCrashScreen(String message) {
        message = (message == null) ? "Unknown crash." : message;
        Formatter.printSpacedSeparatorToken();
        Formatter.printCrashToken();
        Formatter.printCrashMessage(message);
        Formatter.printSpacedSeparatorToken();
    }

    // ------*------ Progression Notices ------*------

    /**
     * Prints a guess notice to the console that indicates the start of a new round.
     * <p>
     * This notice is composed of a guessing round header, preceded by a leading newline.
     */
    public void printGuessNotice() {
        Formatter.printNewlineToken();
        Formatter.printGuessHeader();
    }

    /**
     * Prints a feedback notice to the console that indicates a guess's accuracy.
     * <p>
     * This notice is composed of a letters notice rendering each letter with an
     * appropriate green, yellow, or gray background, preceded by a leading bullet point.
     * 
     * @param guess the validated guess Word
     * @param states the State array storing match feedback
     * @throws IllegalArgumentException if either guess or states is null
     * @throws IllegalStateException if a State is null or invalid
     */
    public void printFeedbackNotice(Word guess, State[] states) throws IllegalArgumentException, IllegalStateException {

        if(guess == null) {
            throw new IllegalArgumentException("Guess word cannot be null.");
        }

        if(states == null) {
            throw new IllegalArgumentException("States cannot be null.");
        }

        char[] letters = guess.getText().toCharArray();
        Formatter.printBulletToken();
        this.printLettersNotice(letters, states);
        Formatter.printNewlineToken();

    }

    /**
     * Prints a notice to the console that tracks the available attempts.
     * <p>
     * This notice is composed of a formatted statement displaying the exact number of
     * attempts remaining.
     * 
     * @param attempts the number of remaining attempts
     */
    public void printAttemptsNotice(int attempts) {
        Formatter.printAttemptsMessage(attempts);
    }

    /**
     * Prints an error notice to the console that describes recoverable user errors.
     * <p>
     * This notice is composed of a red error token and a custom description of the
     * error, prompting the user to try another guess.
     * 
     * @param message the custom error description, or null to print "Unknown error."
     */
    public void printErrorNotice(String message) {
        message = (message == null) ? "Unknown error." : message;
        Formatter.printErrorToken();
        Formatter.printErrorMessage(message);
    }

    // ------*------ Termination Summaries ------*------

    /**
     * Prints a victory summary to the console that concludes a successful guess.
     * <p>
     * This summary is composed of a victory message detailing the total attempts used,
     * preceded by a leading newline.
     * 
     * @param attempts the number of attempts used to guess correctly
     */
    public void printWinSummary(int attempts) {
        Formatter.printNewlineToken();
        Formatter.printWinMessage(attempts);
    }

    /**
     * Prints a defeat summary to the console that concludes an unsuccessful guess.
     * <p>
     * This summary is composed of a defeat message revealing the hidden target Word,
     * preceded by a leading newline.
     * 
     * @param target the correct target Word that was not guessed
     * @throws IllegalArgumentException if target Word is null
     */
    public void printLossSummary(Word target) throws IllegalArgumentException {

        if(target == null) {
            throw new IllegalArgumentException("Target word cannot be null.");
        }

        Formatter.printNewlineToken();
        Formatter.printLossMessage(target.getText());

    }

    // ------*------ Input Management ------*------

    /**
     * Prompts the user for a guess, awaits interaction, and reads the console's input.
     * <p>
     * This prompt is composed of a message requesting user input.
     * 
     * @return the trimmed input provided by the user
     */
    public String promptGuess() {
        Formatter.printGuessMessage();
        return this.scanner.nextLine().trim();
    }

    /**
     * Closes the underlying Scanner's input stream to prevent resource leaks.
     */
    public void closeInput() {
        this.scanner.close();
    }

    // ------*------ Helper Methods ------*------

    /**
     * Prints the letters notice to the console that corresponds to their match States.
     * <p>
     * This notice is composed of colored letter tokens with green, yellow, or gray
     * backgrounds corresponding to each letter's match State.
     * 
     * @param letters the character array of the guessed Word
     * @param states the State array storing match feedback
     * @throws IllegalStateException if a State is null or invalid
     */
    private void printLettersNotice(char[] letters, State[] states) throws IllegalStateException {

        for(int i = 0; i < states.length; i++) {
            if(states[i] == State.CORRECT) {
                Formatter.printCorrectLetterToken(letters[i]);
            } else if(states[i] == State.MISPLACED) {
                Formatter.printMisplacedLetterToken(letters[i]);
            } else if(states[i] == State.INCORRECT) {
                Formatter.printIncorrectLetterToken(letters[i]);
            } else {
                throw new IllegalStateException("Invalid state: Letter \'" + letters[i] + "\' has \"" + states[i] + "\" state.");
            }
        }

    }
    
}