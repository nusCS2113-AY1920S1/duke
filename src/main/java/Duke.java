import java.util.Scanner;

public class Duke {

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[$]" : "[@]"); //return tick or X symbols
        }

        //...
    }
//changed public static void to public void
    public static void main(String[] args) {

        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, I'm Duke! What can I do for you today?");
        input = scanner.nextLine();
        String inputArr[] = input.split(" ", 2);
        Task classArr[] = new Task[100];
        for(int a = 0; a < 100; a++){
            classArr[a] = new Task("");
        }
        int num = 0;
        //String arr[] = new String[100];
        int i = 0;
        while(!input.equals("bye")) {
            //System.out.println(input);
            if(!inputArr[0].equals("done") && !inputArr[0].equals("list")){
                Task t = new Task(input);
                t.isDone = false;
                System.out.println("added: " + input);
                classArr[i] = t;
                input = scanner.nextLine();
                inputArr = input.split(" ", 2);
                i += 1;
            }
            else if(inputArr[0].equals("done")){
                num = Integer.parseInt(inputArr[1]) - 1;
                classArr[num].isDone = true;
                System.out.println("Nice! I've marked this task as done: \n" + classArr[num].getStatusIcon() + classArr[num].description);
                input = scanner.nextLine();
                inputArr = input.split(" ", 2);
            }
            else{
                System.out.println("Here are the tasks in your list:\n");
                for(int j=0;j<i;j++){
                    System.out.println((j+1) + ". " + classArr[j].getStatusIcon() + " " + classArr[j].description);
                }
                input = scanner.nextLine();
                inputArr = input.split(" ", 2);
            }
        }

        {
            System.out.println("Bye! See you again soon!");
        }

    }
}
