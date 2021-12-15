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
        return money >= price;
    }

    public int getMoney() {
        return money;
    }
}
