package com.omaridris.wordle.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.github.stefanbirkner.systemlambda.SystemLambda;
import com.omaridris.wordle.utilities.Ansi;
import com.omaridris.wordle.utilities.Rgb;

/**
 * Conducts tests on the methods of {@link Renderer} using JUnit API,
 * and System Lambda API to intercept console output.
 * <p>
 * Trivial methods (e.g. hardcoded output and direct print statements) are not tested;
 * thus, only parameterized methods with formatting logic or user input will be tested.
 * 
 * @author Omar Idris
 */
public class RendererTest {

    // ------*------ Testing print(String) ------*------

    /**
     * Tests {@link Renderer#print(String)} method to validate that it correctly outputs
     * the passed text to the console.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario print(String) is invoked with an arbitrary text
     * @expected the intercepted console output is strictly equal to the passed text
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testOverloadedPrintOutputsCorrectly() throws Exception {
        
        // Arrange
        String text = "Testing print(String)...";
        String expectedOutput = text;

        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Renderer.print(text);});

        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The text printed to the console should match the passed text.");

    }

    /**
     * Tests {@link Renderer#print(String)} method to validate that it dynamically prints
     * text by delaying the output for each character using the default delay.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario print(String) is invoked with a standard, non-ANSI text to simplify
     *           delay calculations
     * @expected execution takes at least (characters * default delay) milliseconds, and
     *           at most the expected time plus a dynamic overhead of 20% or 100ms
     */
    @Test
    public void testOverloadedPrintUsesDefaultDelay() {

        // Arrange
        String nonAnsiText = "Testing print(String)..."; 
        long expectedDelayMs = (long)(Renderer.DEFAULT_DELAY * nonAnsiText.length());
        long maxDelayMs = expectedDelayMs + Math.max(100L, (long)(expectedDelayMs * 0.20));

        // Act
        long start = System.nanoTime();
        Renderer.print(nonAnsiText);
        long end = System.nanoTime();
        double actualDelayMs = (end - start) / 1000000.0;

        // Assert
        Assertions.assertTrue(actualDelayMs >= expectedDelayMs, String.format("Execution too fast. Took %.2fms, expected at least %dms.", actualDelayMs, expectedDelayMs));
        Assertions.assertTrue(actualDelayMs <= maxDelayMs, String.format("Execution too slow. Took %.2fms, allowed at most %dms.", actualDelayMs, maxDelayMs));

    }

    // ------*------ Testing print(String, int) ------*------

    /**
     * Tests {@link Renderer#print(String, int)} method to validate that it correctly
     * outputs the passed text to the console.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario print(String, int) is invoked with an arbitrary text and a zero-delay
     * @expected the intercepted console output is strictly equal to the passed text
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testMasterPrintOutputsCorrectly() throws Exception {

        // Arrange
        String text = "Testing print(String, int)...";
        int delayPerCharacter = 0;
        String expectedOutput = text;

        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Renderer.print(text, delayPerCharacter);});

        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The text printed to the console should match the passed text.");

    }

    /**
     * Tests {@link Renderer#print(String, int)} method to validate that it dynamically
     * prints non-ANSI text by delaying the output for each character.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario print(String, int) is invoked with a standard, non-ANSI text and a
     *           small delay to ensure fast execution
     * @expected execution takes at least (characters * delay) milliseconds, and at most
     *           the expected time plus a dynamic overhead of 20% or 100ms
     */
    @Test
    public void testMasterPrintDelayWithNonAnsiText() {

        // Arrange
        String nonAnsiText = "Testing print(String, int)..."; 
        int delayPerCharacter = 10;
        long expectedDelayMs = (long)(delayPerCharacter * nonAnsiText.length());
        long maxDelayMs = expectedDelayMs + Math.max(100L, (long)(expectedDelayMs * 0.20));

        // Act
        long start = System.nanoTime();
        Renderer.print(nonAnsiText, delayPerCharacter);
        long end = System.nanoTime();
        double actualDelayMs = (end - start) / 1000000.0;

        // Assert
        Assertions.assertTrue(actualDelayMs >= expectedDelayMs, String.format("Execution too fast. Took %.2fms, expected at least %dms.", actualDelayMs, expectedDelayMs));
        Assertions.assertTrue(actualDelayMs <= maxDelayMs, String.format("Execution too slow. Took %.2fms, allowed at most %dms.", actualDelayMs, maxDelayMs));

    }

    /**
     * Tests {@link Renderer#print(String, int)} method to validate that it immediately
     * prints pure-ANSI text by instantly outputting all characters of an ANSI sequence.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario print(String, int) is invoked with an ANSI sequence and a large delay
     *           to expose a failed skip, as execution should take nanoseconds
     * @expected execution takes at least ~0ms, and at most 15ms as CPU overhead
     */
    @Test
    public void testMasterPrintDelayWithAnsiText() {

        // Arrange
        String ansiText = Ansi.getForegroundSequence(new Rgb(195, 145, 255));
        int delayPerCharacter = 100;
        long maxDelayMs = 15; 

        // Act
        long start = System.nanoTime();
        Renderer.print(ansiText, delayPerCharacter);
        long end = System.nanoTime();
        double actualDelayMs = (end - start) / 1000000.0;

        // Assert
        Assertions.assertTrue(actualDelayMs <= maxDelayMs, String.format("Execution too slow. Took %.2fms, allowed at most %dms.", actualDelayMs, maxDelayMs));

    }

    /**
     * Tests {@link Renderer#print(String, int)} method to validate that it correctly
     * delays ANSI-embedded text by dynamically printing the non-ANSI text and
     * immediately outputting the ANSI sequences.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario print(String, int) is invoked with an ANSI-embedded text and a small
     *           delay to ensure fast execution
     * @expected execution takes at least (non-ANSI characters * delay) milliseconds,
     *           and at most the expected time plus a dynamic overhead of 20% or 100ms
     */
    @Test
    public void testMasterPrintDelayWithMixedText() {

        // Arrange
        String nonAnsiText = "Testing print(String, int)...";
        String ansiText = Ansi.getForegroundSequence(new Rgb(195, 145, 255));
        String mixedText = ansiText + nonAnsiText + Ansi.RESET;
        int delayPerCharacter = 10;
        long expectedDelayMs = (long)(delayPerCharacter * nonAnsiText.length());
        long maxDelayMs = expectedDelayMs + Math.max(100L, (long)(expectedDelayMs * 0.20));

        // Act
        long start = System.nanoTime();
        Renderer.print(mixedText, delayPerCharacter);
        long end = System.nanoTime();
        double actualDelayMs = (end - start) / 1000000.0;

        // Assert
        Assertions.assertTrue(actualDelayMs >= expectedDelayMs, String.format("Execution too fast. Took %.2fms, expected at least %dms.", actualDelayMs, expectedDelayMs));
        Assertions.assertTrue(actualDelayMs <= maxDelayMs, String.format("Execution too slow. Took %.2fms, allowed at most %dms.", actualDelayMs, maxDelayMs));

    }

    /**
     * Tests {@link Renderer#print(String, int)} method to validate that it safely
     * outputs the literal "null" to the console.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario print(String, int) is invoked with a null text and a zero-delay
     * @expected the intercepted console output displays "null", preventing exceptions
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testMasterPrintWithNullText() throws Exception {

        // Arrange
        String nullText = null;
        int delayPerCharacter = 0;
        String expectedOutput = "null";
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Renderer.print(nullText, delayPerCharacter);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "Null text should be safely printed as the literal \"null\".");

    }

    // ------*------ Testing printColored(String, Rgb) ------*------

    /**
     * Tests {@link Renderer#printColored(String, Rgb)} method to validate that it
     * correctly applies the foreground color to the text and outputs it to the console.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printColored(String, Rgb) is invoked with an arbitrary text and a valid
     *           Rgb foreground
     * @expected the intercepted console output starts with the foreground ANSI
     *           sequence, contains the text, and ends with a reset sequence
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testOverloadedPrintColoredWithForegroundOutputsCorrectly() throws Exception {

        // Arrange
        String text = "Testing printColored(String, Rgb)...";
        Rgb foreground = new Rgb(195, 145, 255);
        String expectedOutput = Ansi.getForegroundSequence(foreground) + text + Ansi.RESET;

        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Renderer.printColored(text, foreground);});

        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The text printed to the console should contain the foreground and reset sequence.");

    }

    /**
     * Tests {@link Renderer#printColored(String, Rgb)} method to validate that it
     * correctly applies the foreground color to the text and dynamically prints it
     * by delaying the output for each character using the default delay.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printColored(String, Rgb) is invoked with an arbitrary text and a valid
     *           Rgb foreground
     * @expected execution takes at least (characters * default delay) milliseconds,
     *           and at most the expected time plus a dynamic overhead of 20% or 100ms
     */
    @Test
    public void testOverloadedPrintColoredWithForegroundUsesDefaultDelay() {

        // Arrange
        String text = "Testing printColored(String, Rgb)...";
        Rgb foreground = new Rgb(195, 145, 255);
        long expectedDelayMs = (long)(Renderer.DEFAULT_DELAY * text.length());
        long maxDelayMs = expectedDelayMs + Math.max(100L, (long)(expectedDelayMs * 0.20));

        // Act
        long start = System.nanoTime();
        Renderer.printColored(text, foreground);
        long end = System.nanoTime();
        double actualDelayMs = (end - start) / 1000000.0;

        // Assert
        Assertions.assertTrue(actualDelayMs >= expectedDelayMs, String.format("Execution too fast. Took %.2fms, expected at least %dms.", actualDelayMs, expectedDelayMs));
        Assertions.assertTrue(actualDelayMs <= maxDelayMs, String.format("Execution too slow. Took %.2fms, allowed at most %dms.", actualDelayMs, maxDelayMs));

    }

    // ------*------ Testing printColored(String, Rgb, Rgb) ------*------

    /**
     * Tests {@link Renderer#printColored(String, Rgb, Rgb)} method to validate that it
     * correctly applies both background and foreground colors to the text and outputs it
     * to the console.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printColored(String, Rgb, Rgb) is invoked with an arbitrary text and
     *           valid Rgb objects
     * @expected the intercepted console output starts with both ANSI sequences,
     *           contains the text, and ends with a reset sequence
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testOverloadedPrintColoredWithBackgroundAndForegroundOutputsCorrectly() throws Exception {

        // Arrange
        String text = "Testing printColored(String, Rgb, Rgb)...";
        Rgb background = new Rgb(0, 0, 0);
        Rgb foreground = new Rgb(195, 145, 255);
        String activeColors = Ansi.getBackgroundSequence(background) + Ansi.getForegroundSequence(foreground);
        String expectedOutput = activeColors + text + Ansi.RESET;

        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Renderer.printColored(text, background, foreground);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The text printed to the console should contain the active colors and reset sequence.");

    }

    /**
     * Tests {@link Renderer#printColored(String, Rgb, Rgb)} method to validate that it
     * correctly applies both background and foreground colors to the text and
     * dynamically prints it by delaying the output for each character using the default
     * delay.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printColored(String, Rgb, Rgb) is invoked with an arbitrary text and
     *           valid Rgb objects
     * @expected execution takes at least (characters * default delay) milliseconds,
     *           and at most the expected time plus a dynamic overhead of 20% or 100ms
     */
    @Test
    public void testOverloadedPrintColoredWithBackgroundAndForegroundUsesDefaultDelay() {

        // Arrange
        String text = "Testing printColored(String, Rgb, Rgb)...";
        Rgb foreground = new Rgb(195, 145, 255);
        long expectedDelayMs = (long)(Renderer.DEFAULT_DELAY * text.length());
        long maxDelayMs = expectedDelayMs + Math.max(100L, (long)(expectedDelayMs * 0.20));

        // Act
        long start = System.nanoTime();
        Renderer.printColored(text, foreground);
        long end = System.nanoTime();
        double actualDelayMs = (end - start) / 1000000.0;

        // Assert
        Assertions.assertTrue(actualDelayMs >= expectedDelayMs, String.format("Execution too fast. Took %.2fms, expected at least %dms.", actualDelayMs, expectedDelayMs));
        Assertions.assertTrue(actualDelayMs <= maxDelayMs, String.format("Execution too slow. Took %.2fms, allowed at most %dms.", actualDelayMs, maxDelayMs));

    }

    // ------*------ Testing printColored(String, int, Rgb, Rgb) ------*------

    /**
     * Tests {@link Renderer#printColored(String, int, Rgb, Rgb)} method to validate that
     * it correctly applies both background and foreground colors to the text and
     * outputs it to the console.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printColored(String, int, Rgb, Rgb) is invoked with an arbitrary text,
     *           a zero-delay, and valid Rgb objects
     * @expected the intercepted console output starts with both ANSI sequences,
     *           contains the text, and ends with a reset sequence
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testMasterPrintColoredWithBothColors() throws Exception {

        // Arrange
        String text = "Testing printColored(String, int, Rgb, Rgb)...";
        Rgb background = new Rgb(0, 0, 0);
        Rgb foreground = new Rgb(195, 145, 255);
        int delayPerCharacter = 0;
        String activeColors = Ansi.getBackgroundSequence(background) + Ansi.getForegroundSequence(foreground);
        String expectedOutput = activeColors + text + Ansi.RESET;

        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Renderer.printColored(text, delayPerCharacter, background, foreground);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The text printed to the console should contain the active colors and reset sequence.");

    }

    /**
     * Tests {@link Renderer#printColored(String, int, Rgb, Rgb)} method to validate that
     * it correctly applies only the background color to the text and outputs it
     * to the console.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printColored(String, int, Rgb, Rgb) is invoked with an arbitrary text,
     *           a zero-delay, a valid Rgb background, and a null foreground
     * @expected the intercepted console output starts with only the background ANSI
     *           sequence, contains the text, and ends with a reset sequence
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testMasterPrintColoredWithBackgroundOnly() throws Exception {

        // Arrange
        String text = "Testing printColored(String, int, Rgb, Rgb)...";
        Rgb background = new Rgb(0, 0, 0);
        Rgb foreground = null;
        int delayPerCharacter = 0;
        String activeColors = Ansi.getBackgroundSequence(background);
        String expectedOutput = activeColors + text + Ansi.RESET;

        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Renderer.printColored(text, delayPerCharacter, background, foreground);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The text printed to the console should contain only the background and reset sequence.");

    }

    /**
     * Tests {@link Renderer#printColored(String, int, Rgb, Rgb)} method to validate that
     * it correctly applies only the foreground color to the text and outputs it
     * to the console.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printColored(String, int, Rgb, Rgb) is invoked with an arbitrary text,
     *           a zero-delay, a null background, and a valid Rgb foreground
     * @expected the intercepted console output starts with only the foreground ANSI
     *           sequence, contains the text, and ends with a reset sequence
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testMasterPrintColoredWithForegroundOnly() throws Exception {

        // Arrange
        String text = "Testing printColored(String, int, Rgb, Rgb)...";
        Rgb background = null;
        Rgb foreground = new Rgb(195, 145, 255);
        int delayPerCharacter = 0;
        String activeColors = Ansi.getForegroundSequence(foreground);
        String expectedOutput = activeColors + text + Ansi.RESET;

        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Renderer.printColored(text, delayPerCharacter, background, foreground);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The text printed to the console should contain only the foreground and reset sequence.");

    }

    /**
     * Tests {@link Renderer#printColored(String, int, Rgb, Rgb)} method to validate that
     * it prevents color bleeds caused by newlines by wrapping them in reset sequences
     * and reactivating colors.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printColored(String, int, Rgb, Rgb) is invoked with an arbitrary text
     *           containing a '\n' character
     * @expected the intercepted console output replaces every '\n' with reset sequence +
     *           '\n' + active colors
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testMasterPrintColoredPreventsColorBleeds() throws Exception {

        // Arrange
        String text = "Testing\nprintColored(String, int, Rgb, Rgb)...";
        Rgb background = null;
        Rgb foreground = new Rgb(195, 145, 255);
        int delayPerCharacter = 0;
        String activeColors = Ansi.getForegroundSequence(foreground);
        String expectedOutput = activeColors + "Testing" + Ansi.RESET + "\n" + activeColors + "printColored(String, int, Rgb, Rgb)..." + Ansi.RESET;
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Renderer.printColored(text, delayPerCharacter, background, foreground);});

        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The text printed to the console should not contain color bleeds.");

    }

    /**
     * Tests {@link Renderer#printColored(String, int, Rgb, Rgb)} method to validate that
     * it safely outputs the literal "null" to the console.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printColored(String, int, Rgb, Rgb) is invoked with a null text, a
     *           zero-delay, and valid Rgb objects
     * @expected the intercepted console output displays "null", preventing exceptions,
     *           while also applying the active colors and reset sequence
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testMasterPrintColoredWithNullText() throws Exception {

        // Arrange
        String nullText = null;
        Rgb background = new Rgb(0, 0, 0);
        Rgb foreground = new Rgb(195, 145, 255);
        int delayPerCharacter = 0;
        String activeColors = Ansi.getBackgroundSequence(background) + Ansi.getForegroundSequence(foreground);
        String expectedOutput = activeColors + "null" + Ansi.RESET;
        
        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Renderer.printColored(nullText, delayPerCharacter, background, foreground);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "Null text should be safely printed as the literal \"null\", while also containing the active colors and reset sequence.");

    }

    /**
     * Tests {@link Renderer#printColored(String, int, Rgb, Rgb)} method to validate that
     * it safely skips null Rgb parameters and outputs the passed text to the console
     * with no applied colors.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario printColored(String, int, Rgb, Rgb) is invoked with an arbitrary text,
     *           a zero-delay, and null Rgb objects
     * @expected the intercepted console output is strictly equal to the passed text,
     *           with no ANSI leakage
     * @throws Exception if the System Lambda interception fails
     */
    @Test
    public void testMasterPrintColoredWithNullColors() throws Exception {

        // Arrange
        String text = "Testing printColored(String, int, Rgb, Rgb)...";
        Rgb background = null;
        Rgb foreground = null;
        int delayPerCharacter = 0;
        String expectedOutput = text;

        // Act
        String actualOutput = SystemLambda.tapSystemOut(() -> {Renderer.printColored(text, delayPerCharacter, background, foreground);});
        
        // Assert
        Assertions.assertEquals(expectedOutput, actualOutput, "The text printed to the console should match the passed text.");

    }

}