public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

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
