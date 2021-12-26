package vendingmachine.model;

import java.util.List;

public class ItemRepository {
    private final List<Item> items;

    private ItemRepository(List<Item> items) {
        this.items = items;
    }

    public static ItemRepository of(List<Item> items) {
        return new ItemRepository(items);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean checkOrderAble(int smallChange) {
        return items.stream()
            .anyMatch(item -> item.checkOrderPossible(smallChange));
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean checkContainThisItem(String itemName) {
        return items.stream()
            .anyMatch(item -> item.getItemName().equals(itemName));
    }

    public boolean checkEnoughMoney(String itemName, int smallChange) {
        for (Item item : items) {
            if (!item.getItemName().equals(itemName)) {
                continue;
            }
            return item.checkPrice(smallChange);
        }
        return false;
    }

    public boolean checkEnoughQuantity(String itemName) {
        for (Item item : items) {
            if (!item.getItemName().equals(itemName)) {
                continue;
            }
            return item.checkQuantity();
        }
        return false;
    }
}
