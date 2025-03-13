/**
 * Public task that inherits from Task.
 *  <p>
 *  This class provides basic functionality for a ToDo task.
 *  </p>
 *
 */
public class ToDo extends Task {
    public ToDo(String description, boolean isDone) throws MimiException {
        super(description, isDone);
    }

    public ToDo(String description) throws MimiException {
        super(description);
    }
}
