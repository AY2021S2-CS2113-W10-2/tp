package seedu.duke.utilities;

import seedu.duke.command.*;
import seedu.duke.email.Email;
import seedu.duke.email.EmailManager;
import seedu.duke.exceptions.InvalidIndexException;
import seedu.duke.exceptions.InvalidPasswordException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    private Command cmd;

    public Parser() {
        cmd = null;
    }

    public Command getCmd() {
        return cmd;
    }

    public void parse(String userInputString) {
        if (userInputString.toLowerCase().matches("^(list)[ ].*$")) {
            cmd = new ListCommand(userInputString);
        } else if (userInputString.equalsIgnoreCase("bye")) {
            cmd = new ExitCommand(userInputString);
        } else if (userInputString.equalsIgnoreCase("help")) {
            cmd = new HelpCommand(userInputString);
        } else if (userInputString.equalsIgnoreCase("reset")) {
            cmd = new ResetCommand(userInputString);
        } else if (userInputString.toLowerCase().matches("^(read)[ ].*$")) {
            cmd = new ReadCommand(userInputString);
        } else if (userInputString.toLowerCase().matches("^(delete)[ ].*$")) {
            cmd = new DeleteCommand(userInputString);
        } else if (userInputString.toLowerCase().matches("^(archive)[ ].*$")) {
            cmd = new ArchiveCommand(userInputString);
        } else if (userInputString.toLowerCase().matches("^(find)[ ].*$")) {
            cmd = new FindCommand(userInputString);
        } else if (userInputString.equalsIgnoreCase(("compose"))) {
            cmd = new ComposeCommand((userInputString));
        } else if (userInputString.toLowerCase().matches("^(send)[ ].*$")) {
            cmd = new SendCommand(userInputString);
        } else if (userInputString.toLowerCase().matches("^(tag)[ ].*$")) {
            cmd = new TagCommand(userInputString);
        } else if (userInputString.toLowerCase().matches("^(edit)[ ].*$")) {
            cmd = new EditCommand(userInputString);
        } else if (userInputString.toLowerCase().startsWith("number")) {
            cmd = new NumberCommand(userInputString);
        } else if (userInputString.toLowerCase().startsWith("sort")) {
            cmd = new SortCommand(userInputString);
        } else {
            throw new AssertionError(userInputString);
        }
    }

    public static boolean checkEmailValidity(String userInput) {

        String email = userInput.trim();
        if (!(email.endsWith("@outlook.com") || email.endsWith("@hotmail.com")
                || email.endsWith("@gmail.com") || email.endsWith("@yahoo.com"))) {
            return false;
        }
        if (email.startsWith("@")) {
            return false;
        }

        return true;
    }

    public static int extractIndex(String userInput) throws InvalidIndexException {
        try {
            String[] cmdArg = userInput.split(" ", 2);
            assert cmdArg.length > 1 : "cmdArg length <= 1";
            String args = cmdArg[1].trim();
            int indexShow = Integer.parseInt(args);
            return indexShow;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    public static String removeCommand(String userInput) {
        return userInput.split(" ", 2)[1];
    }

    public static int[] extractMultipleIndices(String userInput) throws InvalidIndexException {
        try {
            String[] indicesStr = userInput.split(" ");
            int[] indices = new int[indicesStr.length];
            for (int i = 0; i < indicesStr.length; i++) {
                indices[i] = Integer.parseInt(indicesStr[i].trim());
            }
            return indices;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    public static ArrayList<Email> getTypeToList(EmailManager emailManager, String userInput) {
        String[] cmdArg = userInput.split(" ", 2);
        String emailType = cmdArg[1].trim().toLowerCase();
        ArrayList<Email> emailsToPrint = null;
        emailManager.setListedType(emailType);
        switch (emailType) {
        case ("allemails"):
            emailsToPrint = emailManager.getAllEmails();
            break;
        case ("archive"):
            emailsToPrint = emailManager.getArchivedEmails();
            break;
        case ("deleted"):
            emailsToPrint = emailManager.getDeletedEmails();
            break;
        case ("draft"):
            emailsToPrint = emailManager.getDraftEmails();
            break;
        case ("inbox"):
            emailsToPrint = emailManager.getInboxEmails();
            break;
        case ("junk"):
            emailsToPrint = emailManager.getJunkEmails();
            break;
        case ("sent"):
            emailsToPrint = emailManager.getSentEmails();
            break;
        default:
            throw new AssertionError(emailType);
        }
        return emailsToPrint;
    }

    /**
     * Converts a string containing multiple recipients to
     * a list of recipients.
     *
     * @param recipientsString String containing multiple recipients
     * @return list of recipients
     */
    public static ArrayList<String> parseRecipients(String recipientsString) {
        return new ArrayList<>(Arrays.asList(recipientsString.trim().split(";")));
    }


    /** A utility function to check whether a password is valid or not
     * Password should not contain any space.
     * Password should contain at least one digit(0-9).
     * Password length should be between 8 to 15 characters.
     * Password should contain at least one lowercase letter(a-z).
     * Password should contain at least one uppercase letter(A-Z).
     * Password should contain at least one special character ( @, #, %, &, !, $, etc….).
     * @param password
     * @throws InvalidPasswordException
     */
    public static void isValid(String password)
            throws InvalidPasswordException
    {

        // password length is between 8 and 15
        if (!((password.length() >= 8)
                && (password.length() <= 15))) {
            throw new InvalidPasswordException(1);
        }

        // contain space
        if (password.contains(" ")) {
            throw new InvalidPasswordException(2);
        }
        if (true) {
            int count = 0;

            // check digits from 0 to 9
            for (int i = 0; i <= 9; i++) {

                // to convert int to string
                String str1 = Integer.toString(i);

                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                throw new InvalidPasswordException(3);
            }
        }

        // for special characters
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(password);
        boolean isContain = m.find();
        if (!isContain) {
            throw new InvalidPasswordException(4);
        }

        if (true) {
            int count = 0;

            // checking capital letters
            for (int i = 65; i <= 90; i++) {

                // type casting
                char c = (char)i;

                String str1 = Character.toString(c);
                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                throw new InvalidPasswordException(5);
            }
        }

        if (true) {
            int count = 0;

            // checking small letters
            for (int i = 90; i <= 122; i++) {

                // type casting
                char c = (char)i;
                String str1 = Character.toString(c);

                if (password.contains(str1)) {
                    count = 1;
                }
            }
            if (count == 0) {
                throw new InvalidPasswordException(6);
            }
        }

        // The password is valid
    }
}
