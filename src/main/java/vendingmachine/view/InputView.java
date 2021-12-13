package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public int inputMoney() {
        String money = Console.readLine().trim();
        while (isNotValidInputMoney(money)) {
            money = Console.readLine().trim();
        }
        return Integer.parseInt(money);
    }

    private boolean isNotValidInputMoney(String money) {
        try {
            validateDigit(money);
            validateEmpty(money);
            validateMoneyUnit(money);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    private void validateMoneyUnit(String money) {
        if (Integer.parseInt(money) % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 보유할 금액이 10원 단위로 입력해야합니다. 다시 입력해주세요.");
        }
    }

    private void validateEmpty(String money) {
        if (money.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 보유할 금액으로 공백을 입력할 수 없습니다. 다시 입력해주세요.");
        }
    }

    private void validateDigit(String money) {
        if (money.chars().anyMatch(Character::isLetter)) {
            throw new IllegalArgumentException("[ERROR] 보유할 금액으로 문자를 입력할 수 없습니다. 다시 입력해주세요.");
        }
    }
}
