package vendingmachine.view;

import java.util.HashMap;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.controller.VendingmachineController;
import vendingmachine.model.Coin;

public class VendingmachineView {
	private final static String REQUEST_VENDINGMACHINE_HOLDING_AMOUNT = "자판기가 보유하고 있는 금액을 입력해 주세요.";
	private final static String ERROR_MESSAGE_WRONG_HOLDING_AMOUNT = "[ERROR] 금액은 10단위의 숫자여야 합니다.";
	private final static String MESSAGE_COIN_AMOUNT = "자판기가 보유한 동전";
	private final static String REQUEST_PRODUCT_NAME_PRICE_QUANTITY = "상품명과 가격, 수량을 입력해 주세요.";
	private final static String ERROR_MESSAGE_WRONG_PRODUCT_FORM = "[ERROR] 상품 등록 양식이 올바르지 않습니다.";
	private final static String REQUEST_USER_INPUT_MONEY = "투입 금액을 입력해 주세요.";
	private final static String REQUEST_BUY_PRODUCT = "구매할 상품명을 입력해 주세요.";
	private final static String ERROR_MESSAGE_NOT_NUMBER = "[ERROR] 숫자를 입력해 주세요";
	private final static String ERROR_MESSAGE_NOT_EXIST_PRODUCT = "[ERROR] 해당 상품은 구매할 수 없습니다.";
	private final static String MESSAGE_REPAYMENT = "잔돈";

	private VendingmachineController vendingmachineController = new VendingmachineController();

	private boolean holdingAmountFlag = false;
	private boolean productFlag = false;
	private boolean userMoneyFlag = false;
	private boolean buyProductFlag = false;
	private int holdingAmount = 0;
	private int userMoney = 0;
	private HashMap<Integer, Integer> machineCoin = null;

	public void startMachine() {
		while (!holdingAmountFlag) {
			inputHoldingAmount();
		}

		printCoin(vendingmachineController.makeCoin(holdingAmount));

		while (!productFlag) {
			inputProducts();
		}

		while (!userMoneyFlag) {
			inputUserMoney();
		}

		while (!buyProductFlag || !vendingmachineController.isrepayment(userMoney)) {
			inputBuyProduct();
		}

		repayment();
	}

	private void inputHoldingAmount() {
		System.out.println(REQUEST_VENDINGMACHINE_HOLDING_AMOUNT);
		try {
			holdingAmount = vendingmachineController.inputHoldingAmount(Console.readLine());
			holdingAmountFlag = true;
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR_MESSAGE_WRONG_HOLDING_AMOUNT);
			holdingAmountFlag = false;
		}
	}

	private void printCoin(HashMap<Integer, Integer> coinMap) {
		System.out.println();
		machineCoin = coinMap;
		System.out.println(MESSAGE_COIN_AMOUNT);
		System.out.println("500원 - " + coinMap.get(Coin.COIN_500.getAmount()) + "개");
		System.out.println("100원 - " + coinMap.get(Coin.COIN_100.getAmount()) + "개");
		System.out.println("50원 - " + coinMap.get(Coin.COIN_50.getAmount()) + "개");
		System.out.println("10원 - " + coinMap.get(Coin.COIN_10.getAmount()) + "개");
	}

	private void inputProducts() {
		System.out.println();
		System.out.println(REQUEST_PRODUCT_NAME_PRICE_QUANTITY);
		try {
			vendingmachineController.inputProducts(Console.readLine());
			productFlag = true;
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR_MESSAGE_WRONG_PRODUCT_FORM);
			productFlag = false;
		}
	}

	private void inputUserMoney() {
		System.out.println();
		System.out.println(REQUEST_USER_INPUT_MONEY);
		try {
			userMoney = vendingmachineController.inputUserMoney(Console.readLine());
			userMoneyFlag = true;
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR_MESSAGE_NOT_NUMBER);
			userMoneyFlag = false;
		}
	}

	private void inputBuyProduct() {
		System.out.println();
		System.out.println("투입금액: " + userMoney + "원");
		System.out.println(REQUEST_BUY_PRODUCT);
		try {
			String inputProductName = Console.readLine();
			vendingmachineController.buyProduct(inputProductName, userMoney);
			userMoney = vendingmachineController.decuctMoney(inputProductName, userMoney);
			buyProductFlag = true;
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR_MESSAGE_NOT_EXIST_PRODUCT);
			buyProductFlag = false;
		}
	}

	private void repayment() {
		HashMap<Integer, Integer> repaymentMap = vendingmachineController.repayment(machineCoin, userMoney);
		System.out.println();
		System.out.println("투입금액: " + userMoney + "원");
		System.out.println(MESSAGE_REPAYMENT);

		repaymentMap.forEach((key, value) -> {
			if (value != 0) {
				System.out.println(key + "원 - " + value + "개");
			}
		});
	}
}
