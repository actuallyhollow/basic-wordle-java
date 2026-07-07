package com.omaridris.wordle.utilities;

/**
 * Represents an immutable 24-bit RGB color.
 * <p>
 * This class defines the three 8-bit color channels, ensuring valid RGB values per channel.
 * 
 * @author Omar Idris
 */
public class RGB {

    // ----*---- Attributes ----*----

    private final int r;
    private final int g;
    private final int b;

    /**
     * Instantiates a new RGB object.
     * 
     * @param r the red value in the RGB
     * @param g the green value in the RGB
     * @param b the blue value in the RGB
     * @throws IllegalArgumentException if either passed values is out of range (0, 255)
     */
    public RGB(int r, int g, int b) throws IllegalArgumentException {

        this.validateRGB(r, g, b);

        this.r = r;
        this.g = g;
        this.b = b;

    }

    // ----*---- Getters / Accessors ----*----

    /**
     * Gets the value of r.
     * 
     * @return The r value in the RGB object.
     */
    public int getR() {
        return this.r;
    }

    /**
     * Gets the value of g.
     * 
     * @return The g value in the RGB object.
     */
    public int getG() {
        return this.g;
    }

    /**
     * Gets the value of b.
     * 
     * @return The b value in the RGB object.
     */
    public int getB() {
        return this.b;
    }

    // ----*---- Helper Methods ----*----

    /**
     * Checks whether the passed RGB values are valid.
     * <p>
     * 
     * @param r the red value in the RGB
     * @param g the green value in the RGB
     * @param b the blue value in the RGB
     * @throws IllegalArgumentException if either passed values is out of range (0, 255)
     */
    private void validateRGB(int r, int g, int b) throws IllegalArgumentException {

        if(r < 0 || r > 255) {
            throw new IllegalArgumentException("Red value (" + r + ") is not within (0-255)");
        }
        if(g < 0 || g > 255) {
            throw new IllegalArgumentException("Green value (" + g + ") is not within (0-255)");
        }
        if(b < 0 || b > 255) {
            throw new IllegalArgumentException("Blue value (" + b + ") is not within (0-255)");
        }

    }
    
}