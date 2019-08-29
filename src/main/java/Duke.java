import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        String command = new String();
        ArrayList<Task> list =new ArrayList<Task>();
        while(sc.hasNextLine() && !command.equalsIgnoreCase("bye"))
        {

            command = sc.next();
            boolean inputExists = true;
            String input = sc.nextLine();
            input = input.trim();
            try{
                if(input.equals("") && command.matches("todo|deadline|event|done")) {
                    throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
                }
            } catch (DukeException e) {
                System.out.println(e.getLocalizedMessage());
                inputExists = false;
            }
            if (command.equals("list")) {
            try {
                if(input.equals("")) {
                    if(list.size()== 0)
                    {
                        System.out.println("Whoops, there doesn't seem to be anything here at the moment");
                    }
                    else {
                        int counter = 1;
                        for (Task task : list) {
                            System.out.println(counter++ + ". " + task.toList());
                        }
                    }
                }
                else
                    throw new DukeException("List should not have any other arguments (whitespace acceptable");
            }
            catch(DukeException e)
            {
                System.out.println(e.getLocalizedMessage());
            }
        }
            else if(inputExists) {
                if (command.equals("todo")) {
                    list.add(new Todo(input));
                } else if (command.equals("deadline")) {
                    Deadline temp = null;
                    try{
                        temp = new Deadline(input);
                    }
                    catch(DukeException e){
                        System.out.println(e.getLocalizedMessage());
                    }
                    finally {
                        if(temp != null)
                            list.add(temp);
                    }
                } else if (command.equals("event")) {
                    Event temp = null;
                    try{
                        temp = new Event(input);
                    }
                    catch(DukeException e){
                        System.out.println(e.getLocalizedMessage());
                    }
                    finally {
                        if(temp != null)
                            list.add(temp);
                    }
                } else if (command.equals("done")) {
                    try{
                        int request = Integer.parseInt(input);
                        request -= 1;
                        if(request < 0 && request >= list.size())
                            throw new DukeException("☹ OOPS!!! This index is not within the list");
                        else {
                            list.get(request).markDone();
                            System.out.println("Nice! I've marked this task as done:\n" +
                                    "  " + list.get(request).toList());
                        }
                    }
                    catch(DukeException e)
                    {
                        System.out.println(e.getLocalizedMessage());
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("That is NOT a valid integer");
                    }
                }
                else {
                    try {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                }
            }
        }

        sc.close();
    }

}
