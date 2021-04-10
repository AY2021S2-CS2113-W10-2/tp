package seedu.duke.login;

import seedu.duke.utilities.Ui;
import java.util.InputMismatchException;
import java.util.Scanner;
import static seedu.duke.utilities.Parser.loginCheckEmailsValidity;

public class LoginUi extends Ui {

    public LoginInfo getNewUserLoginInfo() {
        Boolean loginMatch;
        LoginInfo loginInfo;
        while (true) {
            loginInfo = getUserInputForLogin();
            loginMatch = loginCheckEmailsValidity(loginInfo.getUserId());
            if (loginMatch == false) {
                continue;
            }
            break;
        }
        return loginInfo;
    }

    public LoginInfo getLoginInfo() {
        LoginController loginController = new LoginController();
        LoginInfo loginInfo = null;
        printLoginMenu();
        Scanner sc = new Scanner(System.in);
        try {
            int choice = sc.nextInt();
            switch (choice) {
            case 1:
                loginInfo = getUserInputForLogin();
                break;
            case 2:
                loginInfo = loginController.addUser();
                break;
            case 3:
                printGoodBye();
                System.exit(0);
                break;
            default:
                printErrorMessage("You need to enter an integer that is either 1, 2 or 3! Please try again!");
                getLoginInfo();
                break;
            }
        } catch (InputMismatchException e) {
            printErrorMessage("You need to enter an integer that is either 1, 2 or 3! Please try again!");
            loginInfo = getLoginInfo();
        }
        assert loginInfo != null : "loginInfo is still null ";
        return loginInfo;
    }

    public void printLoginMenu() {
        System.out.println(super.logo);
        System.out.println("Select either 1 or 2 or 3 (use numbers):\n"
                + "[Emails address are case sensitive!]\n"
                + "1. Log In\n"
                + "2. Create a new account\n"
                + "3. Exit");
        System.out.println("Enter choice:");
    }

    public LoginInfo getUserInputForLogin() {
        Scanner sc = new Scanner(System.in);
        String userId;
        System.out.println("Enter email address:");
        userId = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();
        return new LoginInfo(userId, password);
    }
}
