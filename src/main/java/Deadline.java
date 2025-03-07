/**
 * Represents a task with a deadline.
 * <p>
 * This class extends {@code Task} by adding a deadline, which is stored as a {@code String}.
 * The deadline typically indicates the due date for the task.
 * </p>
 */
public class Deadline extends Task {

    protected String deadline;

    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline) {
        this(description, false, deadline);
    }

    /**
     * Returns a string representation of this task suitable for file storage.
     * <p>
     * The format is: [done status]|D|[description]|[deadline]
     * where {@code Y} indicates that the task is completed, and {@code N} otherwise.
     * </p>
     *
     * @return a formatted string for file storage
     */
    @Override
    public String printFile() {
        String done = isDone ? "Y" : "N";
        return done + "|D|" + description + "|" + deadline;
    }

    @Override
    public String getDescription() {
        return this.description + "(by: " + this.deadline + ")";
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription();
    }
}
