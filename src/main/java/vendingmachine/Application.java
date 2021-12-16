package vendingmachine;

import vendingmachine.view.VendingMachineView;

public class Application {
	public static void main(String[] args) {
		VendingMachineView vendingmachineView = new VendingMachineView();
		vendingmachineView.startMachine();
	}
}
