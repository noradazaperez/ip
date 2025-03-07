import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
