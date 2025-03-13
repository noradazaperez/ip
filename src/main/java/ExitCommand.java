public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        storage.save(tasks.getTasks());
        ui.showCommand("Adios!! See you...");
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
