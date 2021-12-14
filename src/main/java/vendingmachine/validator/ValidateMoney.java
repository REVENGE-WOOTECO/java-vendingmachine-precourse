package vendingmachine.validator;

import vendingmachine.domain.Coin;

public class ValidateMoney {
    public static final int NEGATIVE_ZERO = 0;
    public static final int REMAINDER_ZERO = 0;

    public static final String ERROR_MONEY_NOT_DIGIT = "[ERROR] 금액은 숫자여야 합니다. 다시 입력해주세요.";
    public static final String ERROR_MONEY_NEGATIVE = "[ERROR] 금액으로 음수를 입력할 수 없습니다. 다시 입력해주세요.";
    public static final String ERROR_MONEY_UNIT = "[ERROR] 금액을 10원 단위로 입력해야합니다. 다시 입력해주세요.";

    public static boolean isNotValidMoney(String machineMoney) {
        try {
            int machineMoneyValue = validateDigit(machineMoney);
            validateNegative(machineMoneyValue);
            validateMoneyUnit(machineMoneyValue);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    private static int validateDigit(String money) {
        try {
            return Integer.parseInt(money);
        } catch (Exception e) {
            throw new IllegalArgumentException(ERROR_MONEY_NOT_DIGIT);
        }
    }

    private static void validateNegative(int money) {
        if (money < NEGATIVE_ZERO) {
            throw new IllegalArgumentException(ERROR_MONEY_NEGATIVE);
        }
    }

    private static void validateMoneyUnit(int money) {
        if (money % Coin.COIN_10.getAmount() != REMAINDER_ZERO) {
            throw new IllegalArgumentException(ERROR_MONEY_UNIT);
        }
    }
}
