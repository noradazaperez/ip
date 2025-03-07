/**
 * Represents a command that can be executed within the application.
 * <p>
 * Implementations of this interface encapsulate a specific action to be performed,
 * </p>
 * <p>
 * Example commands might include adding a task, deleting a task, or marking a task as completed.
 * </p>
 */
public interface Command {

    /**
     * Executes the command using the provided task list, UI, and storage.
     *
     * @param tasks   the task list on which the command will operate
     * @param ui      the user interface for interacting with the user
     * @param storage the storage mechanism for saving or retrieving data
     * @throws MimiException if an error occurs during the execution of the command
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException;

    /**
     * Determines whether this command signals that the application should exit.
     *
     * @return {@code true} if the command should cause the application to terminate;
     *         {@code false} otherwise
     */
    public boolean isExit();

    /**
     * Retrieves a string representation of this command.
     *
     * @return a string that describes this command, useful for logging or debugging
     */
    public String getCommand();
}
