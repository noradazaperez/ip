public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;

    }
    public Event(String description, String from, String to) {
        this(description, false, from, to);
    }

    @Override
    public String printFile(){
        String done = isDone? "Y":"N";
        return done + "|E|" + description + "|" + from + "|" + to;
    }

    @Override
    public String getDescription() {
        return description + "(from: " + from + " to: " + to + ")";
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription();
    }
}
