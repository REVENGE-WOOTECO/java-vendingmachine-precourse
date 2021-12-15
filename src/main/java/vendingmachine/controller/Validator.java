package vendingmachine.controller;

public class Validator {
	public void holdingAmountValidator(String checkMoney) {
		int money = 0;
		try {
			money = Integer.parseInt(checkMoney);
			if (!isDivisibleByTen(money)) {
				throw new IllegalArgumentException();
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}
	}

	public boolean isDivisibleByTen(int money) {
		return money % 10 == 0;
	}
}
