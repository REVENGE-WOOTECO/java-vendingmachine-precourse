package vendingmachine.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.dto.BeverageDto;
import vendingmachine.utils.validator.ValidateBeverage;
import vendingmachine.utils.validator.ValidateMoney;

public class InputView {
    private static final String INPUT_VENDING_MACHINE_MONEY = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String INPUT_BEVERAGES = "\n상품명과 가격, 수량을 입력해 주세요.";
    private static final String INPUT_INSERT_MONEY = "\n투입 금액을 입력해 주세요.";
    private static final String INPUT_BUY_BEVERAGE = "구매할 상품명을 입력해 주세요.";

    private static final String SEMICOLON_DETERMINE = ";";
    private static final String COMMA_DETERMINE = ",";
    private static final int SUBSTRING_IDX = 1;

    private InputView() {
    }

    public static int inputVendingMachineMoney() {
        System.out.println(INPUT_VENDING_MACHINE_MONEY);
        String machineMoney = Console.readLine();
        while (ValidateMoney.isNotValidMoney(machineMoney)) {
            machineMoney = Console.readLine();
        }
        return Integer.parseInt(machineMoney);
    }

    public static List<BeverageDto> inputBeverages() {
        System.out.println(INPUT_BEVERAGES);
        String[] beverages = Console.readLine().split(SEMICOLON_DETERMINE);
        while (ValidateBeverage.isNotValidInputBeverages(beverages)) {
            beverages = Console.readLine().split(SEMICOLON_DETERMINE);
        }
        return Arrays.stream(beverages)
            .map(InputView::splitInfos)
            .map(BeverageDto::from)
            .collect(Collectors.toList());
    }

    private static String[] splitInfos(String beverage) {
        String beverageInfo = beverage.substring(SUBSTRING_IDX, beverage.length() - SUBSTRING_IDX);
        return beverageInfo.split(COMMA_DETERMINE);
    }

    public static int inputInsertMoney() {
        System.out.println(INPUT_INSERT_MONEY);
        String insertMoney = Console.readLine();
        while (ValidateMoney.isNotValidMoney(insertMoney)) {
            insertMoney = Console.readLine();
        }
        return Integer.parseInt(insertMoney);
    }

    public static String inputBuyBeverage() {
        System.out.println(INPUT_BUY_BEVERAGE);
        return Console.readLine();
    }
}
