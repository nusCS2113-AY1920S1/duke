import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> l1 ;
    
    public TaskList(){     
        l1 = new ArrayList<Task>(); 
    }    
    public TaskList(ArrayList<Task> temp ){
        //System.out.println("tasklist temp");
        l1 = temp;  
    }
    public void add_items(Task temp){ 
        //System.out.println("added items to list sucessfully");
        l1.add(temp);
    }
    public ArrayList<Task> get_list(){ 
        return l1;
    }
    public Task get_index(int num){
        return l1.get(num);
    }
    public int get_size(){ 
        return l1.size();
    }
    public void print_list(){ 
        int i = 0;
        for (Task temp : l1) {
            System.out.print(i+1);
            System.out.print(".");
            //boolean status = temp.get_status();
            System.out.println(temp);
            i +=1;
		}
    }
    public String find(String keyword){ 
        String message = "";
        int num = 1; 
        for (Task temp : l1) {
            //System.out.println("PRINTING " );
            //System.out.println(temp.get_attrib());
            
            if(temp.get_name().contains(keyword)){ 
                if(num==1){
                    message +="Here are the matching tasks in your list:\n";
                } 
                message +=String.valueOf(num)+"."+ temp+"\n";
                num+=1; 
            }
        }
        if(message == ""){ 
            return "No such keyword found!"; 
        }
        else{ 
            return message; 
        }
    }
    public void delete(int index) throws DukeException, Exception{
        if(index >= 1 && index <= l1.size()){ 
            
            l1.remove(index-1); 
                
        }
        else{ 
            throw new DukeException("Please give a valid task index to delete!");
        }
    }
    public void change_status(int index,boolean val){ 
        l1.get(index-1).change_status(val);
    }
    
} 