public class IncorrectSyntax extends MimiException {

    public IncorrectSyntax(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.toString() + "\nIncorrect Syntax: " + getMessage();
    }
}
