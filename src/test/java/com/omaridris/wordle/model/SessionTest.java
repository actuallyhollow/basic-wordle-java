package com.omaridris.wordle.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.omaridris.wordle.model.Matcher.State;

/**
 * Conducts tests on the methods of {@link Session} using JUnit API.
 * <p>
 * Trivial methods (e.g. standard constructors, setters, or getters) are not tested;
 * thus, only methods with non-linear logic will be tested.
 * 
 * @author Omar Idris
 */
public class SessionTest {

    // ------*------ Testing Session(Dictionary) ------*------

    /**
     * Tests {@link Session#Session(Dictionary)} constructor to validate the proper
     * instantiation of Session objects with valid Dictionary dependencies.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario Session(Dictionary) is invoked with a valid dictionary
     * @expected a Session object is successfully instantiated without any exceptions
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testInstantiationWithValidDictionary() throws Exception {

        // Arrange
        Dictionary validDictionary = new Dictionary();

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> {new Session(validDictionary);}, "A valid dictionary should successfully instantiate a session.");

    }

    /**
     * Tests {@link Session#Session(Dictionary)} constructor to validate the proper
     * handling of null Dictionary dependencies.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario Session(Dictionary) is invoked with a null dictionary
     * @expected IllegalArgumentException is thrown
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testInstantiationWithNullDictionary() throws Exception {

        // Arrange
        Dictionary nullDictionary = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Session(nullDictionary);}, "An invalid null dictionary should result in IllegalArgumentException.");

    }

    // ------*------ Testing computeUsedAttempts() ------*------

    /**
     * Tests {@link Session#computeUsedAttempts()} method to validate the calculation of
     * used attempts on a brand new Session.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario computeUsedAttempts() is invoked on a new Session
     * @expected an integer value of 0 is returned
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testComputeUsedAttemptsOnNewSession() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        int expectedUsedAttempts = 0;

        // Act
        int actualUsedAttempts = session.computeUsedAttempts();

        // Assert
        Assertions.assertEquals(expectedUsedAttempts, actualUsedAttempts, "A new session should have 0 used attempts.");

    }

    /**
     * Tests {@link Session#computeUsedAttempts()} method to validate the calculation of
     * used attempts on a Session progressed once.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario computeUsedAttempts() is invoked on a progressed Session
     * @expected an integer value of 1 is returned
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testComputeUsedAttemptsOnProgressedSession() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String guess = "OCEAN";
        int expectedUsedAttempts = 1;
        session.progressState(guess);
        
        // Act
        int actualUsedAttempts = session.computeUsedAttempts();

        // Assert
        Assertions.assertEquals(expectedUsedAttempts, actualUsedAttempts, "A progressed session should have 1 used attempt after a single progression.");

    }

    // ------*------ Testing isMatching() ------*------

    /**
     * Tests {@link Session#isMatching()} method to validate the matching status of a
     * brand new Session with no submitted guess.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario isMatching() is invoked on a new Session
     * @expected a false boolean is returned
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testIsMatchingOnNewSession() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        boolean expectedResult = false;

        // Act
        boolean actualResult = session.isMatching();

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "A new session should not have the guess matching the target.");

    }

    /**
     * Tests {@link Session#isMatching()} method to validate the matching status of a
     * Session with an incorrect submitted guess.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario isMatching() is invoked on a Session with a mismatching guess
     * @expected a false boolean is returned
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testIsMatchingOnSessionWithIncorrectGuess() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String target = session.getTarget().getText();
        String incorrectGuess = (target.equals("OCEAN")) ? "BEACH" : "OCEAN"; // Ensures an incorrect guess.
        boolean expectedResult = false;
        session.progressState(incorrectGuess);

        // Act
        boolean actualResult = session.isMatching();

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "A session with an incorrect guess should not match the target.");

    }

    /**
     * Tests {@link Session#isMatching()} method to validate the matching status of a
     * Session with a correct submitted guess.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario isMatching() is invoked on a Session with a fully matching guess
     * @expected a true boolean is returned
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testIsMatchingOnSessionWithCorrectGuess() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String correctGuess = session.getTarget().getText();
        boolean expectedResult = true;
        session.progressState(correctGuess);

        // Act
        boolean actualResult = session.isMatching();

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "A session with a correct guess should match the target.");

    }

    // ------*------ Testing isConcluded() ------*------

    /**
     * Tests {@link Session#isConcluded()} method to validate the conclusion status of a
     * running Session is not prematurely set.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario isConcluded() is invoked on a running Session
     * @expected a false boolean is returned
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testIsConcludedOnRunningSession() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        boolean expectedResult = false;

        // Act
        boolean actualResult = session.isConcluded();

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "A running session should not be concluded.");

    }

    /**
     * Tests {@link Session#isConcluded()} method to validate the conclusion status of a
     * Session terminated by winning is correctly set.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario isConcluded() is invoked on a Session whose target was guessed
     * @expected a true boolean is returned
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testIsConcludedOnWonSession() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String correctGuess = session.getTarget().getText();
        boolean expectedResult = true;
        session.progressState(correctGuess);

        // Act
        boolean actualResult = session.isConcluded();

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "A won session should be concluded after guessing correctly.");

    }

    /**
     * Tests {@link Session#isConcluded()} method to validate the conclusion status of a
     * Session terminated by losing is correctly set.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario isConcluded() is invoked on a Session whose attempts were exhausted
     * @expected a true boolean is returned
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testIsConcludedOnLostSession() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String target = session.getTarget().getText();
        String incorrectGuess = (target.equals("OCEAN")) ? "BEACH" : "OCEAN"; // Ensures an incorrect guess.
        boolean expectedResult = true;

        while(session.getRemainingAttempts() > 0) {
            session.progressState(incorrectGuess);
        }

        // Act
        boolean actualResult = session.isConcluded();

        // Assert
        Assertions.assertEquals(expectedResult, actualResult, "A lost session should be concluded after failing to guess correctly.");

    }

    // ------*------ Testing progressState(String) ------*------

    /**
     * Tests {@link Session#progressState(String)} method to validate the progression of
     * a Session's internal state when submitting a guess matching the target.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario progressState(String) is invoked with a matching guess
     * @expected the guess, match states, and remaining attempts are updated accordingly
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testProgressStateWithMatchingGuess() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String correctGuess = session.getTarget().getText();
        String expectedGuess = correctGuess;
        State[] expectedStates = {State.CORRECT, State.CORRECT, State.CORRECT, State.CORRECT, State.CORRECT};
        int expectedRemainingAttempts = 5;

        // Act
        session.progressState(correctGuess);
        String actualGuess = session.getGuess().getText();
        State[] actualStates = session.getStates();
        int actualRemainingAttempts = session.getRemainingAttempts();

        // Assert
        Assertions.assertEquals(expectedGuess, actualGuess, "A session should have the current guess updated to the submitted guess.");
        Assertions.assertArrayEquals(expectedStates, actualStates, "A session with a matching guess should have all states as correct.");
        Assertions.assertEquals(expectedRemainingAttempts, actualRemainingAttempts, "A session should have 5 remaining attempts after a single progression.");

    }

    /**
     * Tests {@link Session#progressState(String)} method to validate the progression of
     * a Session's internal state when submitting a guess not matching the target.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario progressState(String) is invoked with a mismatching guess
     * @expected the guess, match states, and remaining attempts are updated accordingly
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testProgressStateWithMismatchingGuess() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String target = session.getTarget().getText();
        String incorrectGuess = (target.equals("OCEAN")) ? "BEACH" : "OCEAN"; // Ensures an incorrect guess.
        State[] matchingStates = {State.CORRECT, State.CORRECT, State.CORRECT, State.CORRECT, State.CORRECT};
        String expectedGuess = incorrectGuess;
        boolean expectedStatesEquality = false;
        int expectedRemainingAttempts = 5;

        // Act
        session.progressState(incorrectGuess);
        String actualGuess = session.getGuess().getText();
        boolean actualStatesEquality = java.util.Arrays.equals(matchingStates, session.getStates());
        int actualRemainingAttempts = session.getRemainingAttempts();

        // Assert
        Assertions.assertEquals(expectedGuess, actualGuess, "A session should have the current guess updated to the submitted guess.");
        Assertions.assertEquals(expectedStatesEquality, actualStatesEquality, "A session with a mismatching guess should not have all states as correct.");
        Assertions.assertEquals(expectedRemainingAttempts, actualRemainingAttempts, "A session should have 5 remaining attempts after a single progression.");

    }

    /**
     * Tests {@link Session#progressState(String)} method to validate the proper handling
     * of null guesses.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario progressState(String) is invoked with a null guess
     * @expected IllegalArgumentException is thrown
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testProgressStateWithNullGuess() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String nullGuess = null;

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {session.progressState(nullGuess);}, "A null guess should result in IllegalArgumentException.");

    }

    /**
     * Tests {@link Session#progressState(String)} method to validate the proper handling
     * of guesses not present in the Dictionary dependency.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario progressState(String) is invoked with a dictionary-absent guess
     * @expected IllegalArgumentException is thrown
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testProgressStateWithInvalidGuess() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String invalidGuess = "zzzzz";

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {session.progressState(invalidGuess);}, "A dictionary-absent guess should result in IllegalArgumentException.");

    }

    /**
     * Tests {@link Session#progressState(String)} method to validate the proper handling
     * of Sessions already concluded by winning.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario progressState(String) is invoked on a Session whose target was guessed
     * @expected IllegalStateException is thrown
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testProgressStateOnWonSession() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String correctGuess = session.getTarget().getText();
        session.progressState(correctGuess);

        // Act & Assert
        Assertions.assertThrows(IllegalStateException.class, () -> {session.progressState(correctGuess);}, "Progressing a won session should result in IllegalStateException.");

    }

    /**
     * Tests {@link Session#progressState(String)} method to validate the proper handling
     * of Sessions already concluded by losing.
     * <p>
     * Tests are conducted using the AAA pattern (Arrange, Act, Assert).
     * 
     * @scenario progressState(String) is invoked on a Session whose attempts were used
     * @expected IllegalStateException is thrown
     * @throws Exception if any dependency initialization fails
     */
    @Test
    public void testProgressStateOnLostSession() throws Exception {

        // Arrange
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary);
        String target = session.getTarget().getText();
        String incorrectGuess = (target.equals("OCEAN")) ? "BEACH" : "OCEAN"; // Ensures an incorrect guess.

        while(session.getRemainingAttempts() > 0) {
            session.progressState(incorrectGuess);
        }

        // Act & Assert
        Assertions.assertThrows(IllegalStateException.class, () -> {session.progressState(incorrectGuess);}, "Progressing a lost session should result in IllegalStateException.");

    }

}