import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner scan  = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        while(scan.hasNext()){
            String input = scan.nextLine();
            if (input.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            }

            else if(input.equals("list")){
                for(int i=0; i < taskList.size(); i+=1){
                    System.out.println("Here are the tasks in your list: ");
                    System.out.println(i+1 + "." + taskList.get(i).toString());
                }

            }

            else if(input.split(" ")[0].equals("done")){
                int taskNumber = Integer.parseInt(input.split(" ")[1]) -1;
                taskList.get(taskNumber).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + taskList.get(taskNumber).toString());
            }

            else{
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println("added: " + input);
            }
        }
    }
}
