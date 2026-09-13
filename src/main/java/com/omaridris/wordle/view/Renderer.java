package com.omaridris.wordle.view;

import com.omaridris.wordle.utilities.Ansi;
import com.omaridris.wordle.utilities.Rgb;

/**
 * Renders regular and colored text to the console.
 * <p>
 * This utility class handles the animation of text using a "typewriter" effect, or
 * character-by-character rendering, and the proper formatting of colored text using ANSI
 * escape sequences.
 * <p>
 * It cannot be extended or instantiated and can only be statically accessed.
 * 
 * @author Omar Idris
 * @see Ansi
 */
public final class Renderer {

    // ------*------ Attributes ------*------

    /**
     * The default millisecond delay between printed characters to simulate typing speed.
     */
    public static final int DEFAULT_DELAY = 35;

    /**
     * Prevents instantiation of new Renderer objects.
     * 
     * @throws UnsupportedOperationException if instantiated internally or by Reflection.
     */
    private Renderer() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Renderer objects should not be instantiated.");
    }

    // ------*------ Text Rendering ------*------

    /**
     * Prints text to the console character-by-character using the default delay of 35
     * milliseconds as typing speed.
     * <p>
     * The console stream is flushed after every character to ensure real-time rendering.
     * 
     * @param text the String to print
     * @see Renderer#print(String, int)
     */
    public static void print(String text) {
        Renderer.print(text, Renderer.DEFAULT_DELAY);
    }

    /**
     * Prints text to the console character-by-character using the passed milliseconds
     * delay as typing speed.
     * <p>
     * ANSI escape sequences are detected and printed instantly to prevent artificial
     * delays, and the console stream is flushed after every character to ensure
     * real-time rendering.
     * 
     * @param text the String to print
     * @param milliseconds the pause duration between each character
     */
    public static void print(String text, int milliseconds) {

        if(text == null) {
            text = "null";
        }

        final char ANSI_START = '\u001B';
        final char ANSI_END = 'm';
        char[] characters = text.toCharArray();
        boolean insideAnsi = false;

        for(char character : characters) {
            
            System.out.print(character);
            System.out.flush();

            if(character == ANSI_START) {
                insideAnsi = true;
            }

            if(insideAnsi == false) {
                Renderer.delay(milliseconds);
            }

            if(insideAnsi && character == ANSI_END) {
                insideAnsi = false;
            }
            
        }

    }

    // ------*------ Color Rendering ------*------

    /**
     * Prints text to the console character-by-character with specified foreground RGB
     * color using the default delay of 35 milliseconds as typing speed.
     * <p>
     * Passing null to the Rgb parameter uses the console's default color.
     * 
     * @param text the String to print
     * @param foreground the RGB color for the foreground (or null to skip)
     * @see Renderer#printColored(String, int, Rgb, Rgb)
     */
    public static void printColored(String text, Rgb foreground) {
        Renderer.printColored(text, Renderer.DEFAULT_DELAY, null, foreground);
    }

    /**
     * Prints text to the console character-by-character with specified background and
     * foreground RGB colors using the default delay of 35 milliseconds as typing speed.
     * <p>
     * Passing null to either Rgb parameter uses the console's default color.
     * 
     * @param text the String to print
     * @param background the RGB color for the background (or null to skip)
     * @param foreground the RGB color for the foreground (or null to skip)
     * @see Renderer#printColored(String, int, Rgb, Rgb)
     */
    public static void printColored(String text, Rgb background, Rgb foreground) {
        Renderer.printColored(text, Renderer.DEFAULT_DELAY, background, foreground);
    }

    /**
     * Prints text to the console character-by-character with specified background and
     * foreground RGB colors using the passed milliseconds delay as typing speed.
     * <p>
     * Passing null to either Rgb parameter uses the console's default color.
     * <p>
     * An ANSI reset sequence is appended to the end of the String; thus, it ensures
     * console formatting reverts to default when the String is printed, preventing
     * color bleeds.
     * 
     * @param text the String to print
     * @param milliseconds the pause duration between each character
     * @param background the RGB color for the background (or null to skip)
     * @param foreground the RGB color for the foreground (or null to skip)
     */
    public static void printColored(String text, int milliseconds, Rgb background, Rgb foreground) {

        if(text == null) {
            text = "null";
        }

        if((background == null) && (foreground == null)) {
            Renderer.print(text, milliseconds);
            return;
        }

        String backgroundSequence = (background != null) ? Ansi.getBackgroundSequence(background) : "";
        String foregroundSequence = (foreground != null) ? Ansi.getForegroundSequence(foreground) : "";
        String activeColors = backgroundSequence + foregroundSequence;
        
        // Prevents '\n' bleeds by resetting, printing '\n', then reactivating colors.
        String coloredText = text.replace("\n", Ansi.RESET + "\n" + activeColors);

        System.out.print(activeColors);
        Renderer.print(coloredText, milliseconds);
        System.out.print(Ansi.RESET);

    }

    // ------*------ Helper Methods ------*------

    /**
     * Pauses the current thread for the specified duration in milliseconds.
     * 
     * @param milliseconds the duration for the thread to sleep
     */
    private static void delay(int milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException interruptedException) {
            Thread.currentThread().interrupt(); 
        }

    }
    
}