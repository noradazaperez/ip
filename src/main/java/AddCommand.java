public class AddCommand implements Command {

    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getCommand() {
        return "I just added the task" + task.toString();
    }
}
