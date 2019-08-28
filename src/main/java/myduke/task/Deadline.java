package myduke.task;

public class Deadline extends Task {
    protected String time;

    public Deadline(String description) {
        super(description);
        this.type = "D";
        this.time = "";
    }

    public void setTime(String time) {
        this.time = " (by: " + time + ")";
    }

    public String toString() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description + this.time;
    }
}
