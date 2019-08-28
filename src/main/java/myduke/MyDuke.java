package myduke;

import myduke.task.Deadline;
import myduke.task.Event;
import myduke.task.Task;
import myduke.task.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyDuke {
    Scanner scan = new Scanner(System.in);
    ArrayList<Task> taskBox = new ArrayList<Task>();

    public void loadFile () {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            File file = new File("/Users/qianjie/Desktop/duke/src/main/java/data/duke.txt");
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String value = bufferedReader.readLine();

            while (value != null) {
                String description = value.substring(7);
                if (value.charAt(1) == 'T') {
                    ToDo todo = new ToDo(description);
                    if (value.charAt(4) == '\u2713') { // if it is a tick
                        todo.markAsDone();
                    }
                    taskBox.add(todo);
                }else if (value.charAt(1) == 'D') {
                    Deadline deadline = new Deadline(description);
                    if (value.charAt(4) == '\u2713') {
                        deadline.markAsDone();
                    }
                    taskBox.add(deadline);
                }else {
                    Event event = new Event(description);
                    if (value.charAt(4) == '\u2713') {
                        event.markAsDone();
                    }
                    taskBox.add(event);
                }
                value = bufferedReader.readLine();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if (fileReader != null){
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedReader != null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void writeToFile () {
        File file = new File("/Users/qianjie/Desktop/duke/src/main/java/data/duke.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            for (Task task : taskBox) {
                fileWriter.write(task.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public void greet(){
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void echo(String description){
        System.out.println("added: " + description);
        taskBox.add(new Task(description));
    }

    public void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void run() throws ParseException {
        loadFile();

        while(true){
            String inputs = scan.nextLine();
            if (inputs.equals("bye")){
                this.exit();
                break;
            }else if(inputs.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskBox.size(); i++) {
                    System.out.println((i + 1) + ". " + taskBox.get(i));
                }

            }else if(inputs.startsWith("done")){
                int index = Integer.parseInt(inputs.split(" ")[1]);
                Task target = taskBox.get(index - 1);
                target.markAsDone();
                writeToFile();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskBox.get(index - 1));

            }else if(inputs.startsWith("todo")){
                try {
                    String description = inputs;
                    if(inputs.length() < 5){
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }else{
                        description = inputs.substring(5);
                        runToDo(description);
                    }
                } catch (DukeException e) {
                    e.printStackTrace();
                }

            }else if(inputs.startsWith("deadline")){
                String newInput = inputs.substring(9);
                //System.out.println(newInput);
                String[] temp = newInput.split("/by ");
                String description = temp[0].trim();
                String time = temp[1].trim();
                runDeadline(description , time);

            }else if(inputs.startsWith("event")){
                String newInput = inputs.substring(6);
                String [] temp = newInput.split("/at");
                String description = temp[0].trim();
                String time = temp[1].trim();
                System.out.println(description);
                System.out.println(time);
                runEvent(description , time);

            }else{
                try{
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }catch(DukeException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void runToDo(String description){
        System.out.println("Got it. I've added this task:");
        Task toDoTask = new ToDo(description);
        taskBox.add(toDoTask);
        writeToFile();
        System.out.println(toDoTask);
        System.out.println("Now you have " + taskBox.size() + " tasks in the list.");

    }

    public void runDeadline(String description , String time) throws ParseException {
        System.out.println("Got it. I've added this task:");
        Deadline deadlineTask = new Deadline(description);
        deadlineTask.parseTime(time);
        taskBox.add(deadlineTask);
        writeToFile();
        System.out.println(deadlineTask);
        System.out.println("Now you have " + taskBox.size() + " tasks in the list.");

    }

    public void runEvent(String description , String time) throws ParseException {
        System.out.println("Got it. I've added this task:");
        Event eventTask = new Event(description);
        taskBox.add(eventTask);
        eventTask.parseTime(time);
        writeToFile();
        System.out.println(eventTask);
        System.out.println("Now you have " + taskBox.size() + " tasks in the list.");
    }

}
