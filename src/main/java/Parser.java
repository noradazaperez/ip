/**
 * Provides a utility method for parsing user input into corresponding {@code Command} objects.
 *
 */
public class Parser {

    // Constants for command markers
    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_FROM_DELIMITER = "/from";
    private static final String EVENT_TO_DELIMITER = "/to";

    /**
     * Parses the user input string and returns the corresponding {@code Command} object.
     *
     * @param input the full command string entered by the user
     * @return a {@code Command} object representing the parsed command
     * @throws MimiException if no command is entered, if required arguments are missing,
     *                       or if the input cannot be parsed correctly
     */
    public static Command parse(String input) throws MimiException {
        if (input == null || input.trim().isEmpty()) {
            throw new MimiException("Lo siento!! You did not enter a valid command.");
        }

        // Split into command and argument(s)
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
        case "list":
            return new ShowListCommand();
        case "mark":
            return createMarkCommand(arguments);
        case "unmark":
            return createUnmarkCommand(arguments);
        case "todo":
            return createTodoCommand(arguments);
        case "deadline":
            return createDeadlineCommand(arguments);
        case "event":
            return createEventCommand(arguments);
        case "delete":
            return createDeleteCommand(arguments);
        case "find":
            return createFindCommand(arguments);
        case "exit":
            return new ExitCommand();
        default:
            throw new MimiException("Perdona?? I think I don't know the command: " + command);
        }
    }

    /**
     * Create a mark command
     *
     * @param arguments         argument entered by the user to mark command
     * @return                  the {@code Command} object
     * @throws MimiException    if the index is not correct
     */
    private static Command createMarkCommand(String arguments) throws MimiException {
        if (arguments.isEmpty()) {
            throw new MimiException("Lo siento! You did not enter an index.");
        }
        try {
            int index = Integer.parseInt(arguments);
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new MimiException("Lo siento! You did not enter a valid index: " + arguments);
        }
    }

    /**
     * Create a unmark command
     *
     * @param arguments         argument entered by the user to unmark command
     * @return                  the {@code Command} object
     * @throws MimiException    if the index is not correct
     */
    private static Command createUnmarkCommand(String arguments) throws MimiException {
        if (arguments.isEmpty()) {
            throw new MimiException("Lo siento! You did not enter an index.");
        }
        try {
            int index = Integer.parseInt(arguments);
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new MimiException("Lo siento! You did not enter a valid index: " + arguments);
        }
    }

    /**
     * Create a todo command
     *
     * @param arguments         argument entered by the user with the description
     * @return                  the {@code Command} object
     * @throws MimiException    if the description was not entered
     */
    private static Command createTodoCommand(String arguments) throws MimiException {
        if (arguments.isEmpty()) {
            throw new MimiException("Lo siento! You did not enter a description.");
        }
        return new AddCommand(new ToDo(arguments));
    }

    /**
     * Create a deadline command
     *
     * @param arguments         argument entered by the user with the description
     * @return                  the {@code Command} object
     * @throws MimiException    if the description was not entered
     */
    private static Command createDeadlineCommand(String arguments) throws MimiException {
        if (arguments.isEmpty()) {
            throw new MimiException("Lo siento! You did not enter a deadline.");
        }
        int byIndex = arguments.indexOf(DEADLINE_DELIMITER);
        if (byIndex == -1) {
            throw new MimiException("Lo siento! deadline command must include '" +
                    DEADLINE_DELIMITER + "' followed by the deadline time.");
        }
        String taskDescription = arguments.substring(0, byIndex).trim();
        String deadlineTime = arguments.substring(byIndex + DEADLINE_DELIMITER.length()).trim();

        Deadline deadline = new Deadline(taskDescription, deadlineTime);
        return new AddCommand(deadline);
    }

    /**
     * Create an event command
     *
     * @param arguments         argument entered by the user with the description
     * @return                  the {@code Command} object
     * @throws MimiException    if the description was not entered
     */
    private static Command createEventCommand(String arguments) throws MimiException {
        if (arguments.isEmpty()) {
            throw new MimiException("Lo siento! You did not enter a description.");
        }
        int fromIndex = arguments.indexOf(EVENT_FROM_DELIMITER);
        int toIndex = arguments.indexOf(EVENT_TO_DELIMITER);
        if (fromIndex == -1 || toIndex == -1) {
            throw new MimiException("Lo siento! event command must include '" +
                    EVENT_FROM_DELIMITER + "' and '" + EVENT_TO_DELIMITER + "' with valid positions.");
        }
        String taskDescription = arguments.substring(0, fromIndex).trim();
        String fromTime = arguments.substring(fromIndex + EVENT_FROM_DELIMITER.length(), toIndex).trim();
        String toTime = arguments.substring(toIndex + EVENT_TO_DELIMITER.length()).trim();

        Event event = new Event(taskDescription, fromTime, toTime);
        return new AddCommand(event);
    }

    /**
     * Create a delete command
     *
     * @param arguments         argument entered by the user with the index
     * @return                  the {@code Command} object
     * @throws MimiException    if the index is not correct
     */
    private static Command createDeleteCommand(String arguments) throws MimiException {
        if (arguments.isEmpty()) {
            throw new MimiException("Lo siento! You did not enter an index.");
        }
        try {
            int index = Integer.parseInt(arguments);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new MimiException("Lo siento! You did not enter a valid index: " + arguments);
        }
    }

    /**
     * Create a find command
     *
     * @param arguments         argument entered by the user with the keyword
     * @return                  the {@code Command} object
     * @throws MimiException    if the keyword is not correct
     */
    private static Command createFindCommand(String arguments) throws MimiException {
        if (arguments.isEmpty()) {
            throw new MimiException("Lo siento! You did not enter a keyword.");
        }
        return new FindCommand(arguments);
    }
}
