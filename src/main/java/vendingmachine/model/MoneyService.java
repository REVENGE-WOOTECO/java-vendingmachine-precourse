package vendingmachine.model;

public class MoneyService {
	public int deductMoney(String inputProductName, int userMoney) {
		int productPrice = ProductRepo.getInstance().priceForProduct(inputProductName);
		return userMoney - productPrice;
	}
}
