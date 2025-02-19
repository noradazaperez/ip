import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collection;

public class Mimi {
    static final String bar = "--------------------------------------------------";
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        greet();
        Scanner sc = new Scanner(System.in);
        String userinput;


        while (true) {
            System.out.print("> ");
            userinput = sc.nextLine();
            System.out.println(bar);

            // exit if the user types something like 'quit' or 'exit
            if (userinput.equalsIgnoreCase("exit")) {
                System.out.println("Mimi says adios to you...");
                break;
            }

            try {
                // process the user input
                processInput(userinput);
            } catch (MimiException me) {
                // handle invalid input
                System.out.println(me.toString());
            }
        }

        sc.close();

    }

    /**
     * Splits the user input and decides what to do
     * @param userinput the raw command line from the user
     * @throws MimiException if input format is incorrect
     */
    public static void processInput(String userinput) throws MimiException {
        // Split into at most 2 parts: [0] = command, [1] = rest
        String[] parts = userinput.split(" ", 2);

        // parts[0] is the command; it must exist
        if (parts.length == 0 || parts[0].isEmpty()) {
            throw new MimiException("No command was entered.");
        }


        String command = parts[0].toLowerCase();
        String description = parts.length > 1? parts[1].trim(): "";

        switch (command) {
            case "list":
                showList();
                break;
            case "mark":
                // parts[1] must exist, the description
                if (parts.length == 1) {
                    throw new MimiException("No description was entered.");
                }
                mark(Integer.parseInt(description));
                break;
            case "unmark":
                // parts[1] must exist, the description
                if (parts.length == 1) {
                    throw new MimiException("No description was entered.");
                }
                unmark(Integer.parseInt(description));
                break;
            case "todo":
                // parts[1] must exist, the description
                if (parts.length == 1) {
                    throw new MimiException("No description was entered.");
                }
                addToDo(description);
                break;
            case "deadline":
                // parts[1] must exist, the description
                if (parts.length == 1) {
                    throw new MimiException("No description was entered.");
                }
                addDeadline(description);
                break;
            case "event":
                // parts[1] must exist, the description
                if (parts.length == 1) {
                    throw new MimiException("No description was entered.");
                }
                addEvent(description);
                break;
            case "delete":
                break;
            default:
                throw new MimiException("Perdona?? I think I don't know the command: " + command);
        }

    }

    /**
     * Marks a task as done
     * @param index     index number of the task
     * @throws MimiException    if the task does not exist
     */
    public static void mark(int index) throws MimiException {

        // error handling
        if (index > tasks.size()) {
            throw new MimiException("Sorry! The task doesn't exist.");
        }
        tasks.get(index - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(index - 1));
    }

    public static void unmark(int index) throws MimiException {
        // error handling
        if (index > tasks.size()) {
            throw new MimiException("Sorry! The task doesn't exist.");
        }
        tasks.get(index - 1).markAsNotDone();

        // output message
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(tasks.get(index - 1));
    }

    public static void greet() {
        String logo = """
                 M   M  III  M   M  III\s
                 MM MM   I   MM MM   I\s
                 M M M   I   M M M   I\s
                 M   M   I   M   M   I\s
                 M   M  III  M   M  III\s
                """;
        System.out.println(bar + "\nHello from\n" + logo + bar);
        System.out.println("Hola!! I'm Mimi. I'm still learning English... Sorry if I make mistakes");
        System.out.println("What can I do for you?\n" + bar);

    }



    public static void addToDo(String taskDescription) {
        Task task = new Task(taskDescription);
        tasks.add(task);


        // output message
        System.out.println("That's great! I've added: \n" + task);
        System.out.println("Now you have " + tasks.size() + " tasks");
    }

    public static void addDeadline(String taskDescription) {
        int index = taskDescription.indexOf("/by");
        Deadline deadline = new Deadline(taskDescription.substring(0, index),
                taskDescription.substring(index + 4));
        tasks.add(deadline);
        // output message
        System.out.println("That's great! I've added:\n " + deadline);
        System.out.println("Now you have " + tasks.size() + " tasks");
    }

    public static void addEvent(String taskDescription) {
        int indexFrom = taskDescription.indexOf("/from");
        int indexTo = taskDescription.indexOf("/to");

        Event event = new Event(taskDescription.substring(0, indexFrom),
                taskDescription.substring(indexFrom + 6, indexTo),
                taskDescription.substring(indexTo + 4));
        tasks.add(event);

        // output message
        System.out.println("That's great! I've added:\n " + event);
        System.out.println("Now you have " + tasks.size() + " tasks");
    }

    public static void showList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i).toString());
        }

    }
}
