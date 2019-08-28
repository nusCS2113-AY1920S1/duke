package myduke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    protected Date date;

    public Deadline(String description) {
        super(description);
        this.type = "D";
    }

    public void parseTime(String time) throws ParseException {
        this.date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(time);
    }

    public String toString() {
        return ("[" + this.type + "][" + this.getStatusIcon() + "] " + this.description + " " + this.date).trim();
    }
}
