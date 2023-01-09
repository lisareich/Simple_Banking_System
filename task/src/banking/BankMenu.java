package banking;

import java.sql.SQLException;

public class BankMenu {

    public static void showBankMenu(Bank bank) throws SQLException {

        while(true) {

            PrintUtil.bankMenu();

            switch(InputUtil.nextInt()) {
                case 1: {
                    bank.createAccount();
                    break;
                }
                case 2: {
                    bank.logIntoAccount(InputUtil.inputCardNumber(), InputUtil.inputPin());
                    break;
                }
                case 0: {
                    PrintUtil.bye();
                    System.exit(0);
                }
            }
        }
    }
}
