public class Deadline extends Task {

    protected String by;

    public Deadline(boolean to_add_status,String description, String by) {
        super(to_add_status,description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString().trim() + " (by: " + by.trim() + ")";
    }
    
}