package vendingmachine.controller;

import vendingmachine.domain.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {
    private final VendingMachine vendingMachine;

    public VendingMachineController(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
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
}
