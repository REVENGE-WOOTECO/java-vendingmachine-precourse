package vendingmachine.utils;

import static vendingmachine.utils.Constant.*;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class InputValidator {
    private static final String REGEX_FOR_NUMBER = "^[\\d]+";
    private static final String REGEX_FOR_ITEM_INFO = "^\\[[ㄱ-ㅎ가-힣a-zA-Z]+,[\\d]+,[\\d]+]$";
    private static final String REGEX_FOR_STRING_NAME = "^[ㄱ-ㅎ가-힣a-zA-Z]+";
    private static final int DIVIDED_VALUE = 10;

    private static final Pattern ONLY_DIGIT_PATTERN = Pattern.compile(REGEX_FOR_NUMBER);
    private static final Pattern ONLY_ITEM_INFO_PATTERN = Pattern.compile(REGEX_FOR_ITEM_INFO);
    private static final Pattern ONLY_ITEM_NAME_PATTERN = Pattern.compile(REGEX_FOR_STRING_NAME);

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
        if (!ONLY_DIGIT_PATTERN.matcher(input).matches()) {
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
        if (Integer.parseInt(input) % DIVIDED_VALUE != 0) {
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
        return isDuplicatedItemName(split);
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
        if (!ONLY_ITEM_INFO_PATTERN.matcher(unitItemInfo).matches()) {
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
            String itemName = info[NAME_INDEX];
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
        return substring.split(INFO_SPLIT_VALUE);
    }

    private static void requestValidationAboutDuplication(Set<String> itemNames, String itemName) {
        if (itemNames.contains(itemName)) {
            throw new IllegalArgumentException("[ERROR] 중복된 Item 이름이 있습니다.");
        }
    }

    public static boolean isNotValidNameFormat(String input) {
        try {
            requestValidationToStringNamePattern(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    private static void requestValidationToStringNamePattern(String input) {
        if (!ONLY_ITEM_NAME_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("[ERROR] 상품명은 영문 또는 한글로만 입력하세요.");
        }
    }
}
