import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class Duke {
    private Tasklist taskList;
    private UI ui;
    private Storage storage;
    public Duke(String filePath) {
        this.ui = new UI();
        this.storage = new Storage(filePath);
        try {
            taskList = new Tasklist(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new Tasklist();
        }
    }
    public void run(){
        ui.showWelcome();
        String command= "";
        boolean isExit = false;
        while(!isExit)
        {
            try {
                String line = ui.readCommand();
                ui.showLine();
                Parser.parse(line);
                line += " ";
                Scanner temp = new Scanner(line);
                command = temp.next();
                String input = temp.nextLine();
                input = input.trim();
                if (command.equals("list")) {
                        taskList.print();
                } else if (command.equals("bye")) {
                    isExit = true;
                    System.out.println("Bye. Hope to see you again soon!");
                } else if (command.matches("todo|deadline|event")) {
                    taskList.add(command, input);
                } else if (command.equals("find")){
                    taskList.find(input);
                } else if (command.equals("done")) {
                    taskList.markDone(input);
                } else if (command.equals("delete")){
                    taskList.banishDelete(input);
                }
                else
                {
                    throw new DukeException("How the hell did you reach here");
                }
            }catch (DukeException e) {
                System.out.println(e.getLocalizedMessage());
            }
            finally {
                ui.showLine();
            }
        }
        ui.close();
    }
    public static void main(String[] args) {
        /* I am using A collections */
        Duke duke = new Duke("list.txt");
        duke.run();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.exit(0);
    }

}
