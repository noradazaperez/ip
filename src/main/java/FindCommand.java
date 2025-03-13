/**
 * Represents the find command that can be executed in the application
 */
public class FindCommand implements Command {
    private String keyword;

    /**
     * Constructs a new {@code FindCommand} with the specified keyword
     *
     * @param keyword the keyword that needs to be found
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        ui.showTasks(tasks.find(keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
