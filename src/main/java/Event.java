import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class Event extends ToDo {
    protected LocalDateTime from;
    protected LocalDateTime to;

    // Create a formatter that accepts either "yyyy-MM-dd" or "yyyy-MM-dd HH:mm"
    private static final DateTimeFormatter INPUT_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("dd-MM-yyyy")
            .optionalStart()
            .appendLiteral(' ')
            .appendPattern("HH:mm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    // Output formatter when time is provided
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    // Output formatter when only date is provided (or time is default midnight)
    private static final DateTimeFormatter OUTPUT_DATE_ONLY_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Constructs a new {@code Event} with the specified description, start time, and end time.
     * Assumes the event is not yet completed.
     *
     * @param description the description of the event
     * @param isDone      the completion status of the event
     * @param fromStr     the start time of the event as a string
     * @param toStr       the end time of the event as a string
     * @throws MimiException if the provided date format is invalid
     */
    public Event(String description, boolean isDone, String fromStr, String toStr) throws MimiException {
        super(description, isDone);
        try {
            this.from = LocalDateTime.parse(fromStr, INPUT_FORMATTER);
            this.to = LocalDateTime.parse(toStr, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MimiException("Invalid date format. Please use dd MM yyyy or dd MM yyyy HH:mm.");
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
        // Use the input formatter to preserve the original input format in the file.
        return done + "|E|" + description + "|" + from.format(INPUT_FORMATTER) + "|" + to.format(INPUT_FORMATTER);
    }

    /**
     * Returns a string representation of the event's description including its start and end times.
     *
     * @return a string in the format "[description] (from: [from] to: [to])"
     */
    @Override
    public String getDescription() {
        // Check if the time is default (midnight) to decide on the formatter.
        String formattedFrom = (from.getHour() == 0 && from.getMinute() == 0)
                ? from.format(OUTPUT_DATE_ONLY_FORMATTER)
                : from.format(OUTPUT_DATE_TIME_FORMATTER);

        String formattedTo = (to.getHour() == 0 && to.getMinute() == 0)
                ? to.format(OUTPUT_DATE_ONLY_FORMATTER)
                : to.format(OUTPUT_DATE_TIME_FORMATTER);

        return description + " (from: " + formattedFrom + " to: " + formattedTo + ")";
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
