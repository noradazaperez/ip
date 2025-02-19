public class Deadline extends Task{
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;

    }

    @Override
    public String printFile() {
        String done = isDone?"Y":"N";
        return done + "|D|" + description + "|" + deadline;
    }
    @Override
    public String getDescription() {
        return this.description + "(by: " + this.deadline + ")";
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription();
    }
}
