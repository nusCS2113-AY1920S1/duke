package myduke;

import java.util.ArrayList;

public class MyDuke {
    private String description;
    ArrayList<String> taskBox = new ArrayList<String>();

    public void greet(){
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void echo(String description){
        System.out.println("added: " + description);
        taskBox.add(description);

    }

    public void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showTaskBox() {
        int counter = 1;
        for(String list:taskBox){
            System.out.println(counter + "." + list);
            counter++;
        }
    }
}
