package myduke;
public class MyDuke {
    private String description;

    public void greet(){
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void echo(String description){
        System.out.println("added: " + description);
    }

    public void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }

}
