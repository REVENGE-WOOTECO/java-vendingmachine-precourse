package vendingmachine.domain;

public class VendingMachine {
    private final Beverages beverages;
    private final Coins coins;
    private Money insertMoney;

    public VendingMachine(Beverages beverages, Coins coins, Money insertMoney) {
        this.beverages = beverages;
        this.coins = coins;
        this.insertMoney = insertMoney;
    }

    public void buyBeverage(String beverageName) {
        Beverage beverage = beverages.findBeverageByName(beverageName);
        insertMoney.canBuyBeverage(beverage.getPrice());
        beverage.BuyBeverage();
        this.insertMoney = insertMoney.deduct(beverage.getPrice());
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
