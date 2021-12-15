package vendingmachine.dto;

import vendingmachine.domain.Beverage;

public class BeverageDto {
    private static final int NAME_IDX = 0;
    private static final int PRICE_IDX = 1;
    private static final int AMOUNT_IDX = 2;

    private final String name;
    private final int price;
    private final int amount;

    private BeverageDto(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public static BeverageDto from(String[] beverageInfos) {
        String name = beverageInfos[NAME_IDX];
        int price = Integer.parseInt(beverageInfos[PRICE_IDX]);
        int amount = Integer.parseInt(beverageInfos[AMOUNT_IDX]);

        return new BeverageDto(name, price, amount);
    }

    public Beverage toEntity() {
        return new Beverage(name, price, amount);
    }
}
