package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.domain.VendingMachine;
import vendingmachine.utils.VendingMachineGenerator;

public class Application {
    public static void main(String[] args) {
        VendingMachineGenerator vendingMachineGenerator = new VendingMachineGenerator();
        VendingMachine vendingMachine = vendingMachineGenerator.createVendingMachine();
        
        VendingMachineController vendingMachineController = new VendingMachineController(vendingMachine);
        vendingMachineController.run();
    }
}
