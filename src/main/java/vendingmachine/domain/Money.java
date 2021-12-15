package vendingmachine.domain;

public class Money {

    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public Money deduct(int price) {
        return new Money(this.money - price);
    }

    public boolean canBuyBeverage(int price) {
        if (money < price) {
            throw new IllegalArgumentException("\n[ERROR] 돈이 부족합니다.");
        }
        return true;
    }

    public int getValue() {
        return money;
    }
}
