public class UnmarkCommand implements Command {

    private final int task;

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
