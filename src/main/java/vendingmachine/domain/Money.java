package vendingmachine.domain;

import vendingmachine.validator.ValidateMoney;

public class Money {

    private int money;

    public Money(int money) {
        ValidateMoney.validateNegative(money);
        ValidateMoney.validateMoneyUnit(money);
        this.money = money;
    }
}
