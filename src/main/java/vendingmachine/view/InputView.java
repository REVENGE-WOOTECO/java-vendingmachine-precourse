package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.validator.ValidateMoney;

public class InputView {
    public int inputMoney() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String money = Console.readLine().trim();
        while (ValidateMoney.isNotValidInputMoney(money)) {
            money = Console.readLine().trim();
        }
        return Integer.parseInt(money);
    }
}
