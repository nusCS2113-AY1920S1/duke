import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Task {
    String description;
    boolean isDone;
    //protected String dueDate;
    private Date dueDate = null;
    private static DateFormat dateFormatter = new SimpleDateFormat("E");

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    Task(){
    }
    void readDate(String date) throws DukeException {
        try {
            this.dueDate = dateFormatter.parse(date);
        }
        catch(ParseException e)
        {
            throw new DukeException("Please use DDD format for date");
        }
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    void markDone() throws DukeException {
        if(this.isDone){
            throw new DukeException("But good sir, this task is already done!");
        }
        else
            this.isDone = true;
    }
    boolean checkCompletion() {return this.isDone;}
    public String getType(){ return "G";}
    String getDescription() {
        return this.description;
    }
    String getDueDate() {
        if(this.dueDate != null)
            return dateFormatter.format(this.dueDate);
        else
            return ""; }

    public String toList(){
        return "[?][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
