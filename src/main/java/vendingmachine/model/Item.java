package vendingmachine.model;

import static vendingmachine.utils.Constant.*;

public class Item {
    private final String itemName;
    private final int price;
    private int quantity;

    public Item(String itemName, int price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean checkQuantity() {
        return quantity != ZERO_COUNT;
    }

    public boolean checkPrice(int price) {
        return this.price <= price;
    }

    public boolean checkOrderPossible(int money) {
        return quantity != ZERO_COUNT && money >= price;
    }

    public void orderItem() {
        quantity--;
    }

    public int getPrice() {
        return price;
    }
}
