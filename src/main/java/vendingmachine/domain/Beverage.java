package vendingmachine.domain;

import java.util.Objects;

public class Beverage {
    public static final String ERROR_BEVERAGE_Sold_OUT = "\n[ERROR] 해당 상품은 품절입니다";

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
            throw new IllegalArgumentException(ERROR_BEVERAGE_Sold_OUT);
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
