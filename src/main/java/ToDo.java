/**
 *  Represents a /todo task that inherits from Task
 */
public class ToDo extends Task {
    /**
     * Constructs a new {@code ToDo} with the specified description and completion status.
     *
     * @param description the description of the task
     * @param isDone      {@code true} if the task is completed, {@code false} otherwise
     */
    public ToDo(String description, boolean isDone) throws MimiException {
        super(description, isDone);
    }

    /**
     * Constructs a new {@code Todo} with the specified description
     *
     * @param description the description of the task
     */
    public ToDo(String description) throws MimiException {
        super(description);
    }

    @Override
    public String printFile() {
        String done = isDone ? "Y" : "N";
        return done + "|T|" + getDescription();
    }


    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription();
    }
}
