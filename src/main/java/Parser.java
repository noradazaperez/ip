public class Parser {


    public static Command parse(String input) throws MimiException {

        // Split into at most 2 parts: [0] = command, [1] = rest
        String[] parts = input.split(" ", 2);

        // parts[0] is the command; it must exist
        if (parts.length == 0 || parts[0].isEmpty()) {
            throw new MimiException("No command was entered.");
        }
        String command = parts[0].toLowerCase();
        String description = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
            case "list":
                return new ShowListCommand();
            case "mark":
                // parts[1] must exist, the description
                if (parts.length == 1) {
                    throw new MimiException("Oh no... There is a PROBLEMA with the description.");
                }
                try {
                    return new MarkCommand(Integer.parseInt(description));
                } catch (NumberFormatException e) {
                    throw new MimiException("Oh no... There is a PROBLEMA with the index number.");
                }

            case "unmark":
                // parts[1] must exist, the description
                if (parts.length == 1) {
                    throw new MimiException("Oh no... There is a PROBLEMA with the description");
                }
                try {
                    return new UnmarkCommand(Integer.parseInt(description));
                } catch (NumberFormatException e) {
                    throw new MimiException("Oh no.. There is  a PROBLEMA with the index number.");
                }

            case "todo":
                // parts[1] must exist, the description
                if (parts.length == 1) {
                    throw new MimiException("Oh no... There is a PROBLEMA with the description");
                }
                return new AddCommand(new Task(description));

            case "deadline":
                // parts[1] must exist, the description
                if (parts.length == 1) {
                    throw new MimiException("Oh no... There is a PROBLEMA with the description");
                }
                int index = description.indexOf("/by");
                //todo what happens if there is not a by ?
                Deadline deadline = new Deadline(description.substring(0, index), description.substring(index + 4));
                return new AddCommand(deadline);

            case "event":
                // parts[1] must exist, the description
                if (parts.length == 1) {
                    throw new MimiException("Oh no... There is a PROBLEMA with the description.");
                }
                int indexFrom = description.indexOf("/from");
                int indexTo = description.indexOf("/to");

                Event event = new Event(description.substring(0, indexFrom), description.substring(indexFrom + 6, indexTo), description.substring(indexTo + 4));
                return new AddCommand(event);

            case "delete":
                // parts[1] must exist, the description
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
