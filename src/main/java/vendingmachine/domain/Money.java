package vendingmachine.domain;

import vendingmachine.validator.ValidateMoney;

public class Money {

    private final int money;

    public Money(int money) {
        ValidateMoney.validateNegative(money);
        ValidateMoney.validateMoneyUnit(money);
        this.money = money;
    }

    public Money deduct(Money price) {
        return new Money(this.money - price.money);
    }

    public int getMoney() {
        return money;
    }
}
