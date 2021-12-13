package vendingmachine.domain;

public class Beverage {
    private final String name;
    private final int price;
    private int amount;

    public Beverage(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}
