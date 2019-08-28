import myduke.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ArrayList<Task> taskBox = new ArrayList<Task>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while(true){
            String description = scan.nextLine();
            if (description.equals("bye")){
                break;
            }else if(description.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskBox.size(); i++) {
                    System.out.println((i + 1) + ". " + taskBox.get(i));
                }

            }else if(description.startsWith("done")){
                int index = Integer.parseInt(description.split(" ")[1]);
                Task target = taskBox.get(index - 1);
                target.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskBox.get(index - 1));
            }else{
                System.out.println("added: " + description);
                taskBox.add(new Task(description));
            }


        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
