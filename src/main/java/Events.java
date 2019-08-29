import java.time.LocalDateTime;

public class Events extends Task {

    protected String end;
    protected LocalDateTime end_date; 
    protected boolean date = false; 
    protected String start; 
    protected LocalDateTime start_date; 
    @Override
    public String get_type() {
        return "E";
    }
    public Events(boolean to_add_status,String description, LocalDateTime start_d,LocalDateTime end_d, String command_start, String command_end) {
        super(to_add_status,description);
        end_date = end_d; 
        start_date = start_d; 
        date = true; 
        this.end = command_end.trim();
        this.start = command_start.trim();
    }
    public Events(boolean to_add_status,String description, String start, String end) {
        super(to_add_status,description);
        this.end = end;
        this.start = start; 
    }  
    @Override
    public String toString() {
        if(date == false){ 
            return "[E]" + super.toString() + " (at: " + start.trim() + " to " + end.trim() + ")";
        }
        else{ 
            return "[E]" + super.toString() + " (at: " + start_date + " to "+end_date+ ")";
        }

    }
    @Override
    public String get_attrib(){ 
        //System.out.println(super.get_attrib());
        return "E" + super.get_attrib()+"^"+start.trim()+"^"+end.trim();   
    }
    
}