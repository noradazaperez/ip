/**
 * Represents an event task with a start and end time.
 * <p>
 * An event task is a type of {@code Task} that includes a duration, specified by a start time
 * ("from") and an end time ("to"). This class provides methods to format the event details for
 * file storage as well as for display.
 * </p>
 */
public class Event extends Task {

    /**
     * The start time of the event.
     */
    protected String from;

    /**
     * The end time of the event.
     */
    protected String to;

    /**
     * Constructs a new {@code Event} with the specified description, completion status, start time, and end time.
     *
     * @param description the description of the event
     * @param isDone      indicates whether the event has been completed
     * @param from        the start time of the event as a string
     * @param to          the end time of the event as a string
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new {@code Event} with the specified description, start time, and end time.
     * Assumes the event is not yet completed.
     *
     * @param description the description of the event
     * @param from        the start time of the event as a string
     * @param to          the end time of the event as a string
     */
    public Event(String description, String from, String to) {
        this(description, false, from, to);
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
        return done + "|E|" + description + "|" + from + "|" + to;
    }

    /**
     * Returns a string representation of the event's description including its start and end times.
     *
     * @return a string in the format "[description](from: [from] to: [to])"
     */
    @Override
    public String getDescription() {
        return description + " (from: " + from + " to: " + to + ")";
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
