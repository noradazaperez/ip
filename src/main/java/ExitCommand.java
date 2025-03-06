public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getCommand() {
        return "Adios!!! See you.....";
    }


}
