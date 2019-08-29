public class Event extends Task {
    private String dueDate;

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
            this.dueDate = split[1];
            this.isDone = false;
            counter++;
            System.out.println("Got it. I've added this task:\n  " +
                    this.toList() +
                    "\nNow you have " + this.numTasksCreated() + " tasks in the list.");
        }
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toList(){
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() +
                " (by: " + this.getDueDate() + ")";
    }
}
