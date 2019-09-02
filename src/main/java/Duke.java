import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    private Ui ui;

    public Duke() {
        ui = new Ui();
    }
    private static final String WHITESPACE = " ";
    Scanner inputs = new Scanner(System.in);
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
                String[] arr =  input.split(" \\| ");
                String taskType = arr[0].trim();
                String taskStatus = arr[1].trim();
                String taskDescription = arr[2].trim();
                String taskTime = "";

                if (taskType.equals("T")) {
                    Task todo = new ToDo(taskDescription);
                    if (taskStatus.equals("\u2713")) {
                        todo.markAsDone();
                    }
                    taskList.add(todo);
                }else if (taskType.equals("D")) {
                    taskTime = arr[3].trim();
                    Task deadline = new Deadline(taskDescription , taskTime);
                    if (taskStatus.equals("\u2713")) {
                        deadline.markAsDone();
                    }
                    taskList.add(deadline);
                }else {
                    taskTime = arr[3].trim();
                    Task event = new Event(taskDescription , taskTime);
                    if (taskStatus.equals("\u2713")) {
                        event.markAsDone();
                    }
                    taskList.add(event);
                }
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

        String userInput = inputs.nextLine();

        while(!userInput.equals("bye")) {
            String[] firstBox = firstFilter(userInput); // return a string array with commands and description
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
                        secondBox = secondFilter(newString , "deadline");
                        description = secondBox[0];
                        time = secondBox[1].trim();
                        day = time.split("/")[0];
                        String timeInString = timeFormatter(time , day);
                        runDeadline(description , timeInString);
                        break;

                    case "event":
                        newString = firstBox[1];
                        secondBox = secondFilter(newString , "event");
                        description = secondBox[0];
                        time = secondBox[1];
                        day = time.split("/")[0];
                        timeInString = timeFormatter(time , day);
                        runEvent(description , timeInString);
                        break;

                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }


            }catch(DukeException | ParseException e) {
                e.printStackTrace();
            }finally {
                userInput = inputs.nextLine().trim();
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

    public String[] firstFilter(String userInput) {
        String[] filter = userInput.split(" ");
        String firstWord = filter[0];
        StringBuilder str = new StringBuilder();
        for (int i = 1 ; i < filter.length ; i++) {
            str.append(filter[i]);
            str.append(WHITESPACE);
        }
        String[] result = {firstWord , str.toString().trim()};

        return result;
    }

    public String[] secondFilter(String newString , String firstWord) {
        String[] filter;
        if (firstWord.equals("deadline")) {
            filter = newString.split(" /by ");
        }else{
            filter = newString.split(" /at ");
        }
        return filter;
    }

    public String timeFormatter(String dateInString , String day) throws ParseException {
        DateFormat parser = new SimpleDateFormat("dd/M/yyyy HHmm");
        DateFormat stFormatter = new SimpleDateFormat("d'st of' MMMM yyyy , ha");
        DateFormat ndFormatter = new SimpleDateFormat("d'nd of' MMMM yyyy , ha");
        DateFormat rdFormatter = new SimpleDateFormat("d'rd of' MMMM yyyy , ha");
        DateFormat thFormatter = new SimpleDateFormat("d'th of' MMMM yyyy , ha");

        String output;

        Date convertedDate = parser.parse(dateInString);
        if (day.equals("1")){
            output = stFormatter.format(convertedDate);
        }else if (day.equals("2")) {
            output = ndFormatter.format(convertedDate);
        }else if (day.equals("3")) {
            output = rdFormatter.format(convertedDate);
        }else{
            output = thFormatter.format(convertedDate);
        }

        return output;
    }


    public static void main(String[] args) {
        new Duke().run();

    }
}
