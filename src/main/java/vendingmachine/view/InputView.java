package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.utils.InputValidator;

public class InputView {
    public static int requestCoin() {
        String input;
        do {
            input = Console.readLine();
        } while (InputValidator.isNotValidInputMoney(input));
        return Integer.parseInt(input);
    }

    public static String requestItemList() {
        String input;
        do {
            input = Console.readLine();
        } while (InputValidator.isNotValidItemList(input.split(";")));
        return input;
    }

    public static String requestItemForOrder() {
        String input;
        do {
            input = Console.readLine();
        } while (InputValidator.isNotValidNameFormat(input));
        return input;
    }
}
