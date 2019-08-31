package myduke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }
}
