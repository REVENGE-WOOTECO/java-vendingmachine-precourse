package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.controller.VendingmachineController;

public class VendingmachineView {
	private final static String REQUEST_VENDINGMACHINE_HOLDING_AMOUNT = "자판기가 보유하고 있는 금액을 입력해 주세요.";
	private final static String ERROR_MESSAGE_WRONG_HOLDING_AMOUNT = "[ERROR] 금액은 10단위의 숫자여야 합니다.";

	private VendingmachineController vendingmachineController = new VendingmachineController();

	private boolean flag = false;

	public void startMachine() {
		while (!flag) {
			holdingAmount();
		}
	}

	private void holdingAmount() {
		System.out.println(REQUEST_VENDINGMACHINE_HOLDING_AMOUNT);
		try {
			int money = vendingmachineController.inputHoldingAmount(Console.readLine());
			flag = true;
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR_MESSAGE_WRONG_HOLDING_AMOUNT);
			flag = false;
		}
	}
}
