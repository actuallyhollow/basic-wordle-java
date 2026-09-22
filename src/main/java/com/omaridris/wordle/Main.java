package com.omaridris.wordle;

import com.omaridris.wordle.controller.Coordinator;
import com.omaridris.wordle.model.Dictionary;
import com.omaridris.wordle.model.Session;
import com.omaridris.wordle.view.Composer;

/**
 * Bootstraps and launches the application's execution.
 * <p>
 * This class acts as the main entry point of the program; thus, it handles the
 * initialization of the primary MVC layers and transfers control of the
 * application's lifecycle to {@link Coordinator}.
 * <p>
 * It cannot be extended or instantiated and can only be statically accessed.
 * 
 * @author Omar Idris
 * @see Coordinator
 */
public final class Main {

    // ------*------ Attributes ------*------

    /**
     * Prevents instantiation of new Main objects.
     * 
     * @throws UnsupportedOperationException if instantiated internally or by Reflection.
     */
    private Main() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Main objects should not be instantiated.");
    }
    
    // ------*------ Application Entry ------*------

    /**
     * Serves as the primary entry point to execute the application's launch sequence.
     * <p>
     * Instantiates the core MVC dependencies and launches the application's lifecycle
     * through the underlying Coordinator. Unrecoverable exceptions are caught to print
     * a formatted crash screen and the program terminates.
     * 
     * @param args the command-line arguments passed to the program
     */
    public static void main(String[] args) {

        Composer composer = new Composer(System.in);

        try {
            Dictionary dictionary = new Dictionary();
            Session session = new Session(dictionary);
            Coordinator coordinator = new Coordinator(session, composer);
            coordinator.launch();
        } catch(Exception exception) {
            composer.printCrashScreen(exception.getMessage());
        } finally {
            composer.closeInput();   
        }

    }

}