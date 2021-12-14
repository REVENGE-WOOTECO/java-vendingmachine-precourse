package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.validator.ValidateMoney;

public class InputView {
    public static final String INPUT_VENDING_MACHINE_MONEY = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    public static final String INPUT_BEVERAGES = "상품명과 가격, 수량을 입력해 주세요.";
    public static final String INPUT_INSERT_MONEY = "투입 금액을 입력해 주세요.";
    public static final String INPUT_BUY_BEVERAGE = "구매할 상품명을 입력해 주세요.";

    public int inputVendingMachineMoney() {
        System.out.println(INPUT_VENDING_MACHINE_MONEY);
        String machineMoney = Console.readLine();
        while (ValidateMoney.isNotValidInputMachineMoney(machineMoney)) {
            machineMoney = Console.readLine();
        }
        return Integer.parseInt(machineMoney);
    }
}
