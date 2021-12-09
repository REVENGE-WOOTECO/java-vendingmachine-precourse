package vendingmachine.utils;

public class InputValidator {
    public static boolean notValidInputMoney(String input) {
        // 숫자 여부 검증
        if (isNotValidNumber(input)) {
            return true;
        }
        // 10으로 나눠떨어지는지 검증
        if (isNotValidMoneyValue(input)) {
            return true;
        }
        return false;
    }

    private static boolean isNotValidNumber(String input) {
        return false;
    }

    private static boolean isNotValidMoneyValue(String input) {
        return false;
    }


}
