import java.util.Scanner;
import java.io.*;

public class Duke {
    public static class Task implements Serializable{
        private static final long SerialVersionUID = 20L;
        protected String description;
        protected boolean isDone;
        protected String typeEvent;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[✓]" : "[X]"); //return tick or X symbols
        }
        public String toString(){
            return getStatusIcon() + description;
        }

        //...
    }

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class ToDo extends Task {

        protected String by;

        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Event extends Task {

        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }
//    public static class SerializeArray{
//        public static void saveArray(Task[] arr)throws Exception //writes the array of Person to a file "Persons.ser"
//        {
//            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Persons.ser"));
//            os.writeObject(arr);
//            os.close();
//        }
//        public static Task[] loadArray()throws Exception //Reads the array of Persons back from file.
//        {
//            ObjectInputStream oin = new ObjectInputStream(new FileInputStream("Persons.ser"));
//            Task[] arr = (Task[]) oin.readObject();
//            oin.close();
//            return arr;
//        }
//    }

    public void saveArray(String filename, Task[] output_arr) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(output_arr);
            out.flush();
            out.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public Task[] loadArray(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fis);
            Task[] load_arr = (Task[])in.readObject();
            in.close();
            return load_arr;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

//changed public static void to public void
    public static void main(String[] args) throws Exception {
        Task load_arr[] = new Task[100];
        try {
            FileInputStream fis = new FileInputStream("list.ser");
            ObjectInputStream in = new ObjectInputStream(fis);
            load_arr = (Task[])in.readObject();
            in.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        String input;
        String deadlineArr[];
        String eventArr[];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, I'm Duke! What can I do for you today?");
        input = scanner.nextLine();
        String inputArr[] = input.split(" ", 2);
        Task classArr[] = new Task[100];
        int k = 0;
        if(load_arr != null){
            for(k = 0; k < load_arr.length; k++){
                classArr[k] = load_arr[k];
            }
            for(int b = 0; b < 100; b ++){
                classArr[b] = new Task("");
            }
        }
        for(int a = 0; a < 100; a++){
            classArr[a] = new Task("");
        }
        int num = 0;
        int i = 0;

        while(!input.equals("bye")) {
            if(!inputArr[0].equals("todo") && !inputArr[0].equals("deadline") && !inputArr[0].equals("event") && !inputArr[0].equals("done") && !inputArr[0].equals("list") ){
                System.out.println("☹ OOPS!!! I'm sorry but I don't know what that means.");
                input = scanner.nextLine();
                inputArr = input.split(" ", 2);
            }
            if(inputArr[0].equals("todo")){
                if(inputArr.length == 1){
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                }
                else{
                    classArr[i] = new ToDo(inputArr[1]);
                    classArr[i].isDone = false;
                    System.out.println("Got it! I've added this task: \n" + "[T]" + classArr[i].getStatusIcon() + " " + inputArr[1]);
                    System.out.println("You now have " + (i+1) + " tasks in the list.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                    i += 1;
                }
            }
            if(inputArr[0].equals("deadline")){
                if(inputArr.length == 1){
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                }
                else{
                    deadlineArr = inputArr[1].split("/by", 2);
                    classArr[i] = new Deadline(deadlineArr[0], deadlineArr[1]);
                    System.out.println("Got it! I've added this task: \n" + "[D]" + classArr[i].getStatusIcon() + " " + deadlineArr[0] + "(by: " + deadlineArr[1] + ")");
                    System.out.println("You now have " + (i+1) + " tasks in the list.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                    i += 1;
                }
            }
            if(inputArr[0].equals("event")){
                if(inputArr.length == 1){
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                }
                else{
                    eventArr = inputArr[1].split("/at", 2);
                    classArr[i] = new Event(eventArr[0], eventArr[1]);
                    System.out.println("Got it! I've added this task: \n" + "[E]" + classArr[i].getStatusIcon() + " " + eventArr[0] + "(at: " + eventArr[1] + ")");
                    System.out.println("You now have " + (i+1) + " tasks in the list.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                    i += 1;
                }
            }
            if(inputArr[0].equals("done")){
                if(inputArr.length == 1){
                    System.out.println("☹ OOPS!!! The description of a done task cannot be empty.");
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                }
                else{
                    num = Integer.parseInt(inputArr[1]) - 1;
                    classArr[num].isDone = true;
                    System.out.println("Nice! I've marked this task as done: \n" + classArr[num].getStatusIcon() + classArr[num].description);
                    input = scanner.nextLine();
                    inputArr = input.split(" ", 2);
                }
            }
            if(inputArr[0].equals("list")){
                System.out.println("Here are the tasks in your list:\n");
                for(int j=0;j<i;j++){
                    System.out.println(classArr[j].toString());
                    //+ classArr[j].getStatusIcon() + " " + classArr[j].description +
                    //(j+1) + ". " + classArr[j].getStatusIcon() + " " + classArr[j].description
                }
                input = scanner.nextLine();
                inputArr = input.split(" ", 2);
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream("list.ser");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(classArr);
            out.flush();
            out.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("Bye! Hope to see you again soon.");
    }
}
