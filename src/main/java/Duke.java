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
    public void run() {
        ui.showWelcome();
        String command= "";
        boolean isExit = false;
        while(!isExit)
        {
            try {
                String line = ui.readCommand();
                Parser.parse(line);
                ui.showLine();
                line += " ";
                Scanner temp = new Scanner(line);
                command = temp.next();
                String input = temp.nextLine();
                input = input.trim();
                if (input.equals("") && command.matches("todo|deadline|event|done|delete")) {
                    throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
                } else if (command.matches("list|bye")) {
                    if (input.equals("")) {
                        if (command.equals("list")) {
                            if (taskList.size() == 0) {
                                System.out.println("Whoops, there doesn't seem to be anything here at the moment");
                            } else {
                                taskList.print();
                            }
                        } else if (command.equals("bye")) {
                            isExit = true;
                            System.out.println("Bye. Hope to see you again soon!");
                        }
                    } else
                        throw new DukeException("List should not have any other arguments (whitespace acceptable");
                } else if (command.matches("todo|deadline|event")) {
                        taskList.add(command, input);
                } else if (command.matches("done|delete")) {
                    int request = Integer.parseInt(input);
                    request -= 1;
                    if (taskList.isOutOfRange(request))
                        throw new DukeException("☹ OOPS!!! This index is not within the list");
                    else {
                        if (command.equals("done"))
                            taskList.markDone(request);
                        else if (command.equals("delete"))
                            taskList.banishDelete(request);
                    }
                }
                else
                {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }catch (DukeException e) {
                    System.out.println(e.getLocalizedMessage());
            } catch (NumberFormatException e) {
                    System.out.println("That is NOT a valid integer");
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
