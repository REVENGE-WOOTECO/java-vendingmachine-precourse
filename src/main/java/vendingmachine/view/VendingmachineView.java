package vendingmachine.view;

import java.util.HashMap;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.controller.VendingmachineController;
import vendingmachine.model.Coin;
import vendingmachine.model.MachineMoneyRepo;
import vendingmachine.model.UserMoneyRepo;

public class VendingmachineView {

	private static final String REQUEST_VENDINGMACHINE_HOLDING_AMOUNT = "자판기가 보유하고 있는 금액을 입력해 주세요.";
	private static final String ERROR_MESSAGE_WRONG_HOLDING_AMOUNT = "[ERROR] 금액은 10단위의 숫자여야 합니다.";
	private static final String MESSAGE_COIN_AMOUNT = "자판기가 보유한 동전";
	private static final String REQUEST_PRODUCT_NAME_PRICE_QUANTITY = "상품명과 가격, 수량을 입력해 주세요.";
	private static final String ERROR_MESSAGE_WRONG_PRODUCT_FORM = "[ERROR] 상품 등록 양식이 올바르지 않습니다.";
	private static final String REQUEST_USER_INPUT_MONEY = "투입 금액을 입력해 주세요.";
	private static final String REQUEST_BUY_PRODUCT = "구매할 상품명을 입력해 주세요.";
	private static final String ERROR_MESSAGE_NOT_NUMBER = "[ERROR] 숫자를 입력해 주세요";
	private static final String ERROR_MESSAGE_NOT_EXIST_PRODUCT = "[ERROR] 해당 상품은 구매할 수 없습니다.";
	private static final String MESSAGE_REPAYMENT = "잔돈";

	private final VendingmachineController vendingmachineController = new VendingmachineController();
	private final MachineMoneyRepo mRepo = MachineMoneyRepo.getInstance();

	public void startMachine() {

		inputMachineMoney();

		makeCoin();

		inputProducts();

		inputUserMoney();

		inputBuyProduct();

		repayment();
	}

	private void inputMachineMoney() {
		while (true) {
			System.out.println(REQUEST_VENDINGMACHINE_HOLDING_AMOUNT);
			try {
				vendingmachineController.setMachineMoney(Console.readLine());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(ERROR_MESSAGE_WRONG_HOLDING_AMOUNT);
			}
		}
	}

	private void makeCoin() {
		System.out.println();
		vendingmachineController.convertCoin();
		System.out.println(MESSAGE_COIN_AMOUNT);
		printCoin(mRepo.getMachineCoin(), true);
	}

	private void inputProducts() {
		while (true) {
			System.out.println();
			System.out.println(REQUEST_PRODUCT_NAME_PRICE_QUANTITY);
			try {
				vendingmachineController.inputProducts(Console.readLine());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(ERROR_MESSAGE_WRONG_PRODUCT_FORM);
			}
		}
	}

	private void inputUserMoney() {
		while (true) {
			System.out.println();
			System.out.println(REQUEST_USER_INPUT_MONEY);
			try {
				vendingmachineController.setUserMoney(Console.readLine());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(ERROR_MESSAGE_NOT_NUMBER);
			}
		}
	}

	private void inputBuyProduct() {
		while (!vendingmachineController.isRepayment()) {
			printInputAmountMessage(REQUEST_BUY_PRODUCT);
			try {
				vendingmachineController.buyProduct(Console.readLine());
			} catch (IllegalArgumentException e) {
				System.out.println(ERROR_MESSAGE_NOT_EXIST_PRODUCT);
			}
		}
	}

	private void repayment() {
		printInputAmountMessage(MESSAGE_REPAYMENT);
		HashMap<Integer, Integer> repaymentMap = vendingmachineController.repayment(mRepo
			.getMachineCoin());
		printCoin(repaymentMap, false);
	}

	private void printCoin(HashMap<Integer, Integer> map, boolean flag) {
		for (Coin coin : Coin.values()) {
			if (map.get(coin.getAmount()) > 0 || flag) {
				System.out.println(
					coin.getAmount() + "원 - " + map.get(coin.getAmount()) + "개");
			}
		}
	}

	private void printInputAmountMessage(String message){
		System.out.println();
		System.out.println("투입 금액: " + UserMoneyRepo.getInstance().getUserMoney() + "원");
		System.out.println(message);
	}
}
