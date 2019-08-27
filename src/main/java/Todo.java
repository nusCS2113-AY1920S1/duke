public class Todo extends Task {

    protected String by;

    public Todo(boolean to_add_status,String description) {
        super(to_add_status,description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString().trim() ;
    }
    
}