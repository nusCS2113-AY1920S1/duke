public class Deadline extends Task {
    private String dueDate;

    public Deadline(String description){
        super();
        String[] split = description.split(" /by | /at");
        this.description = split[0];
        this.dueDate = split[1];
        System.out.println("Got it. I've added this task:\n  " +
                this.toList() +
                "\nNow you have "+ this.numTasksCreated() + " tasks in the list.");
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toList(){
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() +
                " (by: " + this.getDueDate() + ")";
    }
}
