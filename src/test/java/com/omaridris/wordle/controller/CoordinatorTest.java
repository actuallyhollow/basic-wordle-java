package com.omaridris.wordle.controller;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.github.stefanbirkner.systemlambda.SystemLambda;
import com.omaridris.wordle.model.Dictionary;
import com.omaridris.wordle.model.Session;
import com.omaridris.wordle.view.Composer;

/**
 * Conducts tests on the methods of {@link Coordinator} using JUnit API,
 * and System Lambda API to inject or intercept console streams.
 * <p>
 * Trivial methods (e.g. simple model routing and basic view updates) are not tested;
 * thus, only methods with dynamic model routing and lifecycle execution will be tested.
 * 
 * @author Omar Idris
 */
public class CoordinatorTest {

    // ------*------ Testing Coordinator(Session, Composer) ------*------

    /**
     * Tests {@link Coordinator#Coordinator(Session, Composer)} constructor to validate
     * the proper instantiation of Coordinator objects with valid dependencies.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario Coordinator(Session, Composer) is invoked with valid dependencies
     * @expected a Coordinator object is successfully instantiated without any exceptions
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testInstantiationWithValidDependencies() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        Composer composer = new Composer(System.in);

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> {new Coordinator(session, composer);}, "Valid dependencies should successfully instantiate a coordinator.");

    }

    /**
     * Tests {@link Coordinator#Coordinator(Session, Composer)} constructor to validate
     * the proper handling of null Session dependencies.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario Coordinator(Session, Composer) is invoked with a null Session
     * @expected IllegalArgumentException is thrown
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testInstantiationWithNullSession() throws Exception {

        // Arrange
        Session nullSession = null;
        Composer composer = new Composer(System.in);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Coordinator(nullSession, composer);}, "An invalid null session should result in IllegalArgumentException.");

    }

    /**
     * Tests {@link Coordinator#Coordinator(Session, Composer)} constructor to validate
     * the proper handling of null Composer dependencies.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario Coordinator(Session, Composer) is invoked with a null Composer
     * @expected IllegalArgumentException is thrown
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testInstantiationWithNullComposer() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        Composer nullComposer = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Coordinator(session, nullComposer);}, "An invalid null composer should result in IllegalArgumentException.");

    }

    // ------*------ Testing launch() ------*------

    /**
     * Tests {@link Coordinator#launch()} method to validate that providing a valid guess
     * successfully progresses the Session.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario launch() is invoked
     * @expected the Session's state progresses given a Word-valid guess
     * @throws Exception if System Lambda injection, interception, or dependencies fail
     */
    @Test
    public void testLaunchProgressesSessionGivenValidGuess() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String validGuess = session.getTarget().getText();
        int expectedRemainingAttempts = 5;

        // Act
        SystemLambda.tapSystemOut(() -> {
            SystemLambda.withTextFromSystemIn(validGuess).execute(() -> {
                Composer composer = new Composer(System.in);
                Coordinator coordinator = new Coordinator(session, composer);
                coordinator.launch();
            });
        });
        int actualRemainingAttempts = session.getRemainingAttempts();

        // Assert
        Assertions.assertEquals(expectedRemainingAttempts, actualRemainingAttempts, "A valid guess should progress the session once.");

    }

    /**
     * Tests {@link Coordinator#launch()} method to validate that providing an invalid
     * guess does not unfairly progress the Session.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario launch() is invoked
     * @expected the Session's state does not progress given a Word-invalid guess
     * @throws Exception if System Lambda injection, interception, or dependencies fail
     */
    @Test
    public void testLaunchDoesNotProgressSessionGivenInvalidGuess() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String invalidGuess = "zzzzz";
        int expectedRemainingAttempts = 6;

        // Act
        SystemLambda.tapSystemOut(() -> {
            SystemLambda.withTextFromSystemIn(invalidGuess).execute(() -> {
                Composer composer = new Composer(System.in);
                Coordinator coordinator = new Coordinator(session, composer);
                Assertions.assertThrows(NoSuchElementException.class, () -> {coordinator.launch();});
            });
        });
        int actualRemainingAttempts = session.getRemainingAttempts();

        // Assert
        Assertions.assertEquals(expectedRemainingAttempts, actualRemainingAttempts, "An invalid guess should not progress the session.");

    }

    /**
     * Tests {@link Coordinator#launch()} method to validate that the remaining attempts
     * feedback is provided upon submitting a mismatching guess.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario launch() is invoked
     * @expected the remaining attempts notice is rendered given an incorrect guess
     * @throws Exception if System Lambda injection, interception, or dependencies fail
     */
    @Test
    public void testLaunchProvidesRemainingAttemptsGivenMismatchingGuess() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String target = session.getTarget().getText();
        String incorrectGuess = (target.equals("OCEAN")) ? "BEACH" : "OCEAN"; // Ensures an incorrect guess.
        boolean expectedResult = true;

        // Act
        String output = SystemLambda.tapSystemOut(() -> {
            SystemLambda.withTextFromSystemIn(incorrectGuess).execute(() -> {
                Composer composer = new Composer(System.in);
                Coordinator coordinator = new Coordinator(session, composer);
                Assertions.assertThrows(NoSuchElementException.class, () -> {coordinator.launch();});
            });
        });
        boolean actualResult = output.contains("You have (5) attempts remaining.");

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The remaining attempts notice should be rendered upon failing to guess.");

    }

    /**
     * Tests {@link Coordinator#launch()} method to validate that the application's
     * lifecycle terminates by winning upon submitting a matching guess.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario launch() is invoked
     * @expected the win summary is rendered given a correct guess
     * @throws Exception if System Lambda injection, interception, or dependencies fail
     */
    @Test
    public void testLaunchTerminatesByWinningGivenMatchingGuess() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String correctGuess = session.getTarget().getText();
        boolean expectedResult = true;

        // Act
        String output = SystemLambda.tapSystemOut(() -> {
            SystemLambda.withTextFromSystemIn(correctGuess).execute(() -> {
                Composer composer = new Composer(System.in);
                Coordinator coordinator = new Coordinator(session, composer);
                coordinator.launch();
            });
        });
        boolean actualResult = output.contains("Splendid! You guessed correctly in (1) attempts.");

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The win summary should be rendered upon winning.");

    }

    /**
     * Tests {@link Coordinator#launch()} method to validate that the application's
     * lifecycle terminates by losing upon consecutively submitting mismatching guesses.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario launch() is invoked
     * @expected the loss summary is rendered given consecutive incorrect guesses
     * @throws Exception if System Lambda injection, interception, or dependencies fail
     */
    @Test
    public void testLaunchTerminatesByLosingGivenMismatchingGuesses() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String target = session.getTarget().getText();
        String guess = (target.equals("OCEAN")) ? "BEACH" : "OCEAN"; // Ensures an incorrect guess.
        boolean expectedResult = true;

        // Act
        String output = SystemLambda.tapSystemOut(() -> {
            SystemLambda.withTextFromSystemIn(guess, guess, guess, guess, guess, guess).execute(() -> {
                Composer composer = new Composer(System.in);
                Coordinator coordinator = new Coordinator(session, composer);
                coordinator.launch();
            });
        });
        boolean actualResult = output.contains("The correct word was \"" + target + "\". Better luck next time!");

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The loss summary should be rendered upon losing.");

    }

    /**
     * Tests {@link Coordinator#launch()} method to validate that the application's
     * lifecycle terminates once the underlying Session concludes.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario launch() is invoked
     * @expected the closing screen is rendered after the Session concludes
     * @throws Exception if System Lambda injection, interception, or dependencies fail
     */
    @Test
    public void testLaunchTerminatesOnceSessionConcludes() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String correctGuess = session.getTarget().getText();
        boolean expectedResult = true;

        // Act
        String output = SystemLambda.tapSystemOut(() -> {
            SystemLambda.withTextFromSystemIn(correctGuess).execute(() -> {
                Composer composer = new Composer(System.in);
                Coordinator coordinator = new Coordinator(session, composer);
                coordinator.launch();
            });
        });
        boolean actualResult = output.contains("Thank You for Playing Wordle!");

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "The closing screen should be rendered upon termination.");

    }

}