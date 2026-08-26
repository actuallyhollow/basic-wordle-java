package com.omaridris.wordle.utilities;

/**
 * Converts RGB colors into ANSI sequences.
 * <p>
 * This utility class handles the string translation required to print visually colored
 * text to the console using ANSI escape sequences.
 * <p>
 * It cannot be extended or instantiated and can only be statically accessed.
 * 
 * @author Omar Idris
 */
public final class Ansi {

    // ------*------ Attributes ------*------

    /**
     * The universal ANSI sequence to reset console text color back to default.
     */
    public static final String RESET = "\u001b[0m";

    /**
     * Prevents instantiation of new Ansi objects.
     * 
     * @throws UnsupportedOperationException if instantiated internally or by Reflection.
     */
    private Ansi() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Ansi objects should not be instantiated.");
    }

    // ------*------ Sequence Generation ------*------

    /**
     * Converts an RGB color to an equivalent ANSI sequence for background coloring.
     * <p>
     * ANSI background sequences are formatted as "\u005Cu001b[48;2;R;G;Bm", such that
     * "R;G;B" are the values of the desired color, and "m" is a terminator character.
     * 
     * @param rgb a valid RGB color
     * @return the equivalent ANSI background sequence
     * @throws IllegalArgumentException if the passed Rgb object is null
     */
    public static String getBackgroundSequence(Rgb rgb) throws IllegalArgumentException {

        if(rgb == null) {
            throw new IllegalArgumentException("Rgb object cannot be null.");
        }

        return String.format("\u001b[48;2;%d;%d;%dm", rgb.getRed(), rgb.getGreen(), rgb.getBlue());

    }

    /**
     * Converts an RGB color to an equivalent ANSI sequence for foreground coloring.
     * <p>
     * ANSI foreground sequences are formatted as "\u005Cu001b[38;2;R;G;Bm", such that
     * "R;G;B" are the values of the desired color, and "m" is a terminator character.
     * 
     * @param rgb a valid RGB color
     * @return the equivalent ANSI foreground sequence
     * @throws IllegalArgumentException if the passed Rgb object is null
     */
    public static String getForegroundSequence(Rgb rgb) throws IllegalArgumentException {

        if(rgb == null) {
            throw new IllegalArgumentException("Rgb object cannot be null.");
        }

        return String.format("\u001b[38;2;%d;%d;%dm", rgb.getRed(), rgb.getGreen(), rgb.getBlue());

    }

}