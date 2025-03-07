import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

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

    @Override
    public String printFile() {
        String done = isDone ? "Y" : "N";
        // Using the INPUT_FORMATTER to output the same format as the input.
        return done + "|E|" + description + "|" + from.format(INPUT_FORMATTER) + "|" + to.format(INPUT_FORMATTER);
    }

    @Override
    public String getDescription() {
        // Using OUTPUT_FORMATTER for a more human-readable format.
        return description + " (from: " + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription();
    }
}
