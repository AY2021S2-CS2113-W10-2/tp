package seedu.duke.command;

import seedu.duke.EmailManager;
import seedu.duke.Parser;
import seedu.duke.Storage;
import seedu.duke.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(String s) {
        super(s);
    }

    @Override
    public void execute(EmailManager emails, Ui ui, Storage storage) {
        try {
            ArrayList emailTypeToPrint = Parser.getTypeToList(userInput);
            emails.printEmailByType(emailTypeToPrint);
        } catch (NullPointerException e1) {
            Ui.showInvalidListTypeMessage();
        }


    }
}
