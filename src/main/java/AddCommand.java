public class AddCommand implements Command {

    Task task;

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
