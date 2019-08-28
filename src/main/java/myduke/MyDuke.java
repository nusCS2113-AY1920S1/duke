package myduke;

import java.util.ArrayList;
import java.util.Scanner;

public class MyDuke {
    Scanner scan = new Scanner(System.in);
    ArrayList<Task> taskBox = new ArrayList<Task>();

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

    public void run() {
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
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskBox.get(index - 1));

            }else if(inputs.startsWith("todo")){
                String description = inputs.substring(5);
                runToDo(description);

            }else if(inputs.startsWith("deadline")){
                String info = inputs.substring(9);
                String description = info.split("/by")[0]; //Description
                String by = info.split("/by")[1]; // Day
                runDeadline(description , by);

            }else if(inputs.startsWith("event")){
                String info = inputs.substring(6);
                String description = info.split("/at")[0]; //Description
                String at = info.split("/at")[1]; // Day
                runEvent(description , at);

            }else{
                this.echo(inputs);
            }
        }
    }

    public void runToDo(String description) {
        System.out.println("Got it. I've added this task:");
        Task toDoTask = new ToDo(description);
        taskBox.add(toDoTask);
        System.out.println(toDoTask);
        System.out.println("Now you have " + taskBox.size() + " tasks in the list.");
    }

    public void runDeadline(String description , String by) {
        System.out.println("Got it. I've added this task:");
        Task deadlineTask = new Deadline(description , by);
        taskBox.add(deadlineTask);
        System.out.println(deadlineTask);
        System.out.println("Now you have " + taskBox.size() + " tasks in the list.");

    }

    public void runEvent(String description , String at) {
        System.out.println("Got it. I've added this task:");
        Task eventTask = new Event(description , at);
        taskBox.add(eventTask);
        System.out.println(eventTask);
        System.out.println("Now you have " + taskBox.size() + " tasks in the list.");
    }

}
