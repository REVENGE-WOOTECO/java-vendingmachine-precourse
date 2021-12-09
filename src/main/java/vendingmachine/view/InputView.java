package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.utils.InputValidator;

public class InputView {
    public static int requestCoin() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String input;
        do {
            input = Console.readLine();
        } while (InputValidator.isNotValidInputMoney(input));
        return Integer.parseInt(input);
    }

}
