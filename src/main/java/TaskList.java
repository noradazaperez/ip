import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task delete(int task) throws MimiException {
        if (task > tasks.size()) {
            throw new MimiException("Sorry! The task doesn't exist. Index out of bounds: " + task);
        }
        return tasks.remove(task-1);

    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void mark(int task) throws MimiException {
        if (task > tasks.size()) {
            throw new MimiException("Sorry! The task doesn't exist. Index out of bounds: " + task);
        }
        tasks.get(task).markAsDone();
    }

    public void unmark(int task) throws MimiException {
        if (task > tasks.size()) {
            throw new MimiException("Sorry! The task doesn't exist. Index out of bounds: " + task);
        }
        tasks.get(task).markAsNotDone();
    }




}
