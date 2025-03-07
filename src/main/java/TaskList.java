import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * <p>
 * This class maintains a collection of {@code Task} objects and provides methods
 * for adding, deleting, marking, and unmarking tasks. The tasks are stored internally
 * using an {@code ArrayList}.
 * </p>
 */
public class TaskList {

    /**
     * The list of tasks.
     */
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a {@code TaskList} with the provided list of tasks.
     *
     * @param tasks an {@code ArrayList} containing {@code Task} objects
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Returns the list of tasks.
     *
     * @return the {@code ArrayList} of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Deletes the task at the specified 1-based index.
     * <p>
     * The index provided should be in a 1-based numbering system, meaning that
     * the first task has an index of 1.
     * </p>
     *
     * @param task the 1-based index of the task to be deleted
     * @return the {@code Task} object that was removed
     * @throws MimiException if the index is out of bounds
     */
    public Task delete(int task) throws MimiException {
        if (task > tasks.size()) {
            throw new MimiException("Sorry! The task doesn't exist. Index out of bounds: " + task);
        }
        return tasks.remove(task - 1);

    }

    /**
     * Adds a new task to the list.
     *
     * @param task the {@code Task} to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks the task at the specified 1-based index as done.
     * <p>
     * The index provided should be in a 1-based numbering system.
     * </p>
     *
     * @param task the 1-based index of the task to mark as done
     * @throws MimiException if the index is out of bounds
     */
    public void mark(int task) throws MimiException {
        if (task > tasks.size()) {
            throw new MimiException("Sorry! The task doesn't exist. Index out of bounds: " + task);
        }
        // Assuming the index is 1-based, you might consider adjusting it to 0-based.
        tasks.get(task).markAsDone();
    }

    /**
     * Marks the task at the specified 1-based index as not done.
     * <p>
     * The index provided should be in a 1-based numbering system.
     * </p>
     *
     * @param task the 1-based index of the task to mark as not done
     * @throws MimiException if the index is out of bounds
     */
    public void unmark(int task) throws MimiException {
        if (task > tasks.size()) {
            throw new MimiException("Sorry! The task doesn't exist. Index out of bounds: " + task);
        }
        // Assuming the index is 1-based, you might consider adjusting it to 0-based.
        tasks.get(task).markAsNotDone();
    }

    public ArrayList<Task> find(String keyword) throws MimiException {
        ArrayList<Task> taskKey = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                taskKey.add(task);
            }
        }
        return taskKey;
    }




}
