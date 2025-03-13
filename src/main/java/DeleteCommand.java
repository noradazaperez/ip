public class DeleteCommand implements Command {

    private int task;

    public DeleteCommand(int task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        tasks.delete(task);
        ui.showCommand("I have deleted the task " + tasks.getTask(task));
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
