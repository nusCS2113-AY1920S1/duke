import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public class Duke {
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        tasks = new TaskList(taskList);
    }
    ArrayList<Task> taskList = new ArrayList<>(100);

    public void loadFile() {
        File file = new File("/Users/qianjie/Desktop/duke/src/main/java/data/duke.txt");
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String input = bufferedReader.readLine();

            while (input != null) {
                parser.loadParse(taskList , input);
                input = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void run() {
        ui.showWelcome();
        loadFile();
        String day;

        String userInput = ui.readUserInput();

        while(!userInput.equals("bye")) {
            String[] firstBox = parser.firstFilter(userInput); // return a string array with commands and description
            String commands = firstBox[0];
            try {
                switch (commands) {
                    case "list":
                        ui.showTaskList(taskList);
                        break;

                    case "done":
                        tasks.runDone(userInput);
                        break;

                    case "delete":
                        tasks.runDelete(userInput);
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
                            break;
                        }

                    case "deadline":
                        String[] secondBox = parser.secondFilter(firstBox[1] , "deadline");
                        day = parser.dayExtractor(secondBox);
                        String timeInString = parser.timeFormatter(secondBox[1].trim() , day);
                        tasks.runDeadline(secondBox[0] , timeInString);
                        break;

                    case "event":
                        secondBox = parser.secondFilter(firstBox[1] , "event");
                        timeInString = parser.timeFormatter(secondBox[1] , parser.dayExtractor(secondBox));
                        tasks.runEvent(secondBox[0] , timeInString);
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
