import java.util.Scanner;

public class Ui {
    private final String bar = "--------------------------------------------------";

    public void showTasks(TaskList taskList) {
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            System.out.println((i + 1) + ": " + taskList.getTasks().get(i).toString());
        }
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

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

    public void showLine() {
        System.out.println(bar);
    }

    public void showError(String error) {
        System.out.println(error);
        //todo change something here maybe
    }

    public void showCommand(Command command) {
        System.out.println(command.getCommand());
    }
}
