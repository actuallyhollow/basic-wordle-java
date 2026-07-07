package com.omaridris.wordle.utilities;

/**
 * Performs conversion of RGB values to ANSI sequences.
 * <p>
 * This utility class handles the conversion required to print colored text to the terminal using ANSI sequences.
 * <p>
 * Cannot be instantiated, and can only be accessed statically.
 * 
 * @author Omar Idris
 */
public class Ansi {

    // ----*---- Attributes ----*----

    /**
     * The universal ANSI sequence to reset terminal text color back to default.
     */
    public static final String RESET = "\u001b[0m";

    /**
     * Prevents instantiation of new Ansi objects.
     */
    private Ansi() {}

    // ----*---- Sequence Generators ----*----

    /**
     * Converts an RGB color to an equivalent ANSI sequence for background coloring.
     * <p>
     * ANSI background sequences are formatted as "\u005Cu001b[48;2;R;G;Bm", such that "R;G;B" are the values of
     * the desired color, and "m" is a terminator character.
     * 
     * @param color a valid RGB color
     * @return the equivalent ANSI background sequence
     * @throws IllegalArgumentException if the passed RGB object is null
     */
    public static String getBackgroundSequence(RGB color) throws IllegalArgumentException {

        if(color == null) {
            throw new IllegalArgumentException("RGB object cannot be null.");
        }

        return String.format("\u001b[48;2;%d;%d;%dm", color.getR(), color.getG(), color.getB());

    }

    /**
     * Converts an RGB color to an equivalent ANSI sequence for foreground (text) coloring.
     * <p>
     * ANSI foreground sequences are formatted as "\u005Cu001b[38;2;R;G;Bm", such that "R;G;B" are the values of
     * the desired color, and "m" is a terminator character.
     * 
     * @param color a valid RGB color
     * @return the equivalent ANSI foreground sequence
     * @throws IllegalArgumentException if the passed RGB object is null
     */
    public static String getForegroundSequence(RGB color) throws IllegalArgumentException {

        if(color == null) {
            throw new IllegalArgumentException("RGB object cannot be null.");
        }

        return String.format("\u001b[38;2;%d;%d;%dm", color.getR(), color.getG(), color.getB());

    }

}