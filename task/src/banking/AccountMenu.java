package banking;

public class AccountMenu {

    public static void showAccMenu(Account account) {

        while(true) {

            PrintUtil.accountMenu();

            switch (InputUtil.nextInt()) {
                case 1: {
                    PrintUtil.printBalance(account);
                    break;
                }
                case 2: {
                    DataBaseUtil.updateBalance("income", InputUtil.inputIncome(), account);
                    break;
                }
                case 3: {
                    PrintUtil.transferMenu();
                    DataBaseUtil.transferMoney(account, InputUtil.inputCardNumberForTransfer());
                    break;
                }
                case 4: {
                    DataBaseUtil.removeAccountFromDB(account);
                    return;
                }
                case 5: {
                    PrintUtil.successfulLogOut();
                    return;
                }
                case 0: {
                    PrintUtil.bye();
                    System.exit(0);
                }
            }
        }
     }
}
