public abstract interface Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException;
    public boolean isExit();
    public String getCommand();
}
