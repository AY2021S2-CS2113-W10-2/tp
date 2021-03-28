package seedu.duke.command;

import seedu.duke.email.EmailManager;
import seedu.duke.Utilities.Parser;
import seedu.duke.Utilities.Storage;
import seedu.duke.Utilities.Ui;
import seedu.duke.email.Deleted;
import seedu.duke.email.Email;
import seedu.duke.exceptions.InvalidIndexException;

import java.util.ArrayList;

public class DeleteCommand extends Command {

    public DeleteCommand(String s) {
        super(s);
    }

    public void execute(EmailManager emails, Ui ui, Storage storage) {

        ArrayList<Email> listedEmails = EmailManager.getListedEmailsList();
        if (listedEmails == null) {
            String feedback = "You have to list emails first" + System.lineSeparator()
                    + "=> list emails" + System.lineSeparator();
            ui.printFeedback(feedback);
            return;
        }

        try {
            int index = Parser.extractIndex(userInput);
            if (index <= 0 || index > listedEmails.size()) {
                throw new InvalidIndexException();
            }
            Email deletedEmail = listedEmails.get(index - 1);
            emails.deleteEmail(deletedEmail);
            if (!(deletedEmail instanceof Deleted)) {
                emails.addToDeleted(deletedEmail);
                System.out.println("Move this email to deleted folder");
            } else {
                System.out.println("Move this email from deleted folder");
            }
        } catch (InvalidIndexException e) {
            e.showErrorMessage("DELETE");
        }

        //ui.showDeleteResult(deletedEmail, emails.getNumOfEmails());
    }

}
