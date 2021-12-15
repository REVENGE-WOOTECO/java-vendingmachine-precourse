package vendingmachine.domain;

import vendingmachine.utils.validator.ValidateMoney;

public class Money {
    public static final String ERROR_MONEY_IS_LESS_THAN_PRICE = "[ERROR] 돈이 부족합니다.";

    private final int money;

    public Money(int money) {
        ValidateMoney.validateNegative(money);
        ValidateMoney.validateMoneyUnit(money);
        this.money = money;
    }

    public Money deduct(int price) {
        return new Money(this.money - price);
    }

    public boolean canBuyBeverage(int price) {
        if (money < price) {
            throw new IllegalArgumentException(ERROR_MONEY_IS_LESS_THAN_PRICE);
        }
        return true;
    }

    public int getValue() {
        return money;
    }
}
