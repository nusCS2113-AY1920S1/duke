import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class Duke {
    private Tasklist taskList;
    private UI ui;
    private Storage storage;
    private Duke(String filePath) {
        this.ui = new UI();
        this.storage = new Storage(filePath);
        try {
            taskList = new Tasklist(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new Tasklist();
        }
    }
    private void run() {
        ui.showWelcome();
        String command= "";
        boolean isExit = false;
        while(!isExit)
        {
            try {
                String line = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(line);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            }catch (DukeException e) {
                ui.showError(e.getLocalizedMessage());
            }
            catch(NullPointerException e){
                ui.showError(e.getLocalizedMessage());
            }
            finally {
                ui.showLine();
            }
        }
        try {
            ui.close();
            storage.store(this.taskList);
        }
        catch(DukeException e)
        {
            ui.showError(e.getLocalizedMessage());
        }
    }
    public static void main(String[] args) {
        /* I am using A collections */
        new Duke("list.txt").run();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.exit(0);
    }

}
