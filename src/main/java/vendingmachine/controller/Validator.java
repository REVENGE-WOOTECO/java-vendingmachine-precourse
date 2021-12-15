package vendingmachine.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

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

	private boolean isDivisibleByTen(int money) {
		return money % 10 == 0;
	}

	public void productsValidator(String products) {
		if(isWrongProductForm(products)){
			throw new IllegalArgumentException();
		}
		//쉼표, 세미콜론 위치 오류
		//상품금액 100원미만이거나 10원으로나누어떨어지지 않는 수 거르기
	}

	private boolean isWrongProductForm(String products) {
		long exceptionCount = Arrays.stream(products.split(";")).collect(Collectors.toList()).stream()
			.filter(product -> product.charAt(0) != '[' || product.charAt(product.length() - 1) != ']').count();
		return exceptionCount != 0;
	}
}
