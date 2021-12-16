package vendingmachine.model;

public class UserMoneyRepo {

	private UserMoneyRepo() {
	}

	private static UserMoneyRepo instance = new UserMoneyRepo();

	public static UserMoneyRepo getInstance() {
		return instance;
	}

	private int userMoney;

	public int getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(int userMoney) {
		this.userMoney = userMoney;
	}

	public void reduceMoney(String inputProductName) {
		int productPrice = ProductRepo.getInstance().priceForName(inputProductName);
		int currentMoney = UserMoneyRepo.getInstance().getUserMoney() - productPrice;
		UserMoneyRepo.getInstance().setUserMoney(currentMoney);
	}
}
