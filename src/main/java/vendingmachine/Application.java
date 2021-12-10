package vendingmachine;

public class Application {
    public static void main(String[] args) {
        VendingMachine vendingMachine = VendingMachine.from();
        vendingMachine.showCoinStatus();
        vendingMachine.makeOrderList();
    }
}
