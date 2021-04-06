package seedu.duke.command;

import seedu.duke.email.Email;
import seedu.duke.email.EmailManager;
import seedu.duke.exceptions.InvalidIndexException;
import seedu.duke.utilities.Storage;
import seedu.duke.utilities.Ui;

import java.util.ArrayList;
import java.util.Arrays;

public class TagCommand extends Command {

    public TagCommand(String s) {
        super(s);
    }

    @Override
    public void execute(EmailManager emails, Ui ui, Storage storage) {
        ArrayList<Email> listedEmails = EmailManager.getListedEmailsList();

        if (listedEmails == null) {
            String feedback = "You have to list emails first" + System.lineSeparator()
                    + "=> list emails" + System.lineSeparator();
            ui.printFeedback(feedback);
            return;
        }

        try {
            int index = extractIndex();
            if (index <= 0 || index > listedEmails.size()) {
                throw new InvalidIndexException();
            }
            ArrayList<String> tags = extractTags();
            Email email = listedEmails.get(index - 1);
            email.setTags(tags);
            String feedback = "You have successfully set the following tags " + tags.toString();
            ui.printFeedback(feedback);
            storage.updateAllTypeEmails(emails.getEmailsList());
        } catch (InvalidIndexException e) {
            e.showErrorMessage("TAG");
            return;
        }
    }

    private ArrayList<String> extractTags() {
        String[] argList = userInput.split(" ", 3);
        String[] tags = argList[2].split(" ");
        return new ArrayList<>(Arrays.asList(tags));
    }

    private int extractIndex() throws InvalidIndexException {
        int index;
        try{
            String[] argList = userInput.split(" ", 3);
            index = Integer.parseInt(argList[1]);
        }
        catch (NumberFormatException e){
            throw new InvalidIndexException();
        }

        return index;
    }
}