package vendingmachine.dto;

import vendingmachine.domain.Money;

public class MoneyDto {

    private final int price;

    public MoneyDto(int price) {
        this.price = price;
    }

    public Money toEntity() {
        return new Money(price);
    }

    public int getPrice() {
        return price;
    }
}
