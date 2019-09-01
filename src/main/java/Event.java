public class Event extends Task {
    private String type = "E";

    public Event(String description) throws DukeException {
        String[] split = description.split("\\s+/at\\s+");
        if (split.length < 2) {
            throw new DukeException("Please use /at to indicate date");
        }
        else if (split.length > 2) {
            throw new DukeException("Too many /at in String");
        }
        else {
            this.description = split[0];
            this.readDate(split[1]);
            this.isDone = false;
            counter++;
            System.out.println("Got it. I've added this task:\n  " +
                    this.toList() +
                    "\nNow you have " + this.numTasksCreated() + " tasks in the list.");
        }
    }

    public Event(String bool, String description, String dueDate) throws DukeException {
        this.description = description;
        this.readDate(dueDate);
        this.isDone = (1 == Integer.parseInt(bool));
        counter++;
    }

    @Override
    public String toList(){
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() +
                " (by: " + this.getDueDate() + ")";
    }
    @Override
    public String getType(){ return "E";}
}
