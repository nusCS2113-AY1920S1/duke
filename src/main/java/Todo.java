public class Todo extends Task {
    public Todo(String description){
        super(description);
        System.out.println("Got it. I've added this task:\n  " +
                this.toList() +
        "\nNow you have "+ this.numTasksCreated() + " tasks in the list.");
    }

    @Override
    public String toList(){
        return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
