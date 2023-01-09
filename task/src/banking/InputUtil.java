package banking;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputCardNumber() {
        PrintUtil.enterCard();
        return scanner.next();
    }

    public static String inputCardNumberForTransfer() {
        return scanner.next();
    }

    public static Long inputMoneyToTransfer() {
        PrintUtil.moneyToTransfer();
        return scanner.nextLong();
    }

    public static String inputPin() {
        PrintUtil.enterPin();
        return scanner.next();
    }

    public static Long inputIncome() {
        PrintUtil.enterIncome();
        return scanner.nextLong();
    }

    public static int nextInt() {
        return scanner.nextInt();
    }

}
