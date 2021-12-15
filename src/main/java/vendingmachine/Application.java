package vendingmachine;

import java.util.Map;

import vendingmachine.domain.Beverages;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Coins;
import vendingmachine.domain.Money;
import vendingmachine.domain.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Coins coins = new Coins(InputView.inputVendingMachineMoney());
        OutputView.printVendingMachineCoins(coins.getCoins());
        Beverages beverages = new Beverages(InputView.inputBeverages());
        Money insertMoney = new Money(InputView.inputInsertMoney());

        VendingMachine vendingMachine = new VendingMachine(beverages, coins, insertMoney);

        while (vendingMachine.isNotReturnCoins()) {
            OutputView.printInsertMoney(vendingMachine.getMoneyValue());
            String buyBeverageName = InputView.inputBuyBeverage();
            while (isNotValidBeverageName(vendingMachine, buyBeverageName)) {
                buyBeverageName = InputView.inputBuyBeverage();
            }
        }
        OutputView.printInsertMoney(vendingMachine.getMoneyValue());
        Map<Coin, Integer> resultCoins = coins.calculateReturnCoins(vendingMachine.getMoneyValue());
        OutputView.printResultCoins(resultCoins);
    }

    private static boolean isNotValidBeverageName(VendingMachine vendingMachine, String buyBeverageName) {
        try {
            vendingMachine.buyBeverage(buyBeverageName);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
