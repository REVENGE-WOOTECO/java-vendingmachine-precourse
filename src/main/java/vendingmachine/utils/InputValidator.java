package vendingmachine.utils;

import java.util.HashSet;
import java.util.Set;
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
            if (isNotValidItemPrice(unitItemInfo)) {
                return true;
            }
        }
        if (isDuplicatedItemName(split)) {
            return true;
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

    private static boolean isNotValidItemPrice(String unitItemInfo) {
        String[] splitValue = parsingToInfo(unitItemInfo);
        return isNotValidMoneyValue(splitValue[1]);
    }

    private static boolean isDuplicatedItemName(String[] split) {
        Set<String> itemNames = new HashSet<>();
        for (String unitItemInfo : split) {
            String[] info = parsingToInfo(unitItemInfo);
            String itemName = info[0];
            try {
                requestValidationAboutDuplication(itemNames, itemName);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return true;
            }
            itemNames.add(itemName);
        }
        return false;
    }

    private static String[] parsingToInfo(String unitItemInfo) {
        String substring = unitItemInfo.substring(1, unitItemInfo.length() - 1);
        return substring.split(",");
    }

    private static void requestValidationAboutDuplication(Set<String> itemNames, String itemName) {
        if (itemNames.contains(itemName)) {
            throw new IllegalArgumentException("[ERROR] 중복된 Item 이름이 있습니다.");
        }
    }
}
