package myduke.task;

public class Event extends Task {
    protected String time;

    public Event(String description , String time) {
        super(description);
        this.time = time;
    }

//    public void setTime (String time) {
//        this.time = " (at: " + time + ")";
//    }

    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description + " (by: " + time + ")";
    }


}
