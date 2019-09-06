import java.util.Scanner;

public class Level1 {


    public static void main(String []args) {
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?"); // prints the first line

        boolean check = true;
        while (check) {

            Scanner scanline = new Scanner(System.in); //read in lines
            String readin;
            String blackline = "--------------------";

            readin = scanline.next(); //scan line and check if the line is "bye"
            if ( ! readin.equals("bye")) {
                System.out.println(readin + "\n" +blackline);
            }
            else{
                System.out.println("Bye. Hope to see you again soon!\n" + blackline);
                check = false;
            }
        }
    }
}