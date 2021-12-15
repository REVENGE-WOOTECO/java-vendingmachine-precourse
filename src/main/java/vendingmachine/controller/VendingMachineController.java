package vendingmachine.controller;

import vendingmachine.domain.Beverages;
import vendingmachine.domain.Coins;
import vendingmachine.domain.Money;
import vendingmachine.domain.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {
    private final VendingMachine vendingMachine;

    public VendingMachineController() {
        this.vendingMachine = createVendingMachine();
    }

    public void run() {
        while (vendingMachine.isNotReturnCoins()) {
            buyBeverage();
        }
        OutputView.printInsertMoney(vendingMachine.getMoneyValue());
        OutputView.printResultCoins(vendingMachine.returnCoins());
    }

    private void buyBeverage() {
        boolean wrongInput = true;
        while (wrongInput) {
            OutputView.printInsertMoney(vendingMachine.getMoneyValue());
            String buyBeverageName = InputView.inputBuyBeverage();
            try {
                vendingMachine.buyBeverage(buyBeverageName);
                wrongInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public VendingMachine createVendingMachine() {
        Coins coins = inputMachineMoney();
        Beverages beverages = inputMachineBeverages();
        Money money = inputCustomerMoney();
        return new VendingMachine(coins, beverages, money);
    }

    public Coins inputMachineMoney() {
        boolean wrongInput = true;
        Coins coins = null;
        while (wrongInput) {
            try {
                int vendingMachineMoney = InputView.inputVendingMachineMoney();
                coins = new Coins(vendingMachineMoney);
                wrongInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        OutputView.printVendingMachineCoins(coins.getCoins());
        return coins;
    }

    public Beverages inputMachineBeverages() {
        boolean wrongInput = true;
        Beverages beverages = null;
        while (wrongInput) {
            try {
                String[] inputBeverages = InputView.inputBeverages();
                beverages = new Beverages(inputBeverages);
                wrongInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return beverages;
    }

    public Money inputCustomerMoney() {
        boolean wrongInput = true;
        Money money = null;
        while (wrongInput) {
            try {
                int customerMoney = InputView.inputInsertMoney();
                money = new Money(customerMoney);
                wrongInput = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return money;
    }
}
