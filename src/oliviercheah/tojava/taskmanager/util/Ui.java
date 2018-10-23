package oliviercheah.tojava.taskmanager.util;

import java.util.Scanner;


public class Ui {

    private Scanner in;

    public Ui(){
        in = new Scanner(System.in);
    }

    public static void printWelcome(){
        System.out.println("Welcome to task manager lvl 6!");
    }

    public static void printError(String message) {
        System.out.println(message);
    }

    public String readUserCommand() {
        System.out.print("Your task? ");
        return in.nextLine().trim();
    }

    public static void showToUser(String message){
        System.out.println(message);
    }
}
