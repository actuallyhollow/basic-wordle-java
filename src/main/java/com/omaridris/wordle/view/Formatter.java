package com.omaridris.wordle.view;

import com.omaridris.wordle.utilities.Rgb;

/**
 * Formats the console's visual output into structural tokens, headers, and messages.
 * <p>
 * This utility class handles the assembly of predefined, primitive visual components
 * using the {@link Renderer} API for console output; thus, it is strictly decoupled
 * from internal logic and user input. By default, all methods implicitly append a
 * newline character '\n' to the console.
 * <p>
 * It cannot be extended or instantiated and can only be statically accessed.
 * 
 * @author Omar Idris
 */
public final class Formatter {

    // ------*------ Attributes ------*------

    private static final Rgb GREEN = new Rgb(65, 115, 60);
    private static final Rgb YELLOW = new Rgb(155, 135, 50);
    private static final Rgb GRAY = new Rgb(50, 50, 50);
    private static final Rgb RED = new Rgb(160, 50, 50);
    private static final int SEPARATOR_LENGTH = 70;
    private static final int SEPARATOR_DELAY = 10;

    /**
     * Prevents instantiation of new Formatter objects.
     * 
     * @throws UnsupportedOperationException if instantiated internally or by Reflection.
     */
    private Formatter() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Formatter objects should not be instantiated.");
    }

    // ------*------ Atomic Tokens ------*------

    /**
     * Prints a standard newline character '\n' token to the console.
     */
    public static void printNewlineToken() {
        Renderer.print("\n");
    }

    /**
     * Prints a separator token to the console using the default length of 70 dashes.
     */
    public static void printSeparatorToken() {
        Renderer.print("-".repeat(Formatter.SEPARATOR_LENGTH) + "\n", Formatter.SEPARATOR_DELAY);
    }

    /**
     * Prints a separator token to the console using the default length of 70 dashes,
     * enclosed by leading and trailing newlines.
     */
    public static void printSpacedSeparatorToken() {
        Formatter.printNewlineToken();
        Formatter.printSeparatorToken();
        Formatter.printNewlineToken();
    }

    /**
     * Prints a standard bullet point token to the console, without appending a newline.
     */
    public static void printBulletToken() {
        Renderer.print(" *  ");
    }

    /**
     * Prints a green character token to the console indicating a correct match,
     * without appending a newline.
     * 
     * @param character the English letter evaluated as correct
     */
    public static void printCorrectLetterToken(char character) {
        Renderer.printColored("[" + character + "]", Formatter.GREEN, null);
    }

    /**
     * Prints a yellow character token to the console indicating a misplaced match,
     * without appending a newline.
     * 
     * @param character the English letter evaluated as misplaced
     */
    public static void printMisplacedLetterToken(char character) {
        Renderer.printColored("[" + character + "]", Formatter.YELLOW, null);
    }

    /**
     * Prints a gray character token to the console indicating an incorrect match,
     * without appending a newline.
     * 
     * @param character the English letter evaluated as incorrect
     */
    public static void printIncorrectLetterToken(char character) {
        Renderer.printColored("[" + character + "]", Formatter.GRAY, null);
    }

    /**
     * Prints a red error tag token to the console, without appending a newline.
     */
    public static void printErrorToken() {
        Renderer.print(" !  ");
        Renderer.printColored("[Error]", Formatter.RED, null);
    }

    /**
     * Prints a red crash tag token to the console, without appending a newline.
     */
    public static void printCrashToken() {
        Renderer.print(" !  ");
        Renderer.printColored("[Crash]", Formatter.RED, null);
    }

    // ------*------ Structural Headers ------*------

    /**
     * Prints a greeting header to the console, used to introduce the application
     * at startup.
     */
    public static void printGreetingHeader() {
        Renderer.print("-*- Welcome to Wordle! -*-\n");
    }

    /**
     * Prints a stylized rules header to the console, used to title the instructional
     * sequence for the application.
     */
    public static void printRulesHeader() {
        Renderer.print("-*- Rules -*-\n");
    }

    /**
     * Prints a color feedback header to the console, used to title the visual key
     * for letter matching.
     */
    public static void printColorKeyHeader() {
        Renderer.print("-*- Color Feedback -*-\n");
    }

    /**
     * Prints a guess header to the console, signaling the start of a guessing round.
     */
    public static void printGuessHeader() {
        Renderer.print("-*- Time for Guessing! -*-\n");
    }

    /**
     * Prints a closing header to the console, signaling the termination
     * of the application.
     */
    public static void printClosingHeader() {
        Renderer.print("-*- Thank You for Playing Wordle! -*-\n");
    }

    // ------*------ Feedback Messages ------*------

    /**
     * Prints an interactive prompt to the console requesting user input for their next
     * guess, without appending a newline to allow inline typing.
     */
    public static void printGuessMessage() {
        Renderer.print(" ?  Enter your guess: ");
    }

    /**
     * Prints a message detailing the number of remaining attempts to the console.
     * 
     * @param attempts the number of remaining attempts
     */
    public static void printAttemptsMessage(int attempts) {
        Renderer.print(" *  You have (" + attempts + ") attempts remaining.\n");
    }

    /**
     * Prints a victory message detailing the total attempts used to the console.
     * 
     * @param attempts the number of attempts used to guess correctly
     */
    public static void printWinMessage(int attempts) {
        Renderer.print(" *  Splendid! You guessed correctly in (" + attempts + ") attempts.\n");
    }

    /**
     * Prints a defeat message revealing the hidden target word to the console.
     * 
     * @param target the correct target word that was not guessed
     */
    public static void printLossMessage(String target) {
        Renderer.print(" *  The correct word was \"" + target + "\". Better luck next time!\n");
    }

    /**
     * Prints an error message to the console alongside a prompt to guess again.
     * 
     * @param message the custom description of the error
     */
    public static void printErrorMessage(String message) {
        Renderer.print(" : " + message + " Try another guess!\n");
    }

    /**
     * Prints a crash message to the console alongside a shutdown notice.
     * 
     * @param message the system description of the crash
     */
    public static void printCrashMessage(String message) {
        Renderer.print(" : " + message + " Shutting down application...\n");
    }

    // ------*------ Compound Sequences ------*------

    /**
     * Prints a multi-line instructional sequence to the console, detailing the core
     * rules and win conditions of Wordle.
     */
    public static void printRulesSequence() {
        Renderer.print(" *  Guess the 5-letter word in 6 attempts.\n");
        Renderer.print(" *  Each guess must be a valid 5-letter word.\n");
        Renderer.print(" *  After each guess, colored feedback will indicate your accuracy.\n");
    }
    
    /**
     * Prints a multi-line visual key to the console, mapping the letter colors to their
     * corresponding match states.
     */
    public static void printColorKeySequence() {
        
        Renderer.print(" *  ");
        Renderer.printColored("[Green] ", Formatter.GREEN, null);
        Renderer.print(" : Letter is in the word, and in correct spot.\n");

        Renderer.print(" *  ");
        Renderer.printColored("[Yellow]", Formatter.YELLOW, null);
        Renderer.print(" : Letter is in the word, but in wrong spot.\n");

        Renderer.print(" *  ");
        Renderer.printColored("[Gray]  ", Formatter.GRAY, null);
        Renderer.print(" : Letter is not in the word in any spot.\n");
        
    }
    
}