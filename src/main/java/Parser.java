/**
 * Provides a utility method for parsing user input into corresponding {@code Command} objects.
 * <p>
 * The {@code parse} method interprets the first word of the input as the command type and uses
 * the rest of the input as arguments or descriptions for that command. Depending on the command,
 * it returns an appropriate {@code Command} implementation. If the input is invalid or cannot be parsed,
 * a {@code MimiException} is thrown.
 * </p>
 */
public class Parser {
    private static final int COMMAND = 0;
    private static final int ARG = 1;
    /**
     * Parses the user input string and returns the corresponding {@code Command} object.
     *
     * @param input the full command string entered by the user
     * @return a {@code Command} object representing the parsed command
     * @throws MimiException if no command is entered, if required arguments are missing,
     *                       or if the input cannot be parsed correctly
     */
    public static Command parse(String input) throws MimiException {

        String[] parts = input.split(" ", 2);

        // parts[0] is the command.
        if (parts.length == 0 || parts[COMMAND].isEmpty()) {
            throw new MimiException("No command was entered.");
        }
        String command = parts[COMMAND].toLowerCase();
        String description = parts.length > 1 ? parts[ARG].trim() : "";

        switch (command) {
        case "list":
            return new ShowListCommand();

        case "mark":
            // Ensure description exists
            if (parts.length == 1) {
                throw new MimiException("Oh no... There is a PROBLEMA with the description.");
            }
            try {
                return new MarkCommand(Integer.parseInt(description));
            } catch (NumberFormatException e) {
                throw new MimiException("Oh no... There is a PROBLEMA with the index number.");
            }

        case "unmark":
            // Ensure description exists
            if (parts.length == 1) {
                throw new MimiException("Oh no... There is a PROBLEMA with the description");
            }
            try {
                return new UnmarkCommand(Integer.parseInt(description));
            } catch (NumberFormatException e) {
                throw new MimiException("Oh no.. There is  a PROBLEMA with the index number.");
            }

        case "todo":
            // Ensure description exists
            if (parts.length == 1) {
                throw new MimiException("Oh no... There is a PROBLEMA with the description");
            }
            return new AddCommand(new Task(description));

        case "deadline":
            // Ensure description exists
            if (parts.length == 1) {
                throw new MimiException("Oh no... There is a PROBLEMA with the description");
            }
            int index = description.indexOf("/by");
            // TODO: Handle the case where "/by" is not present in the description.
            Deadline deadline = new Deadline(description.substring(0, index).trim(),
                    description.substring(index + 4).trim());
            return new AddCommand(deadline);

        case "event":
            // Ensure description exists
            if (parts.length == 1) {
                throw new MimiException("Oh no... There is a PROBLEMA with the description.");
            }
            int indexFrom = description.indexOf("/from");
            int indexTo = description.indexOf("/to");
            Event event = new Event(description.substring(0, indexFrom).trim(),
                    description.substring(indexFrom + 6, indexTo).trim(),
                    description.substring(indexTo + 4).trim());
            return new AddCommand(event);

        case "delete":
            // Ensure description exists
            if (parts.length == 1) {
                throw new MimiException("No description was entered.");
            }
            return new DeleteCommand(Integer.parseInt(description));

        case "find":
            if (parts.length == 1) {
                throw new MimiException("No description was entered.");
            }
            return new FindCommand(description);

        case "exit":
            return new ExitCommand();

        default:
            throw new MimiException("Perdona?? I think I don't know the command: " + command);
    }
    }
}
