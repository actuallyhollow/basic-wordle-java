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

    // ------*------ Testing getBackgroundSequence(RGB) ------*------

    /**
     * Tests {@link Ansi#getBackgroundSequence(RGB)} method to validate the conversion of
     * a valid RGB color into an ANSI background sequence.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario getBackgroundSequence(RGB) is invoked with a valid, non-null RGB color
     * @expected a corresponding ANSI background sequence is returned
     */
    @Test
    public void testGetBackgroundSequenceOnValidRGB() {

        // Arrange
        RGB validRGB = new RGB(195, 145, 255);
        String expectedSequence = "\u001b[48;2;195;145;255m";

        // Act
        String actualSequence = Ansi.getBackgroundSequence(validRGB);

        // Assert
        Assertions.assertEquals(expectedSequence, actualSequence, "A valid RGB should produce the expected ANSI background sequence.");

    }

    /**
     * Tests {@link Ansi#getBackgroundSequence(RGB)} method to validate the proper
     * handling of null RGB colors.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario getBackgroundSequence(RGB) is invoked with a null RGB color
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testGetBackgroundSequenceOnNullRGB() {

        // Arrange
        RGB nullRGB = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {Ansi.getBackgroundSequence(nullRGB);}, "A null RGB should result in IllegalArgumentException.");

    }

    // ------*------ Testing getForegroundSequence(RGB) ------*------

    /**
     * Tests {@link Ansi#getForegroundSequence(RGB)} method to validate the conversion of
     * a valid RGB color into an ANSI foreground sequence.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario getForegroundSequence(RGB) is invoked with a valid, non-null RGB color
     * @expected a corresponding ANSI foreground sequence is returned
     */
    @Test
    public void testGetForegroundSequenceOnValidRGB() {

        // Arrange
        RGB validRGB = new RGB(195, 145, 255);
        String expectedSequence = "\u001b[38;2;195;145;255m";

        // Act
        String actualSequence = Ansi.getForegroundSequence(validRGB);

        // Assert
        Assertions.assertEquals(expectedSequence, actualSequence, "A valid RGB should produce the expected ANSI foreground sequence.");

    }

    /**
     * Tests {@link Ansi#getForegroundSequence(RGB)} method to validate the proper
     * handling of null RGB colors.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario getForegroundSequence(RGB) is invoked with a null RGB color
     * @expected IllegalArgumentException is thrown
     */
    @Test
    public void testGetForegroundSequenceOnNullRGB() {

        // Arrange
        RGB nullRGB = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {Ansi.getForegroundSequence(nullRGB);}, "A null RGB should result in IllegalArgumentException.");

    }

}