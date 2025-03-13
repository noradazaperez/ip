import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the main application for the Mimi program.
 * <p>
 * This class initializes the user interface, storage, and task list, and manages the
 * processing of user commands until the application exits.
 * </p>
 */
public class Mimi {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a new instance of the Mimi application.
     * <p>
     * The constructor initializes the UI and storage, then attempts to load
     * the task list from persistent storage. If loading fails, an empty task list is created.
     * </p>
     *
     * @param filePath the path to the file used for storing the application's data
     */
    public Mimi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (MimiException e) {
            ui.showError(e.toString());
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Mimi application.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                ui.showLine();
                isExit = c.isExit();
            } catch (MimiException me) {
                ui.showError(me.toString());
                ui.showLine();
            }
        }
    }

    /**
     * The entry point of the Mimi application.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        new Mimi("data/out.txt").run();
    }



}
