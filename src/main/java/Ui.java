import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readUserInput() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void showTaskList(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showDone(ArrayList<Task> taskList , int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(index - 1));
    }

    public void showDelete(ArrayList<Task> taskList , int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(index - 1));
    }

    public void showTaskListSize(ArrayList<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void showMatchTasks(ArrayList<Task> searchResults , int index) {
        System.out.println("Here are the matching tasks in your list:");
        for (Task results : searchResults) {
            index++;
            System.out.println(index + "." + results);
        }
    }

    public void showAddTodoTask(ArrayList<Task> taskList , Task todoTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(todoTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void showAddDeadlineTask(ArrayList<Task> taskList , Task deadlineTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(deadlineTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void showAddEventTask(ArrayList<Task> taskList , Task eventTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(eventTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void showError (String message) {
        System.out.println("☹ OOPS!!!" + message);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

}
