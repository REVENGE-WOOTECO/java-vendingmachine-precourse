package vendingmachine.model;

import java.util.List;

public class MoneyService {
	public boolean isLowPrice() {
		List<Integer> priceList = ProductRepo.getInstance().getPriceList();
		return priceList.stream()
			.allMatch(registeredMoney -> registeredMoney > UserMoneyRepo.getInstance().getUserMoney());
	}
}
