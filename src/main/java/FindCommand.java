public class FindCommand implements Command {

    private String keyword;

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
