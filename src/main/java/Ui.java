import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interaction and output for the Mimi application.
 * <p>
 * This class is responsible for reading user input from the console, displaying messages,
 * printing task lists, greeting the user with a logo, and showing errors.
 * </p>
 */
public class Ui {

    /**
     * A separator line used to visually divide sections of output.
     */
    private String bar = "--------------------------------------------------";

    /**
     * Prints the tasks
     *
     * @param tasks tasks to be printed
     */
    public void showTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ": " + tasks.get(i).toString());
        }
    }

    /**
     * Reads a line of input from the user via the console.
     *
     * @return the input command entered by the user as a {@code String}
     */
    public String readCommand(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Greets the user by displaying an ASCII logo and introductory messages.
     */
    public void greet() {
        String logo = """
                 M   M  III  M   M  III\s
                 MM MM   I   MM MM   I\s
                 M M M   I   M M M   I\s
                 M   M   I   M   M   I\s
                 M   M  III  M   M  III\s
                """;
        showLine();
        System.out.println("\nHello from\n" + logo);
        showLine();
        System.out.println("Hola!! I'm Mimi. I'm still learning English... Sorry if I make mistakes");
        System.out.println("What can I do for you?\n");
        showLine();
    }

    /**
     * Displays a separator line to the console.
     */
    public void showLine() {
        System.out.println(bar);
    }

    /**
     * Displays an error message to the user.
     *
     * @param error the error message to be displayed
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Displays the output message corresponding to a command.
     *
     * @param command the {@code Command} whose output message is to be shown
     */
    public void showCommand(Command command){
        System.out.println(command.getCommand());
    }
}
