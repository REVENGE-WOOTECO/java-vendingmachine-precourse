package vendingmachine.utils;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern patternForNumber = Pattern.compile("^[0-9]+");

    public static boolean isNotValidInputMoney(String input) {
        if (isNotValidNumber(input)) {
            return true;
        }
        if (isNotValidMoneyValue(input)) {
            return true;
        }
        return false;
    }

    private static boolean isNotValidNumber(String input) {
        try {
            requestValidationToPattern(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
        requestValidationToPattern(input);
        return false;
    }

    private static void requestValidationToPattern(String input) {
        if (!patternForNumber.matcher(input).matches()) {
            throw new IllegalArgumentException("[ERROR] 0 이상의 숫자만 입력하세요.");
        }
    }

    private static boolean isNotValidMoneyValue(String input) {
        try {
            checkRemainderIsZero(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    private static void checkRemainderIsZero(String input) {
        if (Integer.parseInt(input) % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 10원으로 나누어떨어지는 금액만 가능합니다.");
        }
    }

}
