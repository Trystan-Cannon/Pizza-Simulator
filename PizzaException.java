/**
 * This class represents a run-time exception which occurs when a runtime
 * error occurs when working with a pizza.
 * 
 * These exceptions are thrown in place of <code>RuntimExceptions</code>
 * throughout the PizzaSimulator program, as specified by the assignment spec.
 * 
 * @author Trystan Cannon
 */
public class PizzaException extends RuntimeException {
    
    /**
     * Constructs an exception whose message is the default:
     * <code>"An exception has occurred when working with a pizza!"</code>.
     */
    public PizzaException() {
        super("An exception has occurred when working with a pizza!");
    }
    
    /**
     * Constructs an exception with the given message.
     * @param message The message attached to the constructed exception.
     */
    public PizzaException(String message) {
        super(message);
    }
    
}