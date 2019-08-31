package myduke;

import myduke.task.Deadline;
import myduke.task.Event;
import myduke.task.Task;
import myduke.task.ToDo;

import java.util.ArrayList;

public class DukeTools {

    ArrayList<Task> taskList = new ArrayList<>();

    public void runDone(String userInput) {
        int index = Integer.parseInt(userInput.split(" ")[1]);
        Task chosenTask = taskList.get(index - 1);
        chosenTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(index - 1));
    }

    public void runToDo(String description) {
        System.out.println("Got it. I've added this task:");
        Task toDoTask = new ToDo(description);
        taskList.add(toDoTask);
        System.out.println(toDoTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void runDeadline(String description , String time) {
        System.out.println("Got it. I've added this task:");
        Task deadlineTask = new Deadline(description , time);
        taskList.add(deadlineTask);
        System.out.println(deadlineTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void runEvent(String description , String time) {
        System.out.println("Got it. I've added this task:");
        Task eventTask = new Event(description , time);
        taskList.add(eventTask);
        System.out.println(eventTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void showList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    private static final String WHITESPACE = " ";

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
}
