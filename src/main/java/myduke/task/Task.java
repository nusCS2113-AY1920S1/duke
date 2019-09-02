package myduke.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task{

    protected String description;
    protected boolean isDone;
    protected String type;
    protected String time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
        this.time = "";
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTime() {
        return this.time;
    }

//    public void setTime(String dateInString) throws ParseException {
//        DateFormat parser = new SimpleDateFormat("dd/M/yyyy HHmm");
//        DateFormat formatter = new SimpleDateFormat("d'th of' MMM yyyy , hh a ");
//        Date convertedDate = parser.parse(dateInString);
//        String output = formatter.format(convertedDate);
//        this.time = output;
//    }


    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

