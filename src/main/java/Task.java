import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Task {
    protected String description;
    protected boolean isDone;
    //protected String dueDate;
    protected Date dueDate;
    private static DateFormat dateFormatter = new SimpleDateFormat("E");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(){
    }
    public void readDate(String date) throws DukeException {
        try {
            this.dueDate = dateFormatter.parse(date);
        }
        catch(ParseException e)
        {
            throw new DukeException("Please use DDD format for date");
        }
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public void markDone(){
        if(this.isDone){
            System.out.println("But good sir, this task is already done!");
        }
        else
            this.isDone = true;
    }
    public boolean checkCompletion() {return this.isDone;}
    public String getType(){ return "G";}
    public String getDescription() {
        return this.description;
    }
    public String getDueDate() { return this.dateFormatter.format(this.dueDate); }

    public String toList(){
        return "[?][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
