import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * The deadline indicates the due date and time for the task.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructs a new {@code Deadline} with the specified description, and start time.
     *
     * @param description the description of the deadline
     * @param isDone      the completion status of the deadline
     * @throws MimiException if the provided date format is invalid
     */
    public Deadline(String description, boolean isDone, String deadlineStr) throws MimiException {
        super(description, isDone);
        try {
            this.deadline = LocalDateTime.parse(deadlineStr, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MimiException("Invalid date format. Please use dd-MM-yyyy or dd MM-yyyy HH:mm.");
        }
    }

    /**
     * Constructs a new {@code Deadline} with the specified description, and start time.
     *
     * @param description the description of the deadline
     * @param deadlineStr deadline date in a string
     * @throws MimiException if the provided date format is invalid
     */
    public Deadline(String description, String deadlineStr) throws MimiException {
        this(description, false, deadlineStr);
    }

    @Override
    public String printFile() {
        String done = isDone ? "Y" : "N";
        // Output using the input formatter
        return done + "|D|" + description + "|" + deadline.format(INPUT_FORMATTER);
    }

    @Override
    public String getDescription() {
        // Choose the formatter based on whether the time with hour or not
        String formattedDeadline;
        if (deadline.getHour() == 0 && deadline.getMinute() == 0) {
            formattedDeadline = deadline.format(DATE_ONLY_OUTPUT_FORMATTER);
        } else {
            formattedDeadline = deadline.format(DATE_TIME_OUTPUT_FORMATTER);
        }
        return this.description + " (by: " + formattedDeadline + ")";
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription();
    }
}

