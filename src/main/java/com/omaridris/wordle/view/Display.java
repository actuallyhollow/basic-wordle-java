package com.omaridris.wordle.view;

import java.util.Scanner;
import com.omaridris.wordle.utilities.Rgb;

/**
 * Represents a Display unit that manages the console's visual output and standard input.
 * <p>
 * This class acts as the View layer in the MVC architecture; thus, it strictly manages
 * both I/O streams and is completely decoupled from any internal logic. It utilizes the
 * API provided by {@link Renderer} to display and render text, including support for
 * colored console output.
 * <p>
 * Unless otherwise specified, all methods in this class implicitly append a newline
 * character '\n' to the console.
 * 
 * @author Omar Idris
 */
public class Display {

    // ------*------ Attributes ------*------

    private static final Rgb GREEN = new Rgb(65, 115, 60);
    private static final Rgb YELLOW = new Rgb(155, 135, 50);
    private static final Rgb GRAY = new Rgb(50, 50, 50);
    private static final Rgb RED = new Rgb(160, 50, 50);
    private static final int SEPARATOR_LENGTH = 70;
    private static final int SEPARATOR_DELAY = 10;
    private Scanner scanner;

    /**
     * Instantiates a new Display object.
     * <p>
     * Initializes the underlying Scanner to read input directly from the standard
     * console stream.
     */
    public Display() {
        this.scanner = new Scanner(System.in);
    }

    // ------*------ Generic Formatting ------*------

    /**
     * Prints a standard newline character '\n' to the console.
     */
    public void printNewLine() {
        Renderer.print("\n");
    }

    /**
     * Prints a dashed separator to the console using the default length of 70 dashes.
     */
    public void printSeparator() {
        Renderer.print("-".repeat(Display.SEPARATOR_LENGTH) + "\n", Display.SEPARATOR_DELAY);
    }

    /**
     * Prints a bullet point to the console without appending a newline.
     */
    public void printBulletPoint() {
        Renderer.print(" *  ");
    }

    // ------*------ Static Game Messages ------*------

    /**
     * Prints a welcome message to the console.
     */
    public void printWelcomeMessage() {
        Renderer.print("-*- Welcome to Wordle! -*-\n");
    }

    /**
     * Prints the rules of Wordle to the console.
     */
    public void printRules() {
        Renderer.print("-*- Rules -*-\n");
        Renderer.print(" *  Guess the 5-letter word in 6 attempts.\n");
        Renderer.print(" *  Each guess must be a valid 5-letter word.\n");
        Renderer.print(" *  After each guess, colored feedback will indicate your accuracy.\n");
    }
    
    /**
     * Prints the color feedback meanings to the console.
     */
    public void printColorFeedbackKey() {

        Renderer.print("-*- Color Feedback -*-\n");
        
        this.printBulletPoint();
        Renderer.printColored("[Green] ", Display.GREEN, null);
        Renderer.print(" : Letter is in the word, and in correct spot.\n");

        this.printBulletPoint();
        Renderer.printColored("[Yellow]", Display.YELLOW, null);
        Renderer.print(" : Letter is in the word, but in wrong spot.\n");

        this.printBulletPoint();
        Renderer.printColored("[Gray]  ", Display.GRAY, null);
        Renderer.print(" : Letter is not in the word in any spot.\n");
        
    }

    /**
     * Prints a closing message to the console.
     */
    public void printClosingMessage() {
        Renderer.print("-*- Thank You for Playing Wordle! -*-\n");
    }

    // ------*------ User Interaction ------*------

    /**
     * Prints a guessing prompt to the console and accepts the user's input.
     * 
     * @return the text String provided by the user
     */
    public String printGuessPrompt() {
        Renderer.print("-*- Time for Guessing! -*-\n");
        Renderer.print(" ?  Enter your guess: ");
        return this.scanner.nextLine().trim();
    }

    /**
     * Prints a custom error message to the console with a deep, red background.
     * 
     * @param message the message describing the error
     */
    public void printError(String message) {
        Renderer.print(" !  ");
        Renderer.printColored("[Error]", Display.RED, null);
        Renderer.print(" : " + message + " Try another guess!\n");
    }

    // ------*------ Letter Rendering ------*------

    /**
     * Prints a character to the console with a vivid, green background without
     * appending a newline.
     * 
     * @param letter the character deemed as correct
     */
    public void printCorrectLetter(char letter) {
        Renderer.printColored("[" + letter + "]", Display.GREEN, null);
    }

    /**
     * Prints a character to the console with a warm, yellow background without
     * appending a newline.
     * 
     * @param letter the character deemed as misplaced
     */
    public void printMisplacedLetter(char letter) {
        Renderer.printColored("[" + letter + "]", Display.YELLOW, null);
    }

    /**
     * Prints a character to the console with a pale, gray background without
     * appending a newline.
     * 
     * @param letter the character deemed as incorrect
     */
    public void printIncorrectLetter(char letter) {
        Renderer.printColored("[" + letter + "]", Display.GRAY, null);
    }

    // ------*------ Round Results ------*------

    /**
     * Prints a message to the console detailing the remaining attempts.
     * 
     * @param attempts the number of remaining attempts
     */
    public void printRemainingAttempts(int attempts) {
        Renderer.print(" *  You have (" + attempts + ") attempts remaining.\n");
    }

    /**
     * Prints a winning message to the console detailing the attempts used to win.
     * 
     * @param attempts the number of attempts used to guess correctly
     */
    public void printWinMessage(int attempts) {
        Renderer.print(" *  Splendid! You guessed correctly in (" + attempts + ") attempts.\n");
    }

    /**
     * Prints a loss message to the console detailing the correct hidden word.
     * 
     * @param word the hidden target word
     */
    public void printLossMessage(String word) {
        Renderer.print(" *  The correct word was \"" + word + "\". Better luck next time!\n");
    }

    // ------*------ Resource Management ------*------

    /**
     * Closes the underlying Scanner's input stream to prevent resource leaks.
     */
    public void closeIO() {
        this.scanner.close();
    }

}