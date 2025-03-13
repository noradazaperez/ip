public class MarkCommand implements Command {
    private final int task;

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
