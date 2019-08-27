import java.util.Scanner;

public class Duke {

    public static class Task {
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

//changed public static void to public void
    public static void main(String[] args) {

        String input;
        String deadlineArr[];
        String eventArr[];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, I'm Duke! What can I do for you today?");
        input = scanner.nextLine();
        String inputArr[] = input.split(" ", 2);
        Task classArr[] = new Task[100];
        for(int a = 0; a < 100; a++){
            classArr[a] = new Task("");
        }
        int num = 0;
        int i = 0;

        while(!input.equals("bye")) {
            //System.out.println(input);
            if(inputArr[0].equals("todo")){
                classArr[i] = new ToDo(inputArr[1]);
                classArr[i].isDone = false;
                System.out.println("Got it! I've added this task: \n" + "[T]" + classArr[i].getStatusIcon() + " " + inputArr[1]);
                System.out.println("You now have " + (i+1) + " tasks in the list.");
                input = scanner.nextLine();
                inputArr = input.split(" ", 2);
                i += 1;
            }
            if(inputArr[0].equals("deadline")){
                deadlineArr = inputArr[1].split("/by", 2);
                classArr[i] = new Deadline(deadlineArr[0], deadlineArr[1]);
                System.out.println("Got it! I've added this task: \n" + "[D]" + classArr[i].getStatusIcon() + " " + deadlineArr[0] + "(by: " + deadlineArr[1] + ")");
                System.out.println("You now have " + (i+1) + " tasks in the list.");
                input = scanner.nextLine();
                inputArr = input.split(" ", 2);
                i += 1;
            }
            if(inputArr[0].equals("event")){
                eventArr = inputArr[1].split("/at", 2);
                classArr[i] = new Event(eventArr[0], eventArr[1]);
                System.out.println("Got it! I've added this task: \n" + "[E]" + classArr[i].getStatusIcon() + " " + eventArr[0] + "(at: " + eventArr[1] + ")");
                System.out.println("You now have " + (i+1) + " tasks in the list.");
                input = scanner.nextLine();
                inputArr = input.split(" ", 2);
                i += 1;
            }
            if(inputArr[0].equals("done")){
                num = Integer.parseInt(inputArr[1]) - 1;
                classArr[num].isDone = true;
                System.out.println("Nice! I've marked this task as done: \n" + classArr[num].getStatusIcon() + classArr[num].description);
                input = scanner.nextLine();
                inputArr = input.split(" ", 2);
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

            System.out.println("Bye! See you again soon!");

    }
}
