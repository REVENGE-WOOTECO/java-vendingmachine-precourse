package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.utils.InputValidator;

public class InputView {
    public static int requestCoin() {
        PrintView.requestInitialCoin();
        String input;
        do {
            input = Console.readLine();
        } while (InputValidator.isNotValidInputMoney(input));
        return Integer.parseInt(input);
    }

}
