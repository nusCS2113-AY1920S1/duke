import java.util.Scanner;

public class Level2 {

    public static void main(String []args) {
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?"); // prints the first line

        String[] store = new String[100];
        int num = 0;

        boolean check = true;
        while (check) {

            Scanner scanline = new Scanner(System.in); //read in lines
            String readin;
            String blackline = "--------------------";

            readin = scanline.next(); //scan line and check if the line is "bye"

            if ( ! readin.equals("bye") && ! readin.equals("list")) {
                System.out.println("added : " +readin + "\n" +blackline);
                store[num] = readin;
                num +=1;
            }

            if (readin.equals("list")){
                for (int i=0;i<num;i++){
                    System.out.println( i+1 + ". " + store[i]);
                }
                System.out.println(blackline);
            }

            if (readin.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!\n" + blackline);
                check = false;
            }
        }
    }
}