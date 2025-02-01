public class Deadline {
    protected String description;
    protected boolean isDone;
    protected String deadline;

    public Deadline(String description, String deadline) {
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;

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
        return this.description + "(by: " + this.deadline + ")";
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription();
    }
}
