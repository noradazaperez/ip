/**
 * Represents the mark task command that can be executed in the application
 */
public class MarkCommand implements Command {
    private final int task;

    /**
     * Constructs a new {@code MarkCommand} with the specified task index
     *
     * @param task the index that needs to be marked
     */
    public MarkCommand(int task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        tasks.mark(task);
        ui.showCommand("I just marked the task " + tasks.getTask(task));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
