import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, I'm Duke! What can I do for you today?");
        input = scanner.nextLine();
        String arr[] = new String[100];
        int i = 0;
        while(!input.equals("bye")) {
            //System.out.println(input);
            if(!input.equals("list")){
                System.out.println("added: " + input);
                arr[i] = input;
                input = scanner.nextLine();
                i += 1;
            }
            else{
                for(int j=0;j<i;j++){
                    System.out.println((j+1) + ". " + arr[j]);
                }
                input = scanner.nextLine();
            }
        }

        {
            System.out.println("Bye! See you again soon!");
        }

    }
//        if(input == "bye"){
//            System.out.println("Bye. Hope to see you again soon!\n");
//        }
//        else{
//            System.out.println( input );
//        }
    }
