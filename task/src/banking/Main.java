package banking;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataBaseUtil.connect(args[1]);
        DataBaseUtil.createDB();
        BankMenu.showBankMenu(new Bank());
    }
}