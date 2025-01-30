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
            String [] input = userinput.split(" ");
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
                default:
                    addTask(userinput);
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

    public static void addTask(String taskDescription) {
        Task task = new Task(taskDescription);
        tasks[taskIndex] = task;
        taskIndex++;

        System.out.println("added: " + task.getDescription());
    }

    public static void showList() {
        for (int i = 0; i < taskIndex; i++) {
            System.out.println((i+1) + ". " + tasks[i].toString());
        }
    }
}
