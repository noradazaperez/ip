public class UnmarkCommand implements Command {

    private final int task;

    public UnmarkCommand(int task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        tasks.unmark(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getCommand() {
        return "I just unmarked " + task;
    }
}
