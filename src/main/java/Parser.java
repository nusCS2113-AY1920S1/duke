import java.util.Scanner;

public class Parser {
    public static String deadline = "\\s*/by\\s*";
    public static String event = "\\s*/at\\s*";
    public static String taskSeparator = "\\s*\\|\\s*";
    public static String newLine = "\n";
    public Parser(){}

    public static void parse(String line) throws DukeException {
        Scanner temp = new Scanner(line);
        if(!temp.hasNext()){
            throw new DukeException("Empty Command!");
        }
        String command = temp.next();
        if (command.matches("list|bye")) {
            if (temp.hasNextLine()) {
                throw new DukeException("List should not have any other arguments (whitespace acceptable");
            }
            else{
                //for the future
            }
        }
        else if (command.matches("todo|deadline|event|done|delete|find")) {
            if(!temp.hasNextLine())
                throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
            String input = temp.nextLine();
            input = input.trim();
            //System.out.println("input is" + input + "\nCommand is" + command);

            if(input.isBlank())
            {
                throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
            }
            else {
                //for the future
            }
        }
        else
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
