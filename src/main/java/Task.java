import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * Represents an abstract task with a description and a completion status.
 * <p>
 * This class provides basic functionality for marking a task as done or not done,
 * retrieving its status, and generating string representations for both display and file storage.
 * </p>
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    // Formatter that accepts either "yyyy-MM-dd" or "yyyy-MM-dd HH:mm"
    protected static final DateTimeFormatter INPUT_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("dd-MM-yyyy")
            .optionalStart()
            .appendLiteral(' ')
            .appendPattern("HH:mm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    // Formatter for output with date and time
    protected static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    // Formatter for output with date only
    protected static final DateTimeFormatter DATE_ONLY_OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM yyyy");


    /**
     * Constructs a new {@code Task} with the specified description and completion status.
     *
     * @param description the description of the task
     * @param isDone      {@code true} if the task is completed, {@code false} otherwise
     */
    public Task(String description, boolean isDone) throws MimiException{
        if (description.isEmpty()) {
            throw new MimiException("Lo siento! You ned to write a description.");
        }
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructs a new {@code Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) throws MimiException {
        this(description, false);
    }

    /**
     * Returns the status icon of the task.
     *
     * @return a string representing the task's status icon
     */
    public String getStatusIcon() {
        return (isDone? "X":" ");
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task formatted for file storage.
     * <p>
     * The format is: [done status]|T|[description]
     * </p>
     *
     * @return a formatted string suitable for saving in a file
     */
    public abstract String printFile();

}
