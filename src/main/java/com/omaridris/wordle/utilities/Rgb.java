package com.omaridris.wordle.utilities;

/**
 * Represents an immutable 24-bit RGB color.
 * <p>
 * This class defines the three 8-bit color channels; thus, it ensures valid RGB
 * values per channel.
 * <p>
 * It cannot be extended and can only be directly instantiated.
 * 
 * @author Omar Idris
 */
public final class Rgb {

    // ------*------ Attributes ------*------

    private final int red;
    private final int green;
    private final int blue;

    /**
     * Instantiates a new, valid Rgb object.
     * 
     * @param red the red value in the RGB color
     * @param green the green value in the RGB color
     * @param blue the blue value in the RGB color
     * @throws IllegalArgumentException if any passed value is out of range (0, 255)
     */
    public Rgb(int red, int green, int blue) throws IllegalArgumentException {

        this.validateRgb(red, green, blue);

        this.red = red;
        this.green = green;
        this.blue = blue;

    }

    // ------*------ Getters / Accessors ------*------

    /**
     * Gets the underlying red channel's value.
     * 
     * @return the red value in the Rgb object
     */
    public int getRed() {
        return this.red;
    }

    /**
     * Gets the underlying green channel's value.
     * 
     * @return the green value in the Rgb object
     */
    public int getGreen() {
        return this.green;
    }

    /**
     * Gets the underlying blue channel's value.
     * 
     * @return the blue value in the Rgb object
     */
    public int getBlue() {
        return this.blue;
    }

    // ------*------ Helper Methods ------*------

    /**
     * Checks whether the passed RGB channel values are valid.
     * 
     * @param red the red value in the RGB color
     * @param green the green value in the RGB color
     * @param blue the blue value in the RGB color
     * @throws IllegalArgumentException if any passed value is out of range (0, 255)
     */
    private void validateRgb(int red, int green, int blue) throws IllegalArgumentException {

        if(red < 0 || red > 255) {
            throw new IllegalArgumentException("Red value (" + red + ") is not within (0-255)");
        }
        if(green < 0 || green > 255) {
            throw new IllegalArgumentException("Green value (" + green + ") is not within (0-255)");
        }
        if(blue < 0 || blue > 255) {
            throw new IllegalArgumentException("Blue value (" + blue + ") is not within (0-255)");
        }

    }
    
}