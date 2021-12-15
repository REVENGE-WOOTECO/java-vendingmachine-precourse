package vendingmachine.domain;

import java.util.Objects;

public class Beverage {
    public static final String ERROR_BEVERAGE_AMOUNT_ZERO = "[ERROR] 동일한 상품명은 입력할 수 없습니다. 다시 입력해주세요.";

    private final String name;
    private final int price;
    private int amount;

    public Beverage(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public void BuyBeverage() {
        if (isSoldOut()) {
            throw new IllegalArgumentException(ERROR_BEVERAGE_AMOUNT_ZERO);
        }
        amount--;
    }

    public boolean isSameName(String name) {
        return name.equals(this.name);
    }

    public boolean isSoldOut() {
        return amount == 0;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Beverage beverage = (Beverage)o;
        return name.equals(beverage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
