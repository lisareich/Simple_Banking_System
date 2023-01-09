package banking;

public class PrintUtil {

    public static void successfulLogIn() {
        System.out.println("\nYou have successfully logged in!\n");
    }

    public static void successfulLogOut() {
        System.out.println("\nYou have successfully logged out!\n");
    }

    public static void failedLogin() {
        System.out.println("\nWrong card number or PIN!\n");
    }

    public static void bye() {
        System.out.println("Bye!");
    }

    public static void bankMenu() {
        System.out.println("1. Create an account\n2. Log into account\n0. Exit");
    }

    public static void accountMenu() {
        System.out.println("1. Balance\n2. Add income\n3. Do transfer\n" +
                "4. Close account\n5. Log out\n0. Exit");
    }

    public static void createdCard() {
        System.out.println("\nYour card has been created");
    }

    public static void incomeAdded() {
        System.out.println("Income was added!\n");
    }
    public static void enterCard() {
        System.out.println("\nEnter your card number:");
    }

    public static void enterPin() {
        System.out.println("Enter your PIN:");
    }

    public static void enterIncome() {
        System.out.println("\nEnter income:");
    }

    public static void transferMenu() {
        System.out.println("\nTransfer\nEnter card number:");
    }

    public static void moneyToTransfer() {
        System.out.println("Enter how much money you want to transfer:");
    }

    public static void transferSucceded() {
        System.out.println("Success!\n");
    }

    public static void notEnoughMoney() {
        System.out.println("Not enough money!\n");
    }

    public static void accountClosed() {
        System.out.println("\nThe account has been closed!\n");
    }

    public static void cardDoesntExist() {
        System.out.println("Such a card does not exist.\n");
    }

    public static void mistakeInCardNum() {
        System.out.println("Probably you made a mistake in the card number.\nPlease try again!\n");
    }

    public static void printBalance(Account account) {
        System.out.printf("\nBalance: %d\n\n", DataBaseUtil.readBalanceFromDB(account));
    }
}
