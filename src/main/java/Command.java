/**
 * Represents a command that can be executed within the application.
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
    void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException;

    /**
     * Determines whether this command signals that the application should exit.
     *
     * @return {@code true} if the command is an exit command;
     * {@code false} otherwise
     */
    boolean isExit();

}
