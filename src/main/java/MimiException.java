public class MimiException extends Exception {

    public MimiException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Warning!! There has been a MIMI-ERROR: " + super.getMessage();
    }
}
