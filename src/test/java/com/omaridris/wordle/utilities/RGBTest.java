package com.omaridris.wordle.utilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Conducts tests on the methods of {@link Rgb} using JUnit API.
 * <p>
 * Trivial methods (e.g. standard constructors, setters, or getters) are not tested;
 * thus, only methods with non-linear logic will be tested.
 * 
 * @author Omar Idris
 */
public class RgbTest {

    // ------*------ Testing Rgb(int, int, int) ------*------

    /**
     * Tests {@link Rgb#Rgb(int, int, int)} constructor to validate proper instantiation
     * on valid RGB channel values.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario Rgb(int, int, int) is invoked with valid red, green, and blue values
     * @expected an immutable Rgb object is instantiated with the values as attributes
     */
    @Test
    public void testInstantiationOnValidValues() {

        // Arrange
        int validRed = 195;
        int validGreen = 145;
        int validBlue = 255;

        // Act
        Rgb rgb = new Rgb(validRed, validGreen, validBlue);
        int actualRed = rgb.getRed();
        int actualGreen = rgb.getGreen();
        int actualBlue = rgb.getBlue();

        // Assert
        Assertions.assertNotNull(rgb, "An Rgb object with valid values should not be null.");
        Assertions.assertEquals(validRed, actualRed, "Red attribute should equal the passed red value.");
        Assertions.assertEquals(validGreen, actualGreen, "Green attribute should equal the passed green value.");
        Assertions.assertEquals(validBlue, actualBlue, "Blue attribute should equal the passed blue value.");

    }

    /**
     * Tests {@link Rgb#Rgb(int, int, int)} constructor to validate the proper handling
     * of invalid red channel values.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario Rgb(int, int, int) is invoked with an invalid red value
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testInstantiationOnInvalidRed() {

        // Arrange
        int invalidRed = 315;
        int validGreen = 145;
        int validBlue = 255;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Rgb(invalidRed, validGreen, validBlue);}, "Invalid red values should result in IllegalArgumentException.");

    }

    /**
     * Tests {@link Rgb#Rgb(int, int, int)} constructor to validate the proper handling
     * of invalid green channel values.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario Rgb(int, int, int) is invoked with an invalid green value
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testInstantiationOnInvalidGreen() {

        // Arrange
        int validRed = 195;
        int invalidGreen = 266;
        int validBlue = 255;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Rgb(validRed, invalidGreen, validBlue);}, "Invalid green values should result in IllegalArgumentException.");

    }

    /**
     * Tests {@link Rgb#Rgb(int, int, int)} constructor to validate the proper handling
     * of invalid blue channel values.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario Rgb(int, int, int) is invoked with an invalid blue value
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testInstantiationOnInvalidBlue() {

        // Arrange
        int validRed = 195;
        int validGreen = 145;
        int invalidBlue = -125;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Rgb(validRed, validGreen, invalidBlue);}, "Invalid blue values should result in IllegalArgumentException.");

    }

}