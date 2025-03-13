/**
 * Represents the show list command that can be executed in the application
 */
public class ShowListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
