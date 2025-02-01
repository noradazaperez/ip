public class Event {
    protected String description;
    protected boolean isDone;
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        this.description = description;
        this.isDone = false;
        this.from = from;
        this.to = to;

    }

    public String getStatusIcon() {
        return (isDone? "X":" ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getDescription() {
        return description + "(from: " + from + " to: " + to + ")";
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription();
    }
}
