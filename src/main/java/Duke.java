import java.text.ParseException;

public class Duke {
    private Storage storage;
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        String day;

        String userInput = ui.readUserInput();

        while(!userInput.equals("bye")) {
            String[] firstBox = parser.firstFilter(userInput); // return a string array with commands and description
            String commands = firstBox[0];
            try {
                switch (commands) {
                    case "list":
                        ui.showTaskList(tasks.getTaskList());
                        break;

                    case "done":
                        tasks.runDone(userInput);
                        storage.save();
                        break;

                    case "delete":
                        tasks.runDelete(userInput);
                        storage.save();
                        break;

                    case "find":
                        tasks.runFind(userInput);
                        break;

                    case "todo":
                        try {
                            if (firstBox[1].isBlank()){
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            }else {
                                tasks.runToDo(firstBox[1]);
                            }
                        }catch(DukeException e) {
                            e.printStackTrace();
                        }finally{
                            storage.save();
                            break;
                        }

                    case "deadline":
                        String[] secondBox = parser.secondFilter(firstBox[1] , "deadline");
                        day = parser.dayExtractor(secondBox);
                        String timeInString = parser.timeFormatter(secondBox[1].trim() , day);
                        tasks.runDeadline(secondBox[0] , timeInString);
                        storage.save();
                        break;

                    case "event":
                        secondBox = parser.secondFilter(firstBox[1] , "event");
                        timeInString = parser.timeFormatter(secondBox[1] , parser.dayExtractor(secondBox));
                        tasks.runEvent(secondBox[0] , timeInString);
                        storage.save();
                        break;

                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }


            }catch(DukeException | ParseException e) {
                e.printStackTrace();
            }finally {
                userInput = ui.readUserInput().trim();
            }

        }
        ui.showExit();
    }

    public static void main(String[] args) {
        new Duke().run();

    }
}
