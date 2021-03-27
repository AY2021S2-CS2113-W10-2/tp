package seedu.duke;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Mojo {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private static Ui ui;
    private static EmailManager emails;
    private static Parser parser;
    private static Storage storage;

    public Mojo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            emails = new EmailManager(storage.load());
        } catch (IOException e) {
            emails = new EmailManager();
            e.printStackTrace();
        } catch (ParseException e) {
            emails = new EmailManager();
            e.printStackTrace();
        }
        parser = new Parser();
    }

    public void run() {
        ui.printMenu();
        while (true) {
            String userCommand = ui.getUserInput();
            parser.parse(userCommand.trim());
            try {
                parser.getCmd().execute(emails, ui, storage);
            } catch (NullPointerException e) {
                ui.showMessageForInvalidCommandInput();
            }
        }
    }

    public static void main(String[] args) {
        LoginInfoFileManager loginInfoFileManager = new LoginInfoFileManager();
        LoginManager loginManager = new LoginManager(loginInfoFileManager);
        LoginController lc = new LoginController(loginManager);
        LoginInfo providedLoginInfo = lc.run();
        String userId  = providedLoginInfo.getUserId();
        new Mojo(userId + ".json").run();

    }
}


