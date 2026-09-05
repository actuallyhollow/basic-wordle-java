package com.omaridris.wordle.model;

import com.omaridris.wordle.model.Matcher.State;

/**
 * Represents a playable Session with its complete core mechanics and internal state.
 * <p>
 * This class defines the Session's mutable state, which includes the chosen target,
 * submitted guess, letter match states, and remaining attempts; thus, it irreversibly
 * mutates this defined state to progress the Session within finite cycles, preventing
 * the underlying data from reaching a corrupt state.
 * <p>
 * It cannot be extended and can only be directly instantiated.
 * 
 * @author Omar Idris
 */
public final class Session {

    // ------*------ Attributes ------*------

    private static final int MAX_ATTEMPTS = 6;
    private final Dictionary dictionary;
    private final Word target;
    private Word guess;
    private State[] states;
    private int remainingAttempts;

    /**
     * Instantiates a new Session object.
     * <p>
     * By default, the remaining attempts are initialized to the maximum allowed,
     * and a randomly chosen Word from the dictionary is set as the target.
     * 
     * @param dictionary the dictionary for target selection and guess validation
     * @throws IllegalArgumentException if the passed dictionary is null
     */
    public Session(Dictionary dictionary) {

        if(dictionary == null) {
            throw new IllegalArgumentException("Dictionary cannot be null.");
        }
    
        this.dictionary = dictionary;
        this.target = this.dictionary.getRandom();
        this.guess = null;
        this.states = new State[this.target.getText().length()];
        this.remainingAttempts = Session.MAX_ATTEMPTS;

    }

    // ------*------ Getters / Accessors ------*------

    /**
     * Gets the value of the underlying target Word.
     * 
     * @return the randomly chosen target in the Session
     */
    public Word getTarget() {
        return this.target;
    }

    /**
     * Gets the value of the underlying guess Word.
     * 
     * @return the current guess in the Session, or null if the session just started
     */
    public Word getGuess() {
        return this.guess;
    }

    /**
     * Gets a safe, decoupled copy of the underlying match States.
     * 
     * @return a copy of the evaluated letter match states in the Session
     */
    public State[] getStates() {
        return this.states.clone();
    }

    /**
     * Gets the value of the underlying attempts counter.
     * 
     * @return the number of remaining attempts in the Session
     */
    public int getRemainingAttempts() {
        return this.remainingAttempts;
    }

    // ------*------ Derived Properties ------*------

    /**
     * Computes the total number of attempts already spent.
     * 
     * @return the number of used attempts in the Session
     */
    public int computeUsedAttempts() {
        return Session.MAX_ATTEMPTS - this.remainingAttempts;
    }

    /**
     * Determines whether the current guess exactly matches the target Word by iterating
     * over the letter match states.
     * 
     * @return true if all letter States are correct, false otherwise
     */
    public boolean isMatching() {

        for(State state : this.states) {
            if(state != State.CORRECT) {
                return false;
            }
        }

        return true;

    }

    /**
     * Determines whether the Session has concluded by evaluating both its win condition
     * and remaining attempts.
     *  
     * @return true if the target is guessed or all attempts are spent, false otherwise
     */
    public boolean isConcluded() {
        return (this.isMatching() == true) || (this.remainingAttempts <= 0);
    }

    // ------*------ Core Mechanics ------*------

    /**
     * Progresses the current state of the Session once by evaluating the provided guess.
     * <p>
     * Progressing the state is defined by three key mutations: overwriting the current
     * guess with the new guess, evaluating the new guess against the target,
     * decrementing the remaining attempts count.
     * <p>
     * Progression is strictly bound to the Session's conclusion status; thus, it can
     * only progress its state a limited number of times, preventing corrupt states.
     * 
     * @param guess the guess used to progress the Session's internal state
     * @throws IllegalArgumentException if the guess is null or evaluated as invalid
     * @throws IllegalStateException if the Session has already reached its conclusion
     */
    public void progressState(String guess) throws IllegalArgumentException, IllegalStateException {

        if(guess == null) {
            throw new IllegalArgumentException("Guess word cannot be null.");
        }

        if(this.isConcluded() == true) {
            throw new IllegalStateException("Cannot progress: Session is already concluded.");
        }

        this.guess = this.assignValidGuess(guess);
        this.states = Matcher.evaluate(this.target, this.guess);
        this.remainingAttempts--;

    }

    // ------*------ Helper Methods ------*------

    /**
     * Instantiates a Word from the passed guess and validates it against the dictionary.
     * 
     * @param guess the guess to validate
     * @return a valid Word representation of the guess
     * @throws IllegalArgumentException if the word does not exist in the dictionary
     */
    private Word assignValidGuess(String guess) throws IllegalArgumentException {

        Word word = new Word(guess);

        if(this.dictionary.isValid(word) == true) {
            return word;
        } else {
            throw new IllegalArgumentException("Word must exist in the dictionary.");
        }

    }

}