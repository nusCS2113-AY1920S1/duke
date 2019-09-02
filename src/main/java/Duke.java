import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public class Duke {
    private Parser parser;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
    }
    private static final String WHITESPACE = " ";
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

    public void writeToFile () {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try{
            File file = new File("/Users/qianjie/Desktop/duke/src/main/java/data/duke.txt");
            fileWriter = new FileWriter(file , false);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Task t : taskList) {
                String taskType = t.getType();
                String taskStatus = t.getStatusIcon();
                String taskDescription = t.getDescription();
                String taskTime = t.getTime();

                if (taskType.equals("T")) {
                    bufferedWriter.write(taskType + " | " + taskStatus + " | " + taskDescription);
                    bufferedWriter.newLine();

                }else{
                    bufferedWriter.write(taskType + " | " + taskStatus + " | " + taskDescription + " | " + taskTime);
                    bufferedWriter.newLine();
                }


                bufferedWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void run() {
        ui.showWelcome();
        loadFile();
        String newString;
        String[] secondBox;
        String description;
        String time;
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
                        runDone(userInput);
                        break;

                    case "delete":
                        runDelete(userInput);
                        break;

                    case "find":
                        runFind(userInput);
                        break;

                    case "todo":
                        try {
                            if (firstBox[1].isBlank()){
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            }else {
                                description = firstBox[1];
                                runToDo(description);
                            }
                        }catch(DukeException e) {
                            e.printStackTrace();
                        }finally{
                            break;
                        }

                    case "deadline":
                        newString = firstBox[1];
                        secondBox = parser.secondFilter(newString , "deadline");
                        description = secondBox[0];
                        time = secondBox[1].trim();
                        day = time.split("/")[0];
                        String timeInString = parser.timeFormatter(time , day);
                        runDeadline(description , timeInString);
                        break;

                    case "event":
                        newString = firstBox[1];
                        secondBox = parser.secondFilter(newString , "event");
                        description = secondBox[0];
                        time = secondBox[1];
                        day = time.split("/")[0];
                        timeInString = parser.timeFormatter(time , day);
                        runEvent(description , timeInString);
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

    public void runDone(String userInput) {
        int index = Integer.parseInt(userInput.split(" ")[1]);
        Task chosenTask = taskList.get(index - 1);
        chosenTask.markAsDone();
        ui.showDone(taskList , index);
        writeToFile();
    }

    public void runDelete(String userInput) {
        int index = Integer.parseInt(userInput.split(" ")[1]);
        ui.showDelete(taskList , index);
        taskList.remove(index - 1);
        ui.showTaskListSize(taskList);
        writeToFile();
    }

    public void runFind(String userInput) {
        int index = 0 ;
        ArrayList<Task> searchResults = new ArrayList<>();
        String keyword = userInput.split(" ")[1];
        for (Task task : taskList) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                searchResults.add(task);
            }
        }
        ui.showMatchTasks(taskList , index);
    }

    public void runToDo(String description) {
        Task toDoTask = new ToDo(description);
        taskList.add(toDoTask);
        ui.showAddTodoTask(taskList , toDoTask);
        writeToFile();
    }

    public void runDeadline(String description , String time) {

        Task deadlineTask = new Deadline(description , time);
        taskList.add(deadlineTask);
        ui.showAddDeadlineTask(taskList , deadlineTask);
        writeToFile();
    }

    public void runEvent(String description , String time) {
        Task eventTask = new Event(description , time);
        taskList.add(eventTask);
        ui.showAddEventTask(taskList , eventTask);
        writeToFile();
    }

    public static void main(String[] args) {
        new Duke().run();

    }
}
