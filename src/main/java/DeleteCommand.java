public class DeleteCommand implements Command {
    private final int task;

    public DeleteCommand(int task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        tasks.delete(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getCommand() {
        return "We have just deleted task " + task;
    }


}
