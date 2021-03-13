package seedu.duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;
    private static final String logo = " _________   _____   _____   _____\n"
            + "|  _   _  | |  _  | |_   _| |  _  |\n"
            + "| | | | | | | | | |   | |   | | | |\n"
            + "| | | | | | | |_| |  _| |   | |_| |\n"
            + "|_| |_| |_| |_____| |___|   |_____|\n";


    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserInput() {
        printDivider();
        System.out.println("Enter Command: ");
        String inputLine = in.nextLine();
        while (inputLine.trim().isEmpty()) {
            inputLine = in.nextLine();
        }
        printDivider();
        return inputLine;
    }

    private void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public void printFeedback(String feedback) {
        System.out.println(feedback);
        printDivider();
    }

    public void printMenu() {
        System.out.println("Hello! I'm MojoHR\n" + logo + "What can I do for you?");
        System.out.println("> Use the keyword \"LIST (type) \" to print the emails by types");
        System.out.println("> Use the keyword \"HELP\" to print the menu");
        System.out.println("> Use the keyword \"BYE\" to exit");
    }

    public static void showInvalidIdMessage(String type) {
        System.out.println("OOPS!!! The Email ID that you " + type + " is invalid.");
    }

    public void showMessageForInvalidCommandInput() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(!");
    }

    public static void showInvalidListTypeMessage() {
        System.out.println("OOPS!!! The Email type that you enter is invalid.");
        System.out.println("It must be one of: [emails, inbox, archive, deleted, draft, junk, sent]");
    }
}
