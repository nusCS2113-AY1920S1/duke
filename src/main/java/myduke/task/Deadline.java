package myduke.task;

public class Deadline extends Task {
    protected String time;

    public Deadline(String description , String time) {
        super(description);
        this.time = time;
//        this.type = "D";
//        this.time = "";
    }
//
//    public void setTime(String time) {
//        this.time = " (by: " + time + ")";
//    }

    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " + time + ")";
    }
}
