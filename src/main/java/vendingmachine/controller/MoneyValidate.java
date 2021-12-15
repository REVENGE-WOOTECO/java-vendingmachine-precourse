package vendingmachine.controller;

import java.util.List;

import vendingmachine.model.ProductRepo;

public class MoneyValidate {
	public boolean isLowPrice(int currentMoney) {
		List<Integer> priceList = ProductRepo.getInstance().getPriceList();
		return priceList.stream().allMatch(registeredMoney -> registeredMoney > currentMoney);
	}
}
