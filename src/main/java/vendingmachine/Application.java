package vendingmachine;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        VendingMachine vendingMachine = VendingMachine.from(new ArrayList<>());
        vendingMachine.showCoinStatus();
        vendingMachine.makeOrderList();
        vendingMachine.requestOrder();
    }
}
