import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a new {@code Event} with the specified description, start time, and end time.
     * Assumes the event is not yet completed.
     *
     * @param description the description of the event
     * @param from        the start time of the event as a string
     * @param to          the end time of the event as a string
     */
    public Event(String description, boolean isDone, String fromStr, String toStr) throws MimiException {
        super(description, isDone);
        try {
            this.from = LocalDate.parse(fromStr, INPUT_FORMATTER);
            this.to = LocalDate.parse(toStr, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MimiException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    public Event(String description, String fromStr, String toStr) throws MimiException {
        this(description, false, fromStr, toStr);
    }

    /**
     * Returns a string representation of this event formatted for file storage.
     * <p>
     * The format is: [done status]|E|[description]|[from]|[to],
     * where {@code Y} indicates that the event is completed and {@code N} otherwise.
     * </p>
     *
     * @return a formatted string suitable for saving in a file
     */
    @Override
    public String printFile() {
        String done = isDone ? "Y" : "N";
        // Using the INPUT_FORMATTER to output the same format as the input.
        return done + "|E|" + description + "|" + from.format(INPUT_FORMATTER) + "|" + to.format(INPUT_FORMATTER);
    }

    /**
     * Returns a string representation of the event's description including its start and end times.
     *
     * @return a string in the format "[description](from: [from] to: [to])"
     */
    @Override
    public String getDescription() {
        // Using OUTPUT_FORMATTER for a more human-readable format.
        return description + " (from: " + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }

    /**
     * Returns a string representation of the event for display purposes.
     * <p>
     * The format is: [E][statusIcon] [description] (with the description including time details).
     * </p>
     *
     * @return a formatted string representing this event
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription();
    }
}
