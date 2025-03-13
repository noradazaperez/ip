import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with an event.
 * The event indicates the start time (from) and the end time (to) of the event
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new {@code Event} with the specified description, start time, and end time.
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
            if (from.isAfter(to)) {
                throw new MimiException("Lo siento! Invalid date");
            }
        } catch (DateTimeParseException e) {
            throw new MimiException("Invalid date format. Please use dd MM yyyy or dd MM yyyy HH:mm.");
        }
    }

    /**
     * Constructs a new {@code Event} with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param fromStr     the start time of the event as a string
     * @param toStr       the end time of the event as a string
     * @throws MimiException if the provided date format is invalid
     */
    public Event(String description, String fromStr, String toStr) throws MimiException {
        this(description, false, fromStr, toStr);
    }


    @Override
    public String printFile() {
        String done = isDone ? "Y" : "N";
        // Use the input formatter to preserve the original input format in the file.
        return done + "|E|" + description + "|" + from.format(INPUT_FORMATTER) + "|" + to.format(INPUT_FORMATTER);
    }


    @Override
    public String getDescription() {
        // Check if the time is default (midnight) to decide on the formatter.
        String formattedFrom = (from.getHour() == 0 && from.getMinute() == 0) ? from.format(DATE_ONLY_OUTPUT_FORMATTER) : from.format(DATE_TIME_OUTPUT_FORMATTER);

        String formattedTo = (to.getHour() == 0 && to.getMinute() == 0) ? to.format(DATE_ONLY_OUTPUT_FORMATTER) : to.format(DATE_TIME_OUTPUT_FORMATTER);

        return description + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }


    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription();
    }
}
