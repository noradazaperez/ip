import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * <p>
 * This class extends {@code Task} by adding a deadline, which is stored as a {@code String}.
 * The deadline typically indicates the due date for the task.
 * </p>
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, boolean isDone, String deadlineStr) throws MimiException {
        super(description, isDone);
        try {
            this.deadline = LocalDate.parse(deadlineStr, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MimiException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    public Deadline(String description, String deadlineStr) throws MimiException {
        this(description, false, deadlineStr);
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
        // Output using the input formatter so that the file retains the yyyy-MM-dd format
        return done + "|D|" + description + "|" + deadline.format(INPUT_FORMATTER);
    }

    @Override
    public String getDescription() {
        // Output using the human-readable format
        return this.description + " (by: " + deadline.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription();
    }
}
