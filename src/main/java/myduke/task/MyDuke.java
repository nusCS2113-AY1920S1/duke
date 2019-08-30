package myduke.task;
import myduke.Commands;

import java.util.ArrayList;
import java.util.Scanner;

public class MyDuke {
    ArrayList<Task> taskList = new ArrayList<>();
    Scanner inputs = new Scanner(System.in);

    private static final String WHITESPACE = " ";

    public void runDuke() {
        String newString;
        String[] secondBox;
        String description;
        String time;

        String userInput = inputs.nextLine().trim();
        while(!userInput.equals("bye")) {
            String[] firstBox = firstFilter(userInput); // return a string array with commands and description
//            System.out.println("commands = " + firstBox[0]);
//            System.out.println("description = " + firstBox[1]);

            String firstWord = firstBox[0];
            Commands commands = Commands.valueOf(firstWord);

            switch (commands) {
                case list:
                    showList();
                    break;
                case done:
                    runDone(userInput);
                    break;
                case todo:
                    description = firstBox[1];
                    runToDo(description);
                    break;
                case deadline:
                    newString = firstBox[1];
                    secondBox = secondFilter(newString , commands);
                    description = secondBox[0];
                    time = secondBox[1];
                    runDeadline(description , time);
                    break;
                case event:
                    newString = firstBox[1];
                    secondBox = secondFilter(newString , commands);
                    description = secondBox[0];
                    time = secondBox[1];
                    runEvent(description , time);
                    break;
            }
            userInput = inputs.nextLine().trim();

        }
        System.out.println("Bye. Hope to see you again soon!");
    }

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

    public String[] secondFilter(String newString , Commands commands) {
        String[] filter;
        if (commands.equals(Commands.deadline)) {
            filter = newString.split(" /by ");
        }else{
            filter = newString.split(" /at ");
        }
        return filter;
    }

    public void showList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

}
