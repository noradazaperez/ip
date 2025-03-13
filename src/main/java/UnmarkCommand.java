/**
 * Represents the unmark task command that can be executed in the application
 */
public class UnmarkCommand implements Command {

    private final int task;

    /**
     * Constructs a new {@code UnmarkCommand} with the specified task index
     *
     * @param task the index that needs to be marked
     */
    public UnmarkCommand(int task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        tasks.unmark(task);
        ui.showCommand("I have just unmarked " + tasks.getTask(task));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
