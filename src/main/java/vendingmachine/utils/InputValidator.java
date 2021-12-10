package vendingmachine.utils;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern patternForNumber = Pattern.compile("^[\\d]+");
    private static final Pattern patternForItemInfo = Pattern.compile("^\\[[ㄱ-ㅎ가-힣a-zA-Z]+,[\\d]+,[\\d]+]$");

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
            requestValidationToNumberPattern(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    private static void requestValidationToNumberPattern(String input) {
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

    public static boolean isNotValidItemList(String[] split) {
        for (String unitItemInfo : split) {
            if (isNotValidItemInfo(unitItemInfo)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNotValidItemInfo(String unitItemInfo) {
        try {
            requestValidationToItemPattern(unitItemInfo);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    private static void requestValidationToItemPattern(String unitItemInfo) {
        if (!patternForItemInfo.matcher(unitItemInfo).matches()) {
            throw new IllegalArgumentException("[ERROR] 올바른 패턴을 입력하세요. 예시는 다음과 같습니다 : [사이다,1000,10]");
        }
    }
}
