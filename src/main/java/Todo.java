public class Todo extends Task {
    protected String type = "T";
    public Todo(String description){
        super(description);
        System.out.println("Got it. I've added this task:\n  " +
                this.toList() +
        "\nNow you have "+ this.numTasksCreated() + " tasks in the list.");
    }
    public Todo(String bool, String description){
        super(description);
        this.isDone = (1 == Integer.parseInt(bool));
    }
    @Override
    public String toList(){
        return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
    }
    @Override
    public String getType(){ return "T";}
}
