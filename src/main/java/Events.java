public class Events extends Task {

    protected String by;

    public Events(boolean to_add_status,String description, String by) {
        super(to_add_status,description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by.trim() + ")";
    }
    @Override
    public String get_attrib(){ 
        //System.out.println(super.get_attrib());
        return "E" + super.get_attrib()+"^"+by.trim();   
    }
}