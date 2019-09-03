import java.io.*;
import java.util.ArrayList;

public class TaskList {
    public TaskList() {}
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    private ArrayList<Task> taskList = new ArrayList<>();
    Ui ui = new Ui();

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
        ui.showMatchTasks(searchResults , index);
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









}
