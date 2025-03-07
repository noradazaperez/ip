/**
 * Represents a generic task with a description and a completion status.
 * <p>
 * This class provides basic functionality for marking a task as done or not done,
 * retrieving its status, and generating string representations for both display and file storage.
 * </p>
 */
public class Task {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * Indicates whether the task has been completed.
     */
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description and completion status.
     *
     * @param description the description of the task
     * @param isDone      {@code true} if the task is completed, {@code false} otherwise
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructs a new {@code Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Returns the status icon of the task.
     * <p>
     * An "X" indicates the task is done, while a space indicates it is not done.
     * </p>
     *
     * @return a string representing the task's status icon
     */
    public String getStatusIcon() {
        return (isDone? "X":" ");
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task formatted for file storage.
     * <p>
     * The format is: [done status]|T|[description]
     * where "Y" indicates the task is done and "N" indicates it is not done.
     * </p>
     *
     * @return a formatted string suitable for saving in a file
     */
    public String printFile() {
        String done = isDone ? "Y" : "N";
        return done + "|T|" + getDescription();
    }

    /**
     * Returns a string representation of the task for display purposes.
     * <p>
     * The format is: [T][status icon] [description]
     * </p>
     *
     * @return a formatted string representing the task
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription();
    }
}
