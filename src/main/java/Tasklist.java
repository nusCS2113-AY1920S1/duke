import java.io.*;
import java.net.URL;
import java.util.ArrayList;

class Tasklist {
    private ArrayList<Task> list = new ArrayList<>();
    Tasklist(){}
    Tasklist(String input) throws DukeException {
        String[] splitTasks = input.split(Parser.newLine);
        try {
            for (int i = 0; i < splitTasks.length; i++) {
                String[] split = splitTasks[i].split(Parser.taskSeparator);
                switch(split[0]) {
                    case "T":
                        list.add(new Todo(split[1], split[2]));
                        break;
                    case "D":
                        list.add(new Deadline(split[1], split[2], split[3]));
                        break;
                    case "E":
                        list.add(new Event(split[1], split[2], split[3]));
                        break;
                    default:
                        throw new DukeException((i+1) + "has incorrect task format.");
                }
            }
        }
        catch(DukeException e)
        {
            list.clear();
            throw new DukeException("Issues encountered when creating tasks, initializing empty list.");
        }
    }

    long size(){
        return list.size();
    }
    private boolean isOutOfRange(int request){
        return ((request < 0) || (request >= this.size()));
    }

    void markDone(String input) throws DukeException {
        try {
            int request = Integer.parseInt(input);
            request-=1;
            if(isOutOfRange(request)){
                throw new DukeException("The index was not found within range");
            }
            else {
                this.list.get(request).markDone();
                System.out.println("Nice! I've marked this task as done:\n" +
                        "  " + this.list.get(request).toList());
            }
        }
        catch(DukeException e)
        {
            throw new DukeException(e.getLocalizedMessage());
        }
        catch (NumberFormatException e) {
            throw new DukeException("That is NOT a valid integer");
        }
    }
    void banishDelete(String input) throws DukeException {
        try {
            int request = Integer.parseInt(input);
            request-=1;
            if(isOutOfRange(request)){
                throw new DukeException("The index was not found within range");
            }
            else {
                System.out.println("Noted. I've removed this task:\n" +
                        "  " + list.get(request).toList());
                this.list.remove(request);
                System.out.println("Now you have " + this.list.size() + " tasks in the list.");
            }
        }
        catch(DukeException e)
        {
            throw new DukeException(e.getLocalizedMessage());
        }
        catch (NumberFormatException e) {
            throw new DukeException("That is NOT a valid integer");
        }
    }
    Task get(int index) throws DukeException {
        if(!this.isOutOfRange(index))
            return this.list.get(index);
        else
            throw new DukeException("Requested Task not found within list");
    }
    void add(String type, String input) throws DukeException {
        Task temp;
        try {
            switch (type) {
                case "todo":
                    temp = new Todo(input);
                    break;
                case "deadline":
                    temp = new Deadline(input);
                    break;
                case "event":
                    temp = new Event(input);
                    break;
                default:
                    throw new DukeException("What the Hell happened here?\n"+
                            "Command passed successfully to tasklist.add, not found in any case");
            }
                this.list.add(temp);
                System.out.println("Got it. I've added this task:\n  " +
                        temp.toList() + "\nNow you have "+ this.size() + " tasks in the list.");
        }
        catch (DukeException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }
    void find(String input) throws DukeException {
        ArrayList<Integer> FoundIndex = new ArrayList<>();
        for (int i = 0; i < this.size(); i++)
        {
            if (this.get(i).getDescription().contains(input) || this.get(i).getDueDate().contains(input)) {

                FoundIndex.add(i);
            }
        }
        if(FoundIndex.isEmpty())
            System.out.println("There are no matching tasks in the list");
        else
        {
            System.out.println("Here are the matching tasks in your list:");
            for (Integer foundIndex : FoundIndex) {
                System.out.println((foundIndex + 1) + ". " + this.get(foundIndex).toList());
            }
        }
    }
    void print() {
        if (this.size() == 0) {
            System.out.println("Whoops, there doesn't seem to be anything here at the moment");
        } else {
            int counter = 1;
            for (Task task : list) {
                System.out.println(counter++ + ". " + task.toList());
            }
        }
    }
}
