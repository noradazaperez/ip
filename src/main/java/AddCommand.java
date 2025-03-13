/**
 * Represents the add task command that can be executed within the application.
 */
public class AddCommand implements Command {

    Task task;

    /**
     * Constructs a new {@code AddCommand} with the specified task object
     *
     * @param task the task object to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        tasks.add(task);
        ui.showCommand("I just added the task " + tasks.getTask(tasks.getSize()));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
