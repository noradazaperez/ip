import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    // constant values
    private static final int DONE = 0;
    private static final int TYPE = 1;
    private static final int DESCRIPTION = 2;
    private static final int DATE1 = 3;
    private static final int DATE2 = 4;
    /**
     * It loads the information of the given file and returns a list of tasks
     *
     * @return the list of tasks
     * @throws MimiException if the specified file does not exist
     */
    public ArrayList<Task> load() throws MimiException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task;

        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                // Skip empty or whitespace-only lines if needed
                if (line.isEmpty()) {
                    continue;
                }

                // Split by '|'
                String[] parts = line.split("\\|");

                if (parts.length < 3) {
                    throw new MimiException("Oh no... There is a PROBLEMA with the file");
                }

                // Convert "Y"/"N" to boolean
                boolean done = parts[DONE].trim().equalsIgnoreCase("Y");
                // The third field is always the description
                String description = parts[DESCRIPTION].trim();
                // The second field is the type (T/D/E)
                String type = parts[TYPE].trim();

                if (type.equalsIgnoreCase("T")) {
                    task = new Task(description, done);
                } else if (type.equalsIgnoreCase("D")) {
                    if (parts.length < 4) {
                        throw new MimiException("Oh no... There is a PROBLEMA with the file");
                    }
                    task = new Deadline(description, done, parts[DATE1].trim());
                } else if (type.equalsIgnoreCase("E")) {
                    if (parts.length < 5) {
                        throw new MimiException("Oh no... There is a PROBLEMA with the file");
                    }
                    task = new Event(description, done, parts[DATE1].trim(), parts[DATE2].trim());
                } else {
                    // if the type is not defined
                    throw new MimiException("Oh no... There is a PROBLEMA with the file");
                }

                tasks.add(task);

            }
        } catch (IOException e) {
            throw new MimiException("Ay nooo, I don't know what happened. Error reading file: " + file.getAbsolutePath());
        }

        return tasks;
    }

    /**
     * It saves the information in the list of tasks to the specified file
     *
     * @param tasks array of tasks to be saved
     * @throws MimiException if the file does not exist
     */
    public void save(ArrayList<Task> tasks) throws MimiException {
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.printFile() + "\n");
            }
        } catch (IOException e) {
            throw new MimiException("Ay noo, the file " + file.getAbsolutePath() + " could not be saved.");
        }
    }
}


