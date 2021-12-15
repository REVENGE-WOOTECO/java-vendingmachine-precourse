package vendingmachine.domain;

import java.util.Objects;

public class Beverage {
    private final String name;
    private final int price;
    private int amount;

    public Beverage(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
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
