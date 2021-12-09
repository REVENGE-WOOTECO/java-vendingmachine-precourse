package vendingmachine;

import vendingmachine.view.InputView;

public class VendingMachine {

    public static void initialVendingMachine() {
        int coin = InputView.requestCoin();
        System.out.println("coin = " + coin);
    }
}
