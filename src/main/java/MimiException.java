/**
 * Exception to be thrown in the application that indicates an error
 *
 */
public class MimiException extends Exception {

    /**
     * Constructs a {@code MimiException} that has a message
     *
     * @param message that explains the error
     */
    public MimiException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Warning!! There has been a MIMI-ERROR: " + super.getMessage();
    }
}
