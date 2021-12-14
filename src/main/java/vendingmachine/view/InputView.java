package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.validator.ValidateBeverage;
import vendingmachine.validator.ValidateMoney;

public class InputView {
    public static final String INPUT_VENDING_MACHINE_MONEY = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    public static final String INPUT_BEVERAGES = "상품명과 가격, 수량을 입력해 주세요.";
    public static final String INPUT_INSERT_MONEY = "투입 금액을 입력해 주세요.";
    public static final String INPUT_BUY_BEVERAGE = "구매할 상품명을 입력해 주세요.";
    public static final String SEMICOLON_DETERMINE = ";";

    public int inputVendingMachineMoney() {
        System.out.println(INPUT_VENDING_MACHINE_MONEY);
        String machineMoney = Console.readLine();
        while (ValidateMoney.isNotValidMoney(machineMoney)) {
            machineMoney = Console.readLine();
        }
        return Integer.parseInt(machineMoney);
    }

    public String[] inputBeverages() {
        System.out.println(INPUT_BEVERAGES);
        String[] beverages = Console.readLine().split(SEMICOLON_DETERMINE);
        while (ValidateBeverage.isNotValidInputBeverages(beverages)) {
            beverages = Console.readLine().split(SEMICOLON_DETERMINE);
        }
        return beverages;
    }

    public int inputInsertMoney() {
        System.out.println(INPUT_INSERT_MONEY);
        String insertMoney = Console.readLine();
        while (ValidateMoney.isNotValidMoney(insertMoney)) {
            insertMoney = Console.readLine();
        }
        return Integer.parseInt(insertMoney);
    }

    public String inputBuyBeverage() {
        System.out.println(INPUT_BUY_BEVERAGE);
        return Console.readLine();
    }

}
