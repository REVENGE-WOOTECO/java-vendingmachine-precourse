package vendingmachine.controller;

public class VendingmachineController {
	private Validator validator = new Validator();

	public int inputHoldingAmount(String money) {
		validator.holdingAmountValidator(money);
		return Integer.parseInt(money);
	}

}
