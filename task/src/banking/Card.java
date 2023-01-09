package banking;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Random;

public class Card {

    private static final int BIN = 400000;

    private String cardNumber;
    private String pin;
    private long balance;

    public Card() {
        this.cardNumber = generateCardNumber();
        this.pin = generatePin();
    }

    public Card(String cardNumber, String pin, long balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public static boolean isValidLuhn(String cardNumber) {

        String cardNumWithoutCheck = cardNumber.substring(0,15);
        int checkSum = Integer.parseInt(cardNumber.substring(15));

        int sum = getLuhnSum(cardNumWithoutCheck) + checkSum;

        return sum % 10 == 0;
    }

    public String generateCardNumber() {
        Random random = new Random();
        int checkSum = 0;
        int accountID = random.nextInt(999999999);

        String cardNumWithoutCheck = String.format("%06d" + "%09d", BIN, accountID);

        int sum = getLuhnSum(cardNumWithoutCheck);

        while (sum % 10 != 0) {
            checkSum += 1;
            sum += 1;
        }

        return cardNumWithoutCheck + checkSum;
    }

    private String generatePin() {
        Random random = new Random();

        return String.format("%04d", random.nextInt(10000));
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public long getBalance() {
        return balance;
    }

    public void addIncome(long income) {
        setBalance(balance + income);
    }

    public void extractOutcome(long outcome) {
        setBalance(balance - outcome);
    }

    public void printCardInfo() {
        System.out.printf("Your card number:\n%s\nYour card PIN:\n%s\n\n", this.getCardNumber(), this.getPin());
    }

    public static int getLuhnSum(String cardNumWithoutCheck) {

        int sum = 0;

        int[] cardArray = Arrays.stream(cardNumWithoutCheck.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < cardArray.length; i++) {

            if (i % 2 == 0) {
                cardArray[i] *= 2;
            }
            if (cardArray[i] > 9) {
                cardArray[i] -= 9;
            }

            sum += cardArray[i];
        }

        return sum;
    }
}
