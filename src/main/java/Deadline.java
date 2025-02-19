public class Deadline extends Task{
    protected String deadline;

    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;

    }
    public Deadline(String description, String deadline){
        this(description, false, deadline);
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
