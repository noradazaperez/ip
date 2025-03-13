import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Represents a task with a deadline.
 * <p>
 * This class extends {@code Task} by adding a deadline, which is stored as a {@code LocalDateTime}.
 * The deadline indicates the due date and time for the task.
 * </p>
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    // Formatter that accepts either "yyyy-MM-dd" or "yyyy-MM-dd HH:mm"
    private static final DateTimeFormatter INPUT_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd")
            .optionalStart()
            .appendLiteral(' ')
            .appendPattern("HH:mm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    // Formatter for output with date and time
    private static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    // Formatter for output with date only
    private static final DateTimeFormatter DATE_ONLY_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, boolean isDone, String deadlineStr) throws MimiException {
        super(description, isDone);
        try {
            this.deadline = LocalDateTime.parse(deadlineStr, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MimiException("Invalid date format. Please use yyyy-MM-dd or yyyy-MM-dd HH:mm.");
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

