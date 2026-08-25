package com.omaridris.wordle.utilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Conducts tests on the methods of {@link Ansi} using JUnit API.
 * <p>
 * Trivial methods (e.g. standard constructors, setters, or getters) are not tested;
 * thus, only methods with non-linear logic will be tested.
 * 
 * @author Omar Idris
 */
public class AnsiTest {

    // ------*------ Testing getBackgroundSequence(Rgb) ------*------

    /**
     * Tests {@link Ansi#getBackgroundSequence(Rgb)} method to validate the conversion of
     * valid RGB colors into ANSI background sequences.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario getBackgroundSequence(Rgb) is invoked with a valid Rgb object
     * @expected a corresponding ANSI background sequence is returned
     */
    @Test
    public void testGetBackgroundSequenceWithValidRgb() {

        // Arrange
        Rgb validRgb = new Rgb(195, 145, 255);
        String expectedSequence = "\u001b[48;2;195;145;255m";

        // Act
        String actualSequence = Ansi.getBackgroundSequence(validRgb);

        // Assert
        Assertions.assertEquals(expectedSequence, actualSequence, "A valid RGB color should produce the expected ANSI background sequence.");

    }

    /**
     * Tests {@link Ansi#getBackgroundSequence(Rgb)} method to validate the proper
     * handling of null RGB colors.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario getBackgroundSequence(Rgb) is invoked with a null Rgb object
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testGetBackgroundSequenceWithNullRgb() {

        // Arrange
        Rgb nullRgb = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {Ansi.getBackgroundSequence(nullRgb);}, "A null RGB color should result in IllegalArgumentException.");

    }

    // ------*------ Testing getForegroundSequence(Rgb) ------*------

    /**
     * Tests {@link Ansi#getForegroundSequence(Rgb)} method to validate the conversion of
     * valid RGB colors into ANSI foreground sequences.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario getForegroundSequence(Rgb) is invoked with a valid Rgb object
     * @expected a corresponding ANSI foreground sequence is returned
     */
    @Test
    public void testGetForegroundSequenceWithValidRgb() {

        // Arrange
        Rgb validRgb = new Rgb(195, 145, 255);
        String expectedSequence = "\u001b[38;2;195;145;255m";

        // Act
        String actualSequence = Ansi.getForegroundSequence(validRgb);

        // Assert
        Assertions.assertEquals(expectedSequence, actualSequence, "A valid RGB color should produce the expected ANSI foreground sequence.");

    }

    /**
     * Tests {@link Ansi#getForegroundSequence(Rgb)} method to validate the proper
     * handling of null RGB colors.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario getForegroundSequence(Rgb) is invoked with a null Rgb object
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testGetForegroundSequenceWithNullRgb() {

        // Arrange
        Rgb nullRgb = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {Ansi.getForegroundSequence(nullRgb);}, "A null RGB color should result in IllegalArgumentException.");

    }

}