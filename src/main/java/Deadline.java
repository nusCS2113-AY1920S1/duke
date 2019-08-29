public class Deadline extends Task {
    protected String type = "D";

    public Deadline(String description) throws DukeException {
        String[] split = description.split("\\s+/by\\s+");
        if(split.length < 2)
        {
            throw new DukeException("Please use /by to indicate date");
        }
        else if(split.length > 2)
        {
            throw new DukeException("Too many /by in String");
        }
        else {
            this.description = split[0];
            this.dueDate = split[1];
            this.isDone = false;
            counter++;
            System.out.println("Got it. I've added this task:\n  " +
                    this.toList() +
                    "\nNow you have " + this.numTasksCreated() + " tasks in the list.");
        }
    }

    public Deadline(String bool, String description, String dueDate)
    {
        this.description = description;
        this.dueDate = dueDate;
        this.isDone = (1 == Integer.parseInt(bool));
        counter++;
    }

    @Override
    public String toList(){
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() +
                " (by: " + this.getDueDate() + ")";
    }
    @Override
    public String getType(){ return "D";}
}
