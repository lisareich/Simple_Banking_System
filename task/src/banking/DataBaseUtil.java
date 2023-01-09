package banking;

import org.sqlite.SQLiteDataSource;

import javax.swing.*;
import java.sql.*;

public class DataBaseUtil {

    public static Connection con;

    public static void connect(String database) throws SQLException {
        String url = "jdbc:sqlite:" + database;

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        con = dataSource.getConnection();
    }

    public static void createDB() {

        String createTable = "CREATE TABLE IF NOT EXISTS card " +
                "(id INTEGER, " +
                "number TEXT," +
                "pin TEXT," +
                "balance INTEGER DEFAULT 0)";

        try (PreparedStatement stmt = con.prepareStatement(createTable)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void writeNewAccToDB(String cardNumber, String pin) {

        String addToDB = "INSERT INTO card (number, pin) VALUES (?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(addToDB)) {
            stmt.setString(1, cardNumber);
            stmt.setString(2, pin);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String readPinFromDB(String cardNumber) {

        String readPin = "SELECT pin FROM card WHERE number = ?";

        try (PreparedStatement stmt = con.prepareStatement(readPin)) {
            stmt.setString(1, cardNumber);

            try (ResultSet resSet = stmt.executeQuery()) {
                return resSet.getString("pin");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "0";
    }

    public static Account readAccountFromDB(String cardNumber) {

        String readAcc = "SELECT * FROM card WHERE number = ?";

        try (PreparedStatement stmt = con.prepareStatement(readAcc)) {
            stmt.setString(1, cardNumber);

            try (ResultSet resSet = stmt.executeQuery()) {

                String pin = resSet.getString("pin");
                int balance = resSet.getInt("balance");
                return new Account(new Card(cardNumber, pin, balance));

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void removeAccountFromDB(Account account) {

        String deleteAcc = "DELETE FROM card WHERE number = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(deleteAcc)) {
            preparedStatement.setString(1, account.getCard().getCardNumber());
            preparedStatement.executeUpdate();
            PrintUtil.accountClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBalance(String operationType, long money, Account account) {

        String updateBalance;

        if (operationType.equals("income")) {
            updateBalance = "UPDATE card SET balance = balance + ? WHERE number = ?";
        } else {
            updateBalance = "UPDATE card SET balance = balance - ? WHERE number = ?";
        }

        try (PreparedStatement preparedStatement = con.prepareStatement(updateBalance)) {

            preparedStatement.setLong(1, money);
            preparedStatement.setString(2, account.getCard().getCardNumber());
            preparedStatement.executeUpdate();
            PrintUtil.incomeAdded();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static long readBalanceFromDB(Account account) {

        String readBalance = "SELECT balance FROM card WHERE number = ?";

        try (PreparedStatement stmt = con.prepareStatement(readBalance)) {
            stmt.setString(1, account.getCard().getCardNumber());

            try (ResultSet resSet = stmt.executeQuery()) {
                return resSet.getLong("balance");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    public static boolean isPresentedInDB(String cardNumber) {

        String chooseAcc = "SELECT * FROM card WHERE number = ?";

        try (PreparedStatement stmt = con.prepareStatement(chooseAcc)) {
            stmt.setString(1, cardNumber);

            try (ResultSet resSet = stmt.executeQuery()) {
                return resSet.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void transferMoney(Account accountSender, String cardNumber) {

        if (!Card.isValidLuhn(cardNumber)) {
            PrintUtil.mistakeInCardNum();

        } else {

            if (isPresentedInDB(cardNumber)) {
                Account accountReceiver = readAccountFromDB(cardNumber);
                long money = InputUtil.inputMoneyToTransfer();

                if (readBalanceFromDB(accountSender) >= money) {

                        try {
                            con.setAutoCommit(false);
                            updateBalance("outcome", money, accountSender);
                            updateBalance("income", money, accountReceiver);
                            con.commit();
                            PrintUtil.transferSucceded();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                } else {
                    PrintUtil.notEnoughMoney();
                }

            } else {
                PrintUtil.cardDoesntExist();
            }
        }
    }
}
