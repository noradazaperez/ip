public class MarkCommand implements Command {
    private int task;
    public MarkCommand(int task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        tasks.mark(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getCommand() {
        return "I just marked the task " + task;
    }
}
