package vendingmachine.utils;

import java.util.List;

import vendingmachine.domain.Beverages;
import vendingmachine.domain.Coins;
import vendingmachine.domain.Money;
import vendingmachine.domain.VendingMachine;
import vendingmachine.dto.BeverageDto;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineGenerator {
    boolean wrongInput;

    public VendingMachine createVendingMachine() {
        Coins coins = registerMachineMoney();
        Beverages beverages = registerBeverages();
        Money money = registerCustomerMoney();
        return new VendingMachine(coins, beverages, money);
    }

    public Coins registerMachineMoney() {
        wrongInput = true;
        Coins coins = null;
        while (wrongInput) {
            try {
                int vendingMachineMoney = InputView.inputVendingMachineMoney();
                coins = new Coins(vendingMachineMoney);
                OutputView.printVendingMachineCoins(coins.getCoins());
                wrongInput = false;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
        return coins;
    }

    public Beverages registerBeverages() {
        wrongInput = true;
        Beverages beverages = null;
        while (wrongInput) {
            try {
                List<BeverageDto> inputBeverages = InputView.inputBeverages();
                beverages = new Beverages(inputBeverages);
                wrongInput = false;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
        return beverages;
    }

    public Money registerCustomerMoney() {
        wrongInput = true;
        Money money = null;
        while (wrongInput) {
            try {
                int customerMoney = InputView.inputInsertMoney();
                money = new Money(customerMoney);
                wrongInput = false;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
        return money;
    }
}
