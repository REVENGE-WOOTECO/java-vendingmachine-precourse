package vendingmachine;

import vendingmachine.view.VendingmachineView;

public class Application {
    public static void main(String[] args) {
        VendingmachineView vendingmachineView = new VendingmachineView();
        vendingmachineView.startMachine();
    }
}
