import java.util.ArrayList;

public class TaskList {
    public TaskList() {}
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    private ArrayList<Task> taskList = new ArrayList<>();
    Ui ui = new Ui();
    //Storage storage = new Storage();

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void runDone(String userInput) {
        int index = Integer.parseInt(userInput.split(" ")[1]);
        Task chosenTask = taskList.get(index - 1);
        chosenTask.markAsDone();
        ui.showDone(taskList , index);
        //storage.save();
    }

    public void runDelete(String userInput) {
        int index = Integer.parseInt(userInput.split(" ")[1]);
        ui.showDelete(taskList , index);
        taskList.remove(index - 1);
        ui.showTaskListSize(taskList);
        //storage.save();
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
        //storage.save();
    }

    public void runDeadline(String description , String time) {

        Task deadlineTask = new Deadline(description , time);
        taskList.add(deadlineTask);
        ui.showAddDeadlineTask(taskList , deadlineTask);
        //storage.save();
    }

    public void runEvent(String description , String time) {
        Task eventTask = new Event(description , time);
        taskList.add(eventTask);
        ui.showAddEventTask(taskList , eventTask);
        //storage.save();
    }









}
