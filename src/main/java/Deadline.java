public class Deadline extends Task {
    protected String type = "D";

    public Deadline(String description) throws DukeException {
        String[] split = description.split(Parser.deadline);
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
            this.readDate(split[1]);
            this.isDone = false;
        }
    }

    public Deadline(String bool, String description, String dueDate) throws DukeException {
        this.description = description;
        this.readDate(dueDate);
        this.isDone = (1 == Integer.parseInt(bool));
    }

    @Override
    public String toList(){
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() +
                " (by: " + this.getDueDate() + ")";
    }
    @Override
    public String getType(){ return "D";}
}
