package com.omaridris.wordle.utilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Conducts tests on the methods of {@link RGB} using JUnit API.
 * <p>
 * Trivial methods (e.g. standard constructors, setters, getters) are not tested.
 * Only methods with non-linear logic will be tested.
 * 
 * @author Omar Idris
 */
public class RGBTest {

    // ----*---- Testing Instantiation ----*----

    /**
     * Tests {@link RGB#RGB(int, int, int)} constructor to validate proper instantiation on valid RGB values.
     * <p>
     * Tests are conducted using the AAA pattern (arrange, act, assert).
     * <ul><li>
     *   <b>Scenario:</b>
     *     <ul><li>
     *       RGB() is invoked with valid R, G, B values (0, 255).
     *     </li></ul>
     * </li></ul>
     * <ul><li>
     *   <b>Expected:</b>
     *     <ul><li>
     *       an immutable RGB object is instantiated with the valid values as attributes.
     *     </li></ul>
     * </li></ul>
     */
    @Test
    public void testInstantiationOnValidValues() {

        // Arrange
        int validRed = 195;
        int validGreen = 145;
        int validBlue = 255;

        // Act
        RGB rgb = new RGB(validRed, validGreen, validBlue);
        int actualRed = rgb.getR();
        int actualGreen = rgb.getG();
        int actualBlue = rgb.getB();

        // Assert
        Assertions.assertNotNull(rgb, "An RGB object with valid values should not be null.");
        Assertions.assertEquals(validRed, actualRed, "R attribute should equal the passed R value");
        Assertions.assertEquals(validGreen, actualGreen, "G attribute should equal the passed G value");
        Assertions.assertEquals(validBlue, actualBlue, "B attribute should equal the passed B value");

    }

    /**
     * Tests {@link RGB#RGB(int, int, int)} constructor to validate proper instantiation on invalid R values.
     * <p>
     * Tests are conducted using the AAA pattern (arrange, act, assert).
     * <ul><li>
     *   <b>Scenario:</b>
     *     <ul><li>
     *       RGB() is invoked with an invalid R.
     *     </li></ul>
     * </li></ul>
     * <ul><li>
     *   <b>Expected:</b>
     *     <ul><li>
     *       IllegalArgumentException is thrown.
     *     </li></ul>
     * </li></ul>
     */
    @Test
    public void testInstantiationOnInvalidRed() {

        // Arrange
        int invalidRed = 315;
        int validGreen = 145;
        int validBlue = 255;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new RGB(invalidRed, validGreen, validBlue);}, "Invalid R values should result in IllegalArgumentException.");

    }

    /**
     * Tests {@link RGB#RGB(int, int, int)} constructor to validate proper instantiation on invalid G values.
     * <p>
     * Tests are conducted using the AAA pattern (arrange, act, assert).
     * <ul><li>
     *   <b>Scenario:</b>
     *     <ul><li>
     *       RGB() is invoked with an invalid G.
     *     </li></ul>
     * </li></ul>
     * <ul><li>
     *   <b>Expected:</b>
     *     <ul><li>
     *       IllegalArgumentException is thrown.
     *     </li></ul>
     * </li></ul>
     */
    @Test
    public void testInstantiationOnInvalidGreen() {

        // Arrange
        int validRed = 195;
        int invalidGreen = 266;
        int validBlue = 255;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new RGB(validRed, invalidGreen, validBlue);}, "Invalid G values should result in IllegalArgumentException.");

    }

    /**
     * Tests {@link RGB#RGB(int, int, int)} constructor to validate proper instantiation on invalid B values.
     * <p>
     * Tests are conducted using the AAA pattern (arrange, act, assert).
     * <ul><li>
     *   <b>Scenario:</b>
     *     <ul><li>
     *       RGB() is invoked with an invalid B.
     *     </li></ul>
     * </li></ul>
     * <ul><li>
     *   <b>Expected:</b>
     *     <ul><li>
     *       IllegalArgumentException is thrown.
     *     </li></ul>
     * </li></ul>
     */
    @Test
    public void testInstantiationOnInvalidBlue() {

        // Arrange
        int validRed = 195;
        int validGreen = 145;
        int invalidBlue = -125;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new RGB(validRed, validGreen, invalidBlue);}, "Invalid B values should result in IllegalArgumentException.");

    }

}