import java.util.Scanner;

public class Mimi {
    static String bar = "--------------------------------------------------";
    static Task [] tasks = new Task[100];
    static int taskIndex = 0;

    public static void main(String[] args) {
        String userinput;
        greet();
        Scanner sc = new Scanner(System.in);
        do {
            userinput = sc.nextLine();
            System.out.println(bar);
            String [] input = userinput.split(" ",2);

            switch (input[0]) {
                case "list":
                    showList();
                    break;
                case "mark":
                    mark(Integer.parseInt(input[1]));
                    break;
                case "unmark":
                    unmark(Integer.parseInt(input[1]));
                    break;
                case "todo":
                    addToDo(input[1]);
                    break;
                case "deadline":
                    addDeadline(input[1]);
                    break;
                case "event":
                    addEvent(input[1]);
                    break;
                default:
                    break;
            }

            System.out.println(bar);

        } while (!userinput.equals("bye"));
        System.out.println("Bye. Hope to see you again soon.\n" + bar);

    }

    public static void mark(int index){
        if (index - 1 > taskIndex) {
            System.out.println("Sorry! The task doesn't exist.");
        }
        tasks[index - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks[index - 1]);
    }

    public static void unmark(int index){
        if (index - 1 > taskIndex) {
            System.out.println("Sorry! The task doesn't exist.");
        }
        tasks[index - 1].markAsNotDone();
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
        System.out.println("Hello! I'm Mimi");
        System.out.println("What can I do for you?\n" + bar);

    }



    public static void addToDo(String taskDescription) {
        Task task = new Task(taskDescription);
        tasks[taskIndex] = task;
        taskIndex++;

        System.out.println("That's great! I've added: \n" + task);
        System.out.println("Now you have " + taskIndex + " tasks");
    }

    public static void addDeadline(String taskDescription) {
        int index = taskDescription.indexOf("/by");
        Deadline deadline = new Deadline(taskDescription.substring(0, index),
                taskDescription.substring(index + 4));
        tasks[taskIndex] = deadline;
        taskIndex++;

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

        System.out.println("That's great! I've added:\n " + event);
        System.out.println("Now you have " + taskIndex + " tasks");
    }

    public static void showList() {
        for (int i = 0; i < taskIndex; i++) {
            System.out.println((i+1) + ". " + tasks[i].toString());
        }

    }
}
