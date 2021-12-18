package vendingmachine.controller;

import vendingmachine.model.HoldingCoins;
import vendingmachine.model.InsertedMoney;
import vendingmachine.model.Menus;
import vendingmachine.model.VendingMachineMenu;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {
    public void run() {
        HoldingCoins holdingCoins = inputVendingMachineCoin();
        OutputView.printHoldingCoins(holdingCoins);
        Menus menus = inputVendingMachineMenu();
        InsertedMoney currentMoney = inputInsertMoney();
        buyMenu(currentMoney, menus);
        OutputView.printReturnChangesWithMinimumCount(holdingCoins.getChanges(currentMoney));
    }

    private HoldingCoins inputVendingMachineCoin() {
        while (true) {
            try {
                OutputView.printRequestVendingMachineCoin();
                return new HoldingCoins(InputView.userInput());
            } catch (IllegalArgumentException exception) {
                OutputView.printErrorMessage(exception);
            }
        }
    }

    private Menus inputVendingMachineMenu() {
        while (true) {
            try {
                OutputView.printRequestMenuMessage();
                return new Menus(new VendingMachineMenu(InputView.userInput()).getMenus());
            } catch (IllegalArgumentException exception) {
                OutputView.printErrorMessage(exception);
            }
        }
    }

    private InsertedMoney inputInsertMoney() {
        while (true) {
            try {
                OutputView.printRequestInsertMoney();
                return new InsertedMoney(InputView.userInput());
            } catch (IllegalArgumentException exception) {
                OutputView.printErrorMessage(exception);
            }
        }
    }

    private void buyMenu(InsertedMoney currentMoney, Menus menus) {
        while(!menus.isTerminate(currentMoney)){
            OutputView.printCurrentMoney(currentMoney);
            OutputView.printRequestMenuName();
            currentMoney.buyMenu(menus.buyMenu(InputView.userInput()));
        }
        OutputView.printCurrentMoney(currentMoney);
    }
}
