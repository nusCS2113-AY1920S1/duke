import java.io.Serializable;

//this is the Task class
public class Task implements Serializable{
    private boolean status;
    private String name;
    private static final long serialVersionUID = 1L;
    public Task(boolean to_add_status, String name_to_add){
        status = to_add_status;
        name = name_to_add;
    }
    public void change_status(boolean to_change_status){
        status  = to_change_status;
    }
    public void change_task(String to_change_task){
        name = to_change_task;
    }
    public boolean get_status(){
        return status;
    }
    public String get_name(){
        return name;
    }
    public String get_type(){ 
        return "T";
    }
    public String toString() {
        
        if(status == true){ 
            String name_1 =  "[✓] "+get_name().trim(); 
            return name_1; 
        }
        else{ 
            String name_1 =  "[✗] "+get_name().trim(); 
            return name_1; 
        }
    }
    public String get_attrib(){ 
        String type; 
        if(status==true){ 
            type = "1";
        }
        else{ 
            type = "0";
        }
        return "^"+type+"^"+name ; 
    }
}
