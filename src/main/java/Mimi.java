import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Mimi {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Mimi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (MimiException e) {
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try{
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                ui.showCommand(c);
                ui.showLine();
                isExit = c.isExit();
            } catch (MimiException me) {
                // handle invalid input
                ui.showError(me.toString());
            }
        }
    }


    public static void main(String[] args) {
        new Mimi("data/out.txt").run();
    }



}
