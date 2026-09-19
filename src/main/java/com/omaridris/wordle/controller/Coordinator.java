package com.omaridris.wordle.controller;

import com.omaridris.wordle.model.Session;
import com.omaridris.wordle.view.Composer;

/**
 * Coordinates between the Model and View layers to manage the application's lifecycle.
 * <p>
 * This class acts as the main Controller layer in the MVC architecture; thus, it strictly
 * connects the {@link Session} domain logic with the {@link Composer} visual structures
 * without executing business rules or handling terminal streams itself.
 * <p>
 * It cannot be extended and can only be directly instantiated.
 * 
 * @author Omar Idris
 * @see Session
 * @see Composer
 */
public final class Coordinator {

    // ------*------ Attributes ------*------

    private final Session session;
    private final Composer composer;

    /**
     * Instantiates a new Coordinator object.
     * 
     * @param session the Session maintaining the internal state and logic
     * @param composer the Composer managing the console input and output
     * @throws IllegalArgumentException if either the Session or Composer is null
     */
    public Coordinator(Session session, Composer composer) {

        if(session == null) {
            throw new IllegalArgumentException("Session cannot be null.");
        }

        if(composer == null) {
            throw new IllegalArgumentException("Composer cannot be null.");
        }

        this.session = session;
        this.composer = composer;

    }

    // ------*------ Application Lifecycle ------*------

    /**
     * Launches the application's lifecycle and executes the turn-based progression.
     * <p>
     * Acting as the main entry point for the Controller, it defines the application's
     * lifecycle as three sequential phases: rendering the initial instruction screen,
     * iterating through user turns until the Session concludes, and triggering the
     * closing summaries.
     * 
     * @throws IllegalStateException if the Session has already reached its conclusion
     */
    public void launch() throws IllegalStateException {

        this.composer.printInstructionScreen();

        while(this.session.isConcluded() == false) {
            this.processTurn();
        }

        this.concludeOutcome();
        this.composer.printClosingScreen();
        this.composer.closeInput();

    }

    // ------*------ Helper Methods ------*------

    /**
     * Processes a single interactive turn within the application's lifecycle.
     * <p>
     * A single turn is defined as three sequential steps: prompting the user for a
     * guess, capturing a Word-valid input and progressing the Session, and rendering
     * the turn feedback to the user.
     * 
     * @throws IllegalStateException if the Session has already reached its conclusion
     */
    private void processTurn() throws IllegalStateException {

        this.composer.printGuessNotice();
        this.processInput();
        this.composer.printFeedbackNotice(this.session.getGuess(), this.session.getStates());

        if(this.session.isMatching() == false) {
            this.composer.printAttemptsNotice(this.session.getRemainingAttempts());
        }

    }

    /**
     * Processes continuous user input until a Word-valid guess is submitted.
     * <p>
     * Iteratively attempts to progress the Session using the given input, recovering
     * from domain rejections triggered during this progression by rendering respective
     * error notices before requesting new input.
     * 
     * @throws IllegalStateException if the Session has already reached its conclusion
     */
    private void processInput() throws IllegalStateException {

        while(true) {

            String guess = this.composer.promptGuess();

            try {
                this.session.progressState(guess);
                break;
            } catch(IllegalArgumentException illegalArgumentException) {
                this.composer.printErrorNotice(illegalArgumentException.getMessage());
                continue;
            }

        }

    }

    /**
     * Concludes the application's lifecycle by rendering the final win or loss outcome.
     * <p>
     * Evaluates the Session's final matching status to render either a win summary
     * detailing the attempts used, or a loss summary revealing the hidden target Word.
     */
    private void concludeOutcome() {

        if(this.session.isMatching() == true) {
            this.composer.printWinSummary(this.session.computeUsedAttempts());
        } else {
            this.composer.printLossSummary(this.session.getTarget());
        }

    }

}