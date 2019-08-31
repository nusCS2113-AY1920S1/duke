import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Duke {
    /**
     * Why checkstyle liddat.
     */
    
    static void addToFile(String directory, int position, String type, String content, String content2) {
        String line = "____________________________________________________________\n";
        try (BufferedWriter update = new BufferedWriter(new FileWriter(directory, true))) {
            String fileContent = position + " | false | " + type + " | " + content + " | " + content2 + "\n";
            update.append(fileContent);
            update.close();
            
        }
        catch (IOException e) {
            System.out.println(line + "Something's wrong with saving the file!\n" + line);
        }
    }
    
    /*static void modifyFile(String directory, String oldstring, String newstring) {
        try {
                BufferedReader reader = new BufferedReader(new FileReader(directory));
                String oldContent = "";
                //Reading all the lines of input text file into oldContent

                String line = reader.readLine();

                while (line != null) {
                    oldContent = oldstring + line + System.lineSeparator();
                    line = reader.readLine();
                }

                String newContent = oldContent.replace(oldstring, newstring);
                BufferedWriter update = new BufferedWriter(new FileWriter(directory));
                update.write(newContent);
                try
                {
                    reader.close();
                    update.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("File is missing??");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
*/
    static void modifyFile(String filePath, String oldString, String newString) {
        File fileToBeModified = new File(filePath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        
        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            
            //Reading all the lines of input text file into oldContent
            
            String line = reader.readLine();
            
            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            
            //Replacing oldString with newString in the oldContent
            
            String newContent = oldContent.replace(oldString, newString);
            
            //Rewriting the input text file with newContent
            
            writer = new FileWriter(fileToBeModified);
            
            writer.write(newContent);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                //Closing the resources
                
                reader.close();
                
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        String directory = "C:\\Users\\GY\\Downloads\\CS2113T\\duke\\data\\duke.txt";
        Scanner input = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        dateFormat.setLenient(false);
        String line = "____________________________________________________________\n";
        ArrayList<Task> tasklist = new ArrayList<Task>();
        
        System.out.println(line + " Hello! I'm Duke\n" + " What can I do for you?\n" + line);
        
        
        while (true) {
            String typing = input.nextLine();
            int indexOfSpace = typing.indexOf(" ");
            String instruction = (indexOfSpace != -1) ? typing.substring(0, indexOfSpace) : typing;
            
            if (instruction.equals("todo")) {
                if (indexOfSpace == -1) {
                    System.out.println(line + "☹ OOPS!!! The description of a todo cannot be empty.\n" + line);
                    continue;
                }
                Task t = new Todo(typing.substring(indexOfSpace + 1));
                tasklist.add(t);
                System.out.println(line + "Got it. I've added this task: \n" + "  " + t);
                System.out.println("Now you have " + tasklist.size() + " task(s) in the list.\n" + line);
                
                addToFile(directory, tasklist.size(), "T", typing.substring(indexOfSpace + 1), null);
            }
            else if (instruction.equals("deadline")) {
                try {
                    int indexOfSlash = typing.indexOf("/by");
                    if (indexOfSlash == -1)
                    {
                        System.out.println(line + "☹ OOPS!!! Please use /by\n" + line);
                        continue;
                    }
                    Date date = dateFormat.parse(typing.substring(indexOfSlash + 4));
                    if (!typing.substring(indexOfSlash + 1, indexOfSlash + 3).equals("by")) {
                        System.out.println(line + "☹ OOPS!!! Please enter a valid deadline input.\n" + line);
                        continue;
                    }
                    Task t = new Deadline(typing.substring(indexOfSpace + 1, indexOfSlash), typing.substring(indexOfSlash + 4));
                    tasklist.add(t);
                    System.out.println(line + "Got it. I've added this task: \n" + t);
                    System.out.println("Now you have " + tasklist.size() + " task(s) in the list.\n" + line);
                    
                    addToFile(directory, tasklist.size(), "D", typing.substring(indexOfSpace + 1, indexOfSlash), typing.substring(indexOfSlash + 4));
                }
                catch (StringIndexOutOfBoundsException e) {
                    System.out.println(line + "☹ OOPS!!! Please enter a valid deadline input.\n" + line);
                }
                catch (ParseException e) {
                    System.out.println(line + "☹ OOPS!!! Please enter a valid date! dd-MMM-yyyy HH:mm:ss \n" + line);
                }
            }
            else if (instruction.equals("event")) {
                try {
                    int indexOfSlash = typing.indexOf("/at");
                    if (indexOfSlash == -1)
                    {
                        System.out.println(line + "☹ OOPS!!! Please use /at\n" + line);
                        continue;
                    }
                    Date date = dateFormat.parse(typing.substring(indexOfSlash + 4));
                    Task t = new Event(typing.substring(indexOfSpace + 1, indexOfSlash), typing.substring(indexOfSlash + 4));
                    tasklist.add(t);
                    System.out.println(line + "Got it. I've added this task: \n" + t);
                    System.out.println("Now you have " + tasklist.size() + " task(s) in the list.\n" + line);
    
                    addToFile(directory, tasklist.size(), "E", typing.substring(indexOfSpace + 1, indexOfSlash), typing.substring(indexOfSlash + 4));
    
                }
                catch (StringIndexOutOfBoundsException e) {
                    System.out.println(line + "☹ OOPS!!! Please enter a valid event input.\n" + line);
                }
                catch (ParseException e) {
                    System.out.println(line + "☹ OOPS!!! Please enter a valid date! dd-MMM-yyyy HH:mm:ss \n" + line);
                }
            }
            else if (instruction.equals("done")) {
                try {
                    int temp = Integer.parseInt(typing.substring(5));
                    tasklist.get(temp - 1).done();
                    System.out.print(line);
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(tasklist.get(temp - 1) + "\n" + line);
                }
                catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    System.out.println(line + "Which task?\n" + line);
                    continue;
                }
                
                modifyFile(directory, typing.substring(5) + " | false | ", typing.substring(5) + " | true | ");
            }
            else if (instruction.equals("list")) {
                System.out.println(line + "Here are the tasks in your list: ");
                for (int i = 0; i < tasklist.size(); i += 1) {
                    System.out.print((i + 1) + ". ");
                    System.out.println(tasklist.get(i));
                }
                System.out.print(line);
            }
            else if (instruction.equals("bye")) {
                break;
            }
            else {
                System.out.println(line + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
            }
            
        }
        
        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
