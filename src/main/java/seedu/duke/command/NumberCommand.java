package seedu.duke.command;

import seedu.duke.EmailManager;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.exceptions.InvalidInputException;

public class NumberCommand extends Command {
    public NumberCommand(String s) {
        super(s);
    }

    public void execute(EmailManager emails, Ui ui, Storage storage) {
        try {
            int STARTINDEXOFEMAILTYPE = 7;
            String emailType = this.getUserInput().toLowerCase().substring(STARTINDEXOFEMAILTYPE);
            int totalEmails = 0;

            switch (emailType) {
            case ("archive"):
                totalEmails = emails.getNumOfArchiveEmails();
                break;
            case ("deleted"):
                totalEmails = emails.getNumOfDeletedEmails();
                break;
            case ("draft"):
                totalEmails = emails.getNumOfDraftEmails();
                break;
            case ("email"): //list number of all emails
                totalEmails = emails.getNumOfEmails();
                break;
            case ("inbox"):
                totalEmails = emails.getNumOfInboxEmails();
                break;
            case ("junk"):
                totalEmails = emails.getNumOfJunkEmails();
                break;
            case ("sent"):
                totalEmails = emails.getNumOfSentEmails();
                break;
            default:
                throw new InvalidInputException(); //throw exception
            }
            ui.printNumberOfEmails(totalEmails, emailType);
        } catch (InvalidInputException e) {
            Ui.showInvalidListTypeMessage();
        } catch (StringIndexOutOfBoundsException e) {
            Ui.showInvalidListTypeMessage();
        }

    }

}
