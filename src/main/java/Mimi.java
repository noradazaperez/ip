import java.util.Scanner;

public class Mimi {
    static String bar = "--------------------------------------------------";

    public static void main(String[] args) {
        String userinput;
        greet();
        Scanner sc = new Scanner(System.in);
        do {
            userinput = sc.nextLine();
            System.out.println(bar + "\n" + userinput + "\n" + bar);

        } while (!userinput.equals("bye"));
        System.out.println("Bye. Hope to see you again soon.\n" + bar);

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
}
