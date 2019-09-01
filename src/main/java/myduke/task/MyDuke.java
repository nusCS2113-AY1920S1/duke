package myduke.task;

import myduke.DukeException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MyDuke {
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




    public void runDuke() {
        loadFile();
        String newString;
        String[] secondBox;
        String description;
        String time;
        String userInput = inputs.nextLine();

        while(!userInput.equals("bye")) {
            String[] firstBox = firstFilter(userInput); // return a string array with commands and description
            String commands = firstBox[0];
            try {
                switch (commands) {
                    case "list":
                        showList();
                        break;
                    case "done":
                        runDone(userInput);
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
                        time = secondBox[1];
                        runDeadline(description , time);
                        break;
                    case "event":
                        newString = firstBox[1];
                        secondBox = secondFilter(newString , "event");
                        description = secondBox[0];
                        time = secondBox[1];
                        runEvent(description , time);
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }


            }catch(DukeException e) {
                e.printStackTrace();
            }finally {
                userInput = inputs.nextLine().trim();
            }

        }
//        for (Task t : taskList) {
//            System.out.println(t);
//        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void runDone(String userInput) {
        int index = Integer.parseInt(userInput.split(" ")[1]);
        Task chosenTask = taskList.get(index - 1);
        chosenTask.markAsDone();
        writeToFile();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(index - 1));
    }

    public void runToDo(String description) {
        System.out.println("Got it. I've added this task:");
        Task toDoTask = new ToDo(description);
        taskList.add(toDoTask);
        writeToFile();
        System.out.println(toDoTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void runDeadline(String description , String time) {
        System.out.println("Got it. I've added this task:");
        Task deadlineTask = new Deadline(description , time);
        taskList.add(deadlineTask);
        writeToFile();
        System.out.println(deadlineTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void runEvent(String description , String time) {
        System.out.println("Got it. I've added this task:");
        Task eventTask = new Event(description , time);
        taskList.add(eventTask);
        writeToFile();
        System.out.println(eventTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void showList() {
        //loadFile();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
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



}
