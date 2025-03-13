/**
 * Represents the delete task command that can be executed within the application.
 */
public class DeleteCommand implements Command {

    private int task;

    /**
     * Constructs a new {@code DeleteCommand} with the specified task object
     *
     * @param task the task object to be deleted
     */
    public DeleteCommand(int task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        Task deletedTask = tasks.delete(task);
        ui.showCommand("I have deleted the task " + deletedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
