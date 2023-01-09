package banking;

public class Bank {

    public void createAccount() {

        Account account = new Account();
        DataBaseUtil.writeNewAccToDB(account.getCard().getCardNumber(), account.getCard().getPin());
    }

    public void logIntoAccount(String cardNumber, String cardPin) {

        Account account = DataBaseUtil.readAccountFromDB(cardNumber);

        String validPin = DataBaseUtil.readPinFromDB(cardNumber);

        if (validPin.equals(cardPin)) {
            PrintUtil.successfulLogIn();
            AccountMenu.showAccMenu(account);
        } else {
            PrintUtil.failedLogin();
        }
    }
}
