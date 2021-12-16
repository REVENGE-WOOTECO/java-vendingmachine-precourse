package vendingmachine.controller;

public class Validators {

	public void holdingAmountValidator(int checkMoney) {
		if (!isDivisibleByTen(checkMoney)) {
			throw new IllegalArgumentException();
		}
	}

	private boolean isDivisibleByTen(int money) {
		return money % 10 == 0;
	}

	public boolean productPriceValidator(int price) {
		return price >= 100 && isDivisibleByTen(price);
	}
}
