package seedu.duke.command;

import seedu.duke.EmailManager;
import seedu.duke.Parser;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.email.Email;
import seedu.duke.exceptions.InvalidIndexException;

import java.util.ArrayList;

public class ReadCommand extends Command {
    public ReadCommand(String s) {
        super(s);
    }

    @Override
    public void execute(EmailManager emails, Ui ui, Storage storage) {
        ArrayList<Email> listedEmails = EmailManager.getListedEmailsList();

        if (listedEmails == null) {
            String feedback = "You have to list emails first" + System.lineSeparator()
                    + "=> list emails";
            ui.printFeedback(feedback);
            return;
        }
        try {
            int index = Parser.extractIndex(userInput);
            if (index <= 0 || index > listedEmails.size()) {
                throw new InvalidIndexException();
            }
            Email email = listedEmails.get(index - 1);
            email.setRead(true);

            System.out.println(email);
            System.out.println();
        } catch (InvalidIndexException e) {
            e.showErrorMessage("READ");
        }

    }
}
