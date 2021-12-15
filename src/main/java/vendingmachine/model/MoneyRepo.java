package vendingmachine.model;

public class MoneyRepo {
	private MoneyRepo() {
	}

	private static MoneyRepo instance = new MoneyRepo();

	public static MoneyRepo getInstance() {
		return instance;
	}

	private int userMoney;

	public int getUserMoney() {
		return userMoney;
	}
}
