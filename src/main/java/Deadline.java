import java.time.LocalDateTime;

public class Deadline extends Task {

    protected String by;
    protected LocalDateTime date_1; 
    protected boolean date = false; 
    
    public Deadline(boolean to_add_status,String description, LocalDateTime by,String command) {
        super(to_add_status,description);
        date_1 = by; 
        date = true; 
        this.by = command.trim();
    }
    public Deadline(boolean to_add_status,String description, String by) {
        super(to_add_status,description);
        this.by = by;
    }
    @Override
    public String get_type() {
        return "D";
    }
    @Override
    public String toString() {
        if(date == false){ 
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
        else{ 
            return "[D]" + super.toString() + " (by: " + date_1 + ")";
        }
        //return "[D]" + super.toString().trim() + " (by: " + by.trim() + ")";
    }   
    @Override
    public String get_attrib(){ 
        return "D" + super.get_attrib()+"^"+by.trim(); 
    }
}