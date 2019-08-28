package myduke;
import java.util.ArrayList;
import java.util.Scanner;

public class MyDuke {
    Scanner scan = new Scanner(System.in);
    ArrayList<Task> taskBox = new ArrayList<Task>();

    public void greet(){
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void echo(String inputs){
        System.out.println("added: " + inputs);
        taskBox.add(new Task(inputs));
    }

    public void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void run(){
        while(true){
            String description = scan.nextLine();
            if (description.equals("bye")){
                this.exit();
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
                this.echo(description);
            }
        }
    }
}
