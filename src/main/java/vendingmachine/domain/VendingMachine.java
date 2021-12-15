package vendingmachine.domain;

import java.util.Map;

public class VendingMachine {
    private final Beverages beverages;
    private final Coins coins;
    private Money insertMoney;

    public VendingMachine(Coins coins, Beverages beverages, Money insertMoney) {
        this.coins = coins;
        this.beverages = beverages;
        this.insertMoney = insertMoney;
    }

    public void buyBeverage(String beverageName) {
        Beverage beverage = beverages.findBeverageByName(beverageName);
        insertMoney.canBuyBeverage(beverage.getPrice());
        beverage.BuyBeverage();
        this.insertMoney = insertMoney.deduct(beverage.getPrice());
    }

    public Map<Coin, Integer> returnCoins() {
        return coins.calculateReturnCoins(insertMoney.getValue());
    }

    public boolean isNotReturnCoins() {
        if (insertMoney.getValue() < beverages.findMinPrice()) {
            return false;
        }

        if (beverages.isAllSoled()) {
            return false;
        }

        return true;
    }

    public int getMoneyValue() {
        return insertMoney.getValue();
    }
}
