import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * <p>
 * This class maintains a collection of {@code Task} objects and provides methods
 * for adding, deleting, marking, and unmarking tasks.
 * </p>
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a {@code TaskList} with the provided list of tasks.
     *
     * @param tasks an {@code ArrayList} containing {@code Task} objects
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

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
     * Deletes the task at the specified index.
     *
     * @param task the index of the task to be deleted
     * @return the {@code Task} object that was removed
     * @throws MimiException if the index is out of bounds
     */
    public Task delete(int task) throws MimiException {
        if (task > tasks.size() || task < 0) {
            throw new MimiException("Lo siento! The task doesn't exist. Index out of bounds: " + task);
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
     * Marks the task at the specified index as done
     *
     * @param task the index of the task to mark as done
     * @throws MimiException if the index is out of bounds
     */
    public void mark(int task) throws MimiException {
        if (task > tasks.size() || task < 0) {
            throw new MimiException("Lo siento! The task doesn't exist. Index out of bounds: " + task);
        }
        tasks.get(task-1).markAsDone();
    }

    /**
     * Marks the task at the specified 1-based index as not done.
     *
     * @param task the index of the task to mark as not done
     * @throws MimiException if the index is out of bounds
     */
    public void unmark(int task) throws MimiException {
        if (task > tasks.size() || task < 0) {
            throw new MimiException("Lo siento! The task doesn't exist. Index out of bounds: " + task);
        }
        tasks.get(task-1).markAsNotDone();
    }

    /**
     * Finds a task with the specified keyword
     *
     * @param keyword the keyword that needs to be in the description of the task
     * @return the list with the tasks
     * @throws MimiException
     */
    public ArrayList<Task> find(String keyword) throws MimiException {
        ArrayList<Task> taskKey = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                taskKey.add(task);
            }
        }

        if (taskKey.isEmpty()) {
            throw new MimiException("Lo siento! There is not any such task description");
        }
        return taskKey;
    }




}
