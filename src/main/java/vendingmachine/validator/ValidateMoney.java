package vendingmachine.validator;

public class ValidateMoney {

    public static final int NEGATIVE_ZERO = 0;
    public static final int REMAINDER_ZERO = 0;
    public static final int MIN_MONEY_UNIT = 10;

    public static final String ERROR_MONEY_LETTER = "[ERROR] 보유할 금액으로 문자를 입력할 수 없습니다. 다시 입력해주세요.";
    public static final String ERROR_MONEY_WHITESPACE = "[ERROR] 보유할 금액으로 공백을 입력할 수 없습니다. 다시 입력해주세요.";
    public static final String ERROR_MONEY_NEGATIVE = "[ERROR] 보유할 금액으로 음수를 입력할 수 없습니다. 다시 입력해주세요.";
    public static final String ERROR_MONEY_UNIT = "[ERROR] 보유할 금액이 10원 단위로 입력해야합니다. 다시 입력해주세요.";

    public static boolean isNotValidInputMoney(String money) {
        try {
            validateDigit(money);
            validateEmpty(money);
            validateNegative(money);
            validateMoneyUnit(money);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    private static void validateDigit(String money) {
        boolean hasLetter = money.chars()
            .anyMatch(Character::isLetter);

        if (hasLetter) {
            throw new IllegalArgumentException(ERROR_MONEY_LETTER);
        }
    }

    private static void validateEmpty(String money) {
        if (money.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MONEY_WHITESPACE);
        }
    }

    private static void validateNegative(String money) {
        if (Integer.parseInt(money) < NEGATIVE_ZERO) {
            throw new IllegalArgumentException(ERROR_MONEY_NEGATIVE);
        }
    }

    private static void validateMoneyUnit(String money) {
        if (Integer.parseInt(money) % MIN_MONEY_UNIT != REMAINDER_ZERO) {
            throw new IllegalArgumentException(ERROR_MONEY_UNIT);
        }
    }
}
