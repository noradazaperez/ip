import java.io.*;
import java.util.Scanner;

public class Mimi {
    static final String bar = "--------------------------------------------------";
    static Task [] tasks = new Task[100];
    static int taskIndex = 0;
    static String fileName = "data/out.txt";

    public static void main(String[] args) {

        greet();
        Scanner sc = new Scanner(System.in);
        String userinput;
        try {
            loadTasks(new File(fileName));
        } catch (MimiException e) {
            System.out.println(e);
        }

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
                saveTasks(new File(fileName));
            } catch (MimiException me) {
                // handle invalid input
                System.out.println(me.toString());
            }
        }

        sc.close();
    }

    private static void loadTasks(File file) throws MimiException {
        try (Scanner sc = new Scanner(file)){

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                // Skip empty or whitespace-only lines if needed
                if (line.isEmpty()){
                    continue;
                }

                // Split by '|' (pipe)
                String[] parts = line.split("\\|");

                // Basic validation
                if (parts.length < 3) {
                    throw new MimiException("Oh no... There is a PROBLEMA with the file");
                }

                // Convert "Y"/"N" to boolean
                boolean done = parts[0].equalsIgnoreCase("Y");

                // The third field is always the description
                String description = parts[2].trim();
                Task task;

                // The second field is the type (T/D/E)
                String type = parts[1].trim();
                if (type.equalsIgnoreCase("T")) {
                    task = new Task(description);
                }
                else if (type.equalsIgnoreCase("D")) {
                    // Validation
                    if (parts.length < 4) {
                        throw new MimiException("Oh no... There is a PROBLEMA with the file");
                    }
                    task = new Deadline(description, parts[3].trim());
                }
                else if (type.equalsIgnoreCase("E")) {
                    // Validation
                    if (parts.length < 5) {
                        throw new MimiException("Oh no... There is a PROBLEMA with the file");
                    }
                    task = new Event(description, parts[4].trim(), parts[5].trim());
                }
                else {
                    // ERROR
                    throw new MimiException("Oh no... There is a PROBLEMA with the file");
                }

                tasks[taskIndex++] = task;

            }
        } catch (IOException e) {
            throw new MimiException("Ay nooo, I don't know what happened. Error reading file: " + file.getAbsolutePath());
        }


    }

    private static void saveTasks(File file) throws MimiException {
        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < taskIndex; i++) {
                writer.write(tasks[i].printFile() + "\n");
            }
        } catch (IOException e) {
            throw new MimiException("Ay noo, the file " + file.getAbsolutePath() + " could not be saved.");
        }
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
                    throw new MimiException("Oh no... There is a PROBLEMA with the description.");
                }
                try{
                    mark(Integer.parseInt(description));
                } catch (NumberFormatException e) {
                    throw new MimiException("Oh no... There is a PROBLEMA with the index number.");
                }

                break;
            case "unmark":
                // parts[1] must exist, the description
                if (parts.length == 1) {
                    throw new MimiException("Oh no... There is a PROBLEMA with the description");
                }
                try{
                    unmark(Integer.parseInt(description));
                } catch (NumberFormatException e) {
                    throw new MimiException("Oh no.. There is  a PROBLEMA with the index number.");
                }
                break;
            case "todo":
                // parts[1] must exist, the description
                if (parts.length == 1) {
                    throw new MimiException("Oh no... There is a PROBLEMA with the description");
                }
                addToDo(description);
                break;
            case "deadline":
                // parts[1] must exist, the description
                if (parts.length == 1) {
                    throw new MimiException("Oh no... There is a PROBLEMA with the description");
                }
                addDeadline(description);
                break;
            case "event":
                // parts[1] must exist, the description
                if (parts.length == 1) {
                    throw new MimiException("Oh no... There is a PROBLEMA with the description.");
                }
                addEvent(description);
                break;
            default:
                throw new MimiException("Perdona?? I think I don't know the command: " + command);
        }

    }

    public static void mark(int index) throws MimiException {

        // error handling
        if (index > taskIndex) {
            throw new MimiException("Sorry! The task doesn't exist.");
        }
        tasks[index - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks[index - 1]);
    }

    public static void unmark(int index) throws MimiException {
        // error handling
        if (index > taskIndex) {
            throw new MimiException("Sorry! The task doesn't exist.");
        }
        tasks[index - 1].markAsNotDone();

        // output message
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(tasks[index - 1]);
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
        tasks[taskIndex] = task;
        taskIndex++;

        // output message
        System.out.println("That's great! I've added: \n" + task);
        System.out.println("Now you have " + taskIndex + " tasks");
    }

    public static void addDeadline(String taskDescription) {
        int index = taskDescription.indexOf("/by");
        Deadline deadline = new Deadline(taskDescription.substring(0, index),
                taskDescription.substring(index + 4));
        tasks[taskIndex] = deadline;
        taskIndex++;

        // output message
        System.out.println("That's great! I've added:\n " + deadline);
        System.out.println("Now you have " + taskIndex + " tasks");
    }

    public static void addEvent(String taskDescription) {
        int indexFrom = taskDescription.indexOf("/from");
        int indexTo = taskDescription.indexOf("/to");

        Event event = new Event(taskDescription.substring(0, indexFrom),
                taskDescription.substring(indexFrom + 6, indexTo),
                taskDescription.substring(indexTo + 4));
        tasks[taskIndex] = event;
        taskIndex++;

        // output message
        System.out.println("That's great! I've added:\n " + event);
        System.out.println("Now you have " + taskIndex + " tasks");
    }

    public static void showList() {
        for (int i = 0; i < taskIndex; i++) {
            System.out.println((i+1) + ". " + tasks[i].toString());
        }

    }
}
